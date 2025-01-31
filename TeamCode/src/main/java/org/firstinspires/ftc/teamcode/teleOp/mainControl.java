package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleOp.robotFunctions.robotControl;
import org.firstinspires.ftc.teamcode.teleOp.robotFunctions.linkageControl;


@TeleOp
public class mainControl extends LinearOpMode {
    robotControl bot = new robotControl();
    linkageControl linkage = new linkageControl(hardwareMap);

    @Override
    public void runOpMode() {
        bot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()) {
          bot.run(gamepad1);
          int leftPos = linkage.getPositionLeft();
          int rightPos = linkage.getPositionRight();
          telemetry.addData("left motor is ", leftPos);
          telemetry.addData("right motor is ", rightPos);
          telemetry.update();
        }
        telemetry.addData("initialized", true);
        telemetry.update();

    }
}
