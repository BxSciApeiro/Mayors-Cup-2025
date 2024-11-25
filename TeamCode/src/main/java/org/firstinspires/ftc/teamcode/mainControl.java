package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotFunctions.driveTrain;
import org.firstinspires.ftc.teamcode.robotFunctions.linkage;
import org.firstinspires.ftc.teamcode.robotFunctions.servoIntake;

@TeleOp
public class mainControl extends LinearOpMode {
    @Override
    public void runOpMode() {
        driveTrain train = new driveTrain();
        linkage linkage = new linkage();
        servoIntake intake = new servoIntake();
        waitForStart();


        while (opModeIsActive()) {
            train.runFunction();
            linkage.runFunction();
            intake.runFunction();
        }

    }
}
