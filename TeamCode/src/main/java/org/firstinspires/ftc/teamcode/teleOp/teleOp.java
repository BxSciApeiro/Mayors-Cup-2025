package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.subsystems.linkage;
import org.firstinspires.ftc.teamcode.util.constants.slides.linkageState;

import dev.frozenmilk.mercurial.Mercurial;

@TeleOp
public class teleOp extends OpMode {

    @Override
    public void init() {
        Mercurial.gamepad1().rightTrigger().conditionalBindState().greaterThan(0.05).bind().onTrue(linkage.INSTANCE.setState(linkageState.REST));
        Mercurial.gamepad1().leftTrigger().conditionalBindState().greaterThan(0.05).bind().onTrue(linkage.INSTANCE.setState(linkageState.WALL));
    }

    @Override
    public void loop() {
        telemetry.addData("currentPos: ", linkage.INSTANCE.getEncoder());
        telemetry.addData("targetPos: ", linkage.INSTANCE.getTargetPos());
        telemetry.update();
    }
}
