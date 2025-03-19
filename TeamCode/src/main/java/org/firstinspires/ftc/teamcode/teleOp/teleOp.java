package org.firstinspires.ftc.teamcode.teleOp;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import dev.frozenmilk.mercurial.Mercurial;

@TeleOp(name = "Teleop", group = "Teleop")
@Config
public class teleOp extends OpMode {
    @Override
    public void init() {
        Mercurial.gamepad1().rightTrigger());
        Mercurial.gamepad2().leftTrigger());
    }
    @Override
    public void loop() {
        telemetry.addData("please: ", "hi");
        telemetry.update();
    }
}
