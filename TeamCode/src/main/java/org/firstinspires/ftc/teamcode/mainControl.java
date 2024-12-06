package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotFunctions.Control;

@TeleOp
public class mainControl extends LinearOpMode {
    Control bot = new Control(gamepad1);

    @Override
    public void runOpMode() {
        bot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()) {
          bot.run();
        }
        telemetry.addData("initialized", true);
        telemetry.update();
    }
}
