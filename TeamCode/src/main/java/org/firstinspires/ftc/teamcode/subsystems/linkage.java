package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dev.frozenmilk.dairy.core.dependency.Dependency;
import dev.frozenmilk.dairy.core.dependency.annotation.SingleAnnotation;
import dev.frozenmilk.dairy.core.wrapper.Wrapper;
import dev.frozenmilk.mercurial.commands.Lambda;
import dev.frozenmilk.mercurial.subsystems.Subsystem;
import kotlin.annotation.MustBeDocumented;

public class linkage implements Subsystem {
    public static final linkage INSTANCE = new linkage();

    private static Telemetry tele;
    private static DcMotorEx leftMotor;
    private static DcMotorEx rightMotor;

    private linkage() { }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @MustBeDocumented
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

    @Override
    public void preUserInitHook(@NonNull Wrapper opMode) {
        HardwareMap hMap = opMode.getOpMode().hardwareMap;
        tele = opMode.getOpMode().telemetry;

        leftMotor = hMap.get(DcMotorEx.class, "leftLinkage");
        rightMotor = hMap.get(DcMotorEx.class, "rightLinkage");
    }

    public static Lambda goUp(double upPower) {
        return new Lambda("power-outtake")
                .setInit(() -> {
                    leftMotor.setPower(-upPower);
                    rightMotor.setPower(upPower);
                })
                .setFinish(() -> true);
    }

    public static Lambda goDown(double downPower) {
        return new Lambda("power-outtake")
                .setInit(() -> {
                    leftMotor.setPower(downPower);
                    rightMotor.setPower(-downPower);
                })
                .setFinish(() -> true);
    }

    // all depending on what you need!
    // remember, you only need to write implementations for the hooks you actually use
    // the rest don't need to be added to the class, nice and clean

    //
    // Commands
    //
    // commands are the same as older mercurial!
    // lambda commands are once again, powerful tools for developing simple units of operation
//    @NonNull
//    public static Lambda simpleCommand() {
//        // we need to give commands names
//        // names help to give helpful error messages when something goes wrong in your command
//        // Mercurial will automatically rename your command to match the standard convention
//        // learn more about names and error messages in the names and messages overview
//        return new Lambda("simple")
//                .addRequirements(INSTANCE)
//                .setInit(() -> getMotor().setPower(0.4))
//                .setEnd(interrupted -> {
//                    if (!interrupted) getMotor().setPower(0.0);
//                });
//    }
//
//    // lambda commands have a new powerful extension, designed to work well with the Cell patterns in Dairy Util
//    // RefCell<Double> is an immutable reference with interior immutability
//    // we could also use a LazyCell, or an OpModeLazyCell, or SubsystemObjectCell, depending on our needs
//    // we need to manage the state ourselves, if we want to reset it at the start of each run of this command,
//    // or if its persistent across runs, we are in control
//    // note that, each copy of state is unique to each individual instance of this command
//    // if we wanted shared state across all instances, we could capture state from this class instead
//    // state can also be captured from the method itself, so StatefulLambdaCommand is not the only way to carry state
//    @NonNull
//    public static StatefulLambda<RefCell<Double>> statefulCommand() {
//        // once again, we need to give it a name
//        // learn more about names and error messages in the names and messages overview
//        return new StatefulLambda<>("stateful", new RefCell<>(0.0))
//                // note that stateful lambda commands have all the same methods that
//                // the regular lambda command has
//                // and variants that also take access to state where appropriate
//                .addRequirements(INSTANCE)
//                .setInit((state) -> getMotor().setPower(0.4 + state.get()))
//                // every time this command ends, we increase the power next time we run it
//                // this isn't a terribly practical example
//                // but this is useful for PID controllers and similar, without
//                // requiring the creation of a whole command class just to hold some state
//                .setEnd((interrupted, state) -> {
//                    if (!interrupted) getMotor().setPower(0);
//                    state.accept(state.get() + 0.1);
//                });
//    }
}
