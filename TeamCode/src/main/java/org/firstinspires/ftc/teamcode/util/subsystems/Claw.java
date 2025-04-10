package org.firstinspires.ftc.teamcode.util.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.constants.claw;
import org.firstinspires.ftc.teamcode.util.constants.claw.clawState;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dev.frozenmilk.dairy.core.dependency.Dependency;
import dev.frozenmilk.dairy.core.dependency.annotation.SingleAnnotation;
import dev.frozenmilk.dairy.core.wrapper.Wrapper;
import dev.frozenmilk.mercurial.commands.Lambda;
import dev.frozenmilk.mercurial.subsystems.SDKSubsystem;
import dev.frozenmilk.mercurial.subsystems.Subsystem;
import dev.frozenmilk.util.cell.Cell;

public class Claw extends SDKSubsystem {
    public static final Claw INSTANCE = new Claw();
    private Claw() { }

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

    private static double targetPos;

    private final Cell<Servo> clawServo = subsystemCell(() -> getHardwareMap().get(Servo.class, claw.CLAW));

    public void setTarget(clawState state) {
        switch (state) {
            case OPEN:
                targetPos = claw.openPos;
                break;
            case CLOSED:
                targetPos = claw.closePos;
                break;
        }
        clawServo.get().setPosition(targetPos);
    }

    @Override
    public void preUserInitHook(@NonNull Wrapper opMode) {
    }

    public Lambda setState(clawState clawState) {
        return new Lambda("setClaw")
                .setInit(() -> setTarget(clawState));
    }
}
