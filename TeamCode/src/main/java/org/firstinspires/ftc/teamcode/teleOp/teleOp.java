package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.util.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.util.constants.slides.linkageState;

import dev.frozenmilk.dairy.core.util.features.BulkRead;
import dev.frozenmilk.mercurial.Mercurial;

@Mercurial.Attach
@Linkage.Attach
@BulkRead.Attach
public class teleOp extends OpMode {
    @Override
    public void init() {
        Mercurial.gamepad1().rightTrigger().conditionalBindState().greaterThan(0.05).bind().onTrue(Linkage.INSTANCE.setState(linkageState.REST));
        Mercurial.gamepad1().leftTrigger().conditionalBindState().greaterThan(0.05).bind().onTrue(Linkage.INSTANCE.setState(linkageState.WALL));
    }

    @Override
    public void loop() {
        telemetry.addData("currentPos: ", Linkage.INSTANCE.getEncoder());
        telemetry.addData("targetPos: ", Linkage.INSTANCE.getTargetPos());
        telemetry.update();
    }
}
