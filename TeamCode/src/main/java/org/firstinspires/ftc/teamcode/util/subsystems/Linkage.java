package org.firstinspires.ftc.teamcode.util.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.util.constants.slides;
import org.firstinspires.ftc.teamcode.util.constants.slides.linkageState;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dev.frozenmilk.dairy.core.dependency.Dependency;
import dev.frozenmilk.dairy.core.dependency.annotation.SingleAnnotation;
import dev.frozenmilk.dairy.core.util.controller.calculation.pid.DoubleComponent;
import dev.frozenmilk.dairy.core.util.controller.implementation.DoubleController;
import dev.frozenmilk.dairy.core.util.supplier.numeric.CachedMotionComponentSupplier;
import dev.frozenmilk.dairy.core.util.supplier.numeric.EnhancedDoubleSupplier;
import dev.frozenmilk.dairy.core.util.supplier.numeric.MotionComponents;
import dev.frozenmilk.dairy.core.wrapper.Wrapper;
import dev.frozenmilk.mercurial.commands.Lambda;
import dev.frozenmilk.mercurial.subsystems.SDKSubsystem;
import dev.frozenmilk.mercurial.subsystems.Subsystem;
import dev.frozenmilk.util.cell.Cell;

public class Linkage extends SDKSubsystem {
    public static final Linkage INSTANCE = new Linkage();
    private Linkage() { }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    public @interface Attach { }

    private Dependency<?> dependency = Subsystem.DEFAULT_DEPENDENCY.and(new SingleAnnotation<>(Attach.class));

    @NonNull
    @Override
    public Dependency<?> getDependency() {
        return dependency;
    }

    @Override
    public void setDependency(@NonNull Dependency<?> dependency) {
        this.dependency = dependency;
    }

    private static Wrapper currentMode;
    private static double targetPos;
    private static double posTolerance = 50.0;

    private static linkageState linkageState;

    private final Cell<DcMotorEx> leftSlides = subsystemCell(() -> getHardwareMap().get(DcMotorEx.class, slides.LEFTLINKAGE));
    private final Cell<DcMotorEx> rightSlides = subsystemCell(() -> getHardwareMap().get(DcMotorEx.class, slides.RIGHTLINKAGE));

    private final Cell<EnhancedDoubleSupplier> encoder = subsystemCell(() -> new EnhancedDoubleSupplier(() -> (double) leftSlides.get().getCurrentPosition()));

    private final CachedMotionComponentSupplier<Double> targetSupplier = new CachedMotionComponentSupplier<>(motionComponent -> {
        if (motionComponent == MotionComponents.STATE) {
            return targetPos;
        }
        return Double.NaN;
    });

    private final CachedMotionComponentSupplier<Double> toleranceSupplier = new CachedMotionComponentSupplier<>(motionComponent -> {
        if (motionComponent == MotionComponents.STATE) {
            return posTolerance;
        }
        return Double.NaN;
    });

    private final Cell<DoubleController> controller = subsystemCell(() ->
            new DoubleController(
                    targetSupplier,
                    encoder.get(),
                    toleranceSupplier,
                    (Double power) -> {
                        rightSlides.get().setPower(power);
                        leftSlides.get().setPower(power);
                    },
                    new DoubleComponent.P(MotionComponents.STATE, slides.SlidesP)
                            .plus(new DoubleComponent.I(MotionComponents.STATE, slides.SlidesI))
                            .plus(new DoubleComponent.D(MotionComponents.STATE, slides.SlidesD))
            )
    );

    public void setTarget(double target) {
//        controller.get().setEnabled(true);
        targetPos = target;
        targetSupplier.reset();
    }

    public void setSlideState(linkageState slideState) {
        switch (slideState) {
            case HIGH_BAR:
                setTarget(slides.highBarPos);
                break;
            case PLACE_SPEC:
                setTarget(slides.placeSpecPos);
                break;
            case WALL:
                setTarget(slides.wallPos);
                break;
            case REST:
                setTarget(slides.restPos);
                break;
        }

        linkageState = slideState;
    }

    public double getEncoder() {
        return (encoder.get().state());
    }

    public double getTargetPos() {
        return targetPos;
    }

    @Override
    public void preUserInitHook(@NonNull Wrapper opMode) {
        currentMode = opMode;
        leftSlides.get().setDirection(DcMotorEx.Direction.REVERSE);
        leftSlides.get().setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightSlides.get().setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public Lambda setState(linkageState slideState) {
        return new Lambda("setSlideState")
                .setInit(() -> setSlideState(slideState))
                .setFinish(() -> controller.get().finished());
    }
}
