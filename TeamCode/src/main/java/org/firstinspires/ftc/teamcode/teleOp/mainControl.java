package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleOp.robotFunctions.robotControl;


@TeleOp
public class mainControl extends LinearOpMode {
    robotControl bot = new robotControl();

    @Override
    public void runOpMode() {
        bot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()) {
          bot.run(gamepad1);
        }
        telemetry.addData("initialized", true);
        telemetry.update();

    }
}
