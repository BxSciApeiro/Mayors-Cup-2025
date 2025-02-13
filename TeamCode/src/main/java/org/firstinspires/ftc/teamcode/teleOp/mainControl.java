package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleOp.robotFunctions.robotControl;

@TeleOp
public class mainControl extends LinearOpMode {
    robotControl bot = new robotControl();
    public enum servoState {
        OPEN,
        CLOSED
    }

    @Override
    public void runOpMode() {
        bot.init(hardwareMap, telemetry);
        waitForStart();
        while(opModeIsActive()) {
          bot.run(gamepad1);
          telemetry.update();
        }
        telemetry.addData("initialized", true);
        telemetry.update();
    }
}
