package org.firstinspires.ftc.teamcode.auton.robotFunctions;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.auton.MecanumDrive;

@Autonomous
public class leftPush extends LinearOpMode {
    public Pose2d initialPose;
    int initialX = -36;
    int initialY = -65;
    int frontY = -2;
    int backY = -55;

    @Override
    public void runOpMode() {
        initialPose = new Pose2d(initialX, initialY, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Vector2d forwardMove = new Vector2d(initialX, frontY);
        Vector2d toSampleOne = new Vector2d(initialX - 18, frontY);
        Vector2d sampleOnePush = new Vector2d(initialX - 18, backY);
        Vector2d sampleOneReturn = new Vector2d(initialX - 18, frontY);
        Vector2d toSampleTwo = new Vector2d(initialX - 32, frontY);
        Vector2d sampleTwoPush = new Vector2d(initialX - 32, backY);

        TrajectoryActionBuilder park = drive.actionBuilder(initialPose)
                .splineToConstantHeading(forwardMove, Math.toRadians(90), new TranslationalVelConstraint(100))
                .splineToConstantHeading(toSampleOne, Math.toRadians(270))
                .splineToConstantHeading(sampleOnePush, Math.toRadians(90), new TranslationalVelConstraint(100))
                .splineToConstantHeading(sampleOneReturn, Math.toRadians(90), new TranslationalVelConstraint(100))
                .splineToConstantHeading(toSampleTwo, Math.toRadians(270))
                .splineToConstantHeading(sampleTwoPush, Math.toRadians(90));

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(park.build()));
        }
    }
}

