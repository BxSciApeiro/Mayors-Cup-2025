package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class mainControl extends LinearOpMode {
    org.firstinspires.ftc.teamcode.robotFunctions.mainControl bot = new org.firstinspires.ftc.teamcode.robotFunctions.mainControl();

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
