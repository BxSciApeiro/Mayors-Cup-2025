package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.util.constants.slides.linkageState;

import dev.frozenmilk.mercurial.Mercurial;
import dev.frozenmilk.mercurial.bindings.BoundGamepad;

@Mercurial.Attach
@Linkage.Attach
@TeleOp
public class DairyTesting extends OpMode {
    @Override
    public void init() {
        BoundGamepad Driver = Mercurial.gamepad1();
        Driver.a().onTrue(Linkage.INSTANCE.setState(linkageState.REST));
        Driver.b().onTrue(Linkage.INSTANCE.setState(linkageState.WALL));
    }

    @Override
    public void loop() {
        telemetry.addData("currentPos: ", Linkage.INSTANCE.getEncoder());
        telemetry.addData("targetPos: ", Linkage.INSTANCE.getTargetPos());
        telemetry.update();
    }
}
