package org.firstinspires.ftc.teamcode.auton.robotFunctions;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.teleOp.mainControl.servoState;
import org.firstinspires.ftc.teamcode.auton.MecanumDrive;
import org.firstinspires.ftc.teamcode.teleOp.robotFunctions.linkageControl;
import org.firstinspires.ftc.teamcode.teleOp.robotFunctions.servoClaw;

@Autonomous
public class testLinkageClaw extends LinearOpMode {
    public Pose2d initialPose;

    @Override
    public void runOpMode() {
        initialPose = new Pose2d(25, -65, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        linkageControl linkage = new linkageControl(hardwareMap, telemetry);
        servoClaw claw = new servoClaw(hardwareMap, telemetry);

        Vector2d endVector = new Vector2d(75  , -65);

        TrajectoryActionBuilder park = drive.actionBuilder(initialPose)
                .strafeTo(endVector);

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(
//                    linkage.autoMove(1000),
                    claw.autoMove(servoState.OPEN)
            ));
            telemetry.update();
        }
    }
}



