package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotFunctions.Control;

@TeleOp
public class mainControl extends LinearOpMode {
    Control bot = new Control(telemetry);

    @Override
    public void runOpMode() {
        bot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()) {
//          bot.runOne(gamepad1);
          bot.runTwo(gamepad2);
        }
        telemetry.addData("initialized", true);
        telemetry.update();
    }
}
