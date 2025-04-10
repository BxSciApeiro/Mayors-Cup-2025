package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.subsystems.Claw;
import org.firstinspires.ftc.teamcode.util.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.util.constants.claw.clawState;

import dev.frozenmilk.mercurial.Mercurial;
import dev.frozenmilk.mercurial.bindings.BoundGamepad;

@Mercurial.Attach
@Claw.Attach
@Linkage.Attach
@TeleOp
public class DairyTesting extends OpMode {
    @Override
    public void init() {
        BoundGamepad Driver = Mercurial.gamepad1();
        Driver.b().onTrue(Claw.INSTANCE.setState(clawState.OPEN));
        Driver.a().onTrue(Claw.INSTANCE.setState(clawState.CLOSED));
    }

    @Override
    public void loop() {
        telemetry.addData("currentPos: ", Linkage.INSTANCE.getEncoder());
        telemetry.addData("targetPos: ", Linkage.INSTANCE.getTargetPos());
        telemetry.update();
    }
}
