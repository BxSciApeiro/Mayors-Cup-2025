package org.firstinspires.ftc.teamcode.auton.robotFunctions;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.auton.MecanumDrive;

@Autonomous
public class rightPark extends LinearOpMode {
    public Pose2d initialPose;

    @Override
    public void runOpMode() {
        initialPose = new Pose2d(25, -65, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Vector2d endVector = new Vector2d(75  , -65);

        TrajectoryActionBuilder park = drive.actionBuilder(initialPose)
                .strafeTo(endVector);

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(park.build()));
        }
    }
}

