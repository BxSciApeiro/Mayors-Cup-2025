package org.firstinspires.ftc.teamcode.auton.robotFunctions;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
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
    public Pose2d currentPose;
    int initialX = 20;
    int initialY = -65;
    int frontY = 10;

    @Override
    public void runOpMode() {
        Vector2d initial2d = new Vector2d(initialX, initialY);
        currentPose = new Pose2d(initial2d, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, currentPose);

        linkageControl linkage = new linkageControl(hardwareMap, telemetry);
        servoClaw claw = new servoClaw(hardwareMap, telemetry);

        Vector2d specPos = new Vector2d(initialX - 25, initialY + 30);
        Vector2d specPush = new Vector2d(initialX - 25, initialY + 40);
        Vector2d specBack = new Vector2d(initialX - 25, initialY + 25);

        Vector2d toSamplesOne = new Vector2d(initialX + 5, initialY + 30);
        Vector2d toSamplesTwo = new Vector2d(initialX + 5, frontY);
        Vector2d toSamplesThree = new Vector2d(initialX + 28, frontY);
        Vector2d sampleOnePush = new Vector2d(initialX + 28, initialY + 20);
        Vector2d sampleOneReturn = new Vector2d(initialX + 28, frontY);
        Vector2d sampleOneToSampleTwo = new Vector2d(initialX + 46, frontY);
        Vector2d sampleTwoPush = new Vector2d(initialX + 46, initialY + 20);
        Vector2d specLineUpOne = new Vector2d(initialX + 40, initialY + 25);
        Vector2d specLineUpTwo = new Vector2d(initialX + 35, initialY + 25);
        Vector2d specLineUpThree = new Vector2d(initialX + 35, initialY - 1);
        Vector2d specLineUpFour = new Vector2d(initialX + 35, initialY + 25);

        Vector2d specTwoPos = new Vector2d(initialX - 10, initialY + 30);
        Vector2d specTwoPush = new Vector2d(initialX - 10, initialY + 40);
        Vector2d specTwoBack = new Vector2d(initialX - 10, initialY + 25);

        Vector2d park = new Vector2d(initialX + 20, initialY + 7);

        TrajectoryActionBuilder moveOne = drive.actionBuilder(currentPose)
                .splineToConstantHeading(specPos, Math.toRadians(90));

        TrajectoryActionBuilder moveForward = drive.actionBuilder(new Pose2d(specPos, Math.toRadians(90)))
                .strafeTo(specPush);

        TrajectoryActionBuilder moveTwo = drive.actionBuilder(new Pose2d(specPush, Math.toRadians(90)))
                .strafeTo(specBack)
                .splineToConstantHeading(toSamplesOne, Math.toRadians(5))
                .splineToConstantHeading(toSamplesTwo, Math.toRadians(350))
                .splineToConstantHeading(toSamplesThree, Math.toRadians(270))
                .strafeTo(sampleOnePush)
                .splineToConstantHeading(sampleOneReturn, Math.toRadians(90), new TranslationalVelConstraint(150))
                .splineToConstantHeading(sampleOneToSampleTwo, Math.toRadians(270))
                .splineToConstantHeading(sampleTwoPush, Math.toRadians(90), new TranslationalVelConstraint(150));

        TrajectoryActionBuilder moveThree = drive.actionBuilder(new Pose2d(sampleTwoPush, Math.toRadians(90)))
                .strafeTo(specLineUpOne)
                .setTangent(Math.toRadians(90))
                .splineTo(specLineUpTwo, Math.toRadians(270))
                .strafeTo(specLineUpThree);

        TrajectoryActionBuilder moveFour = drive.actionBuilder(new Pose2d(specLineUpThree, Math.toRadians(-90)))
                .setTangent(Math.toRadians(-90))
                .splineTo(specTwoPos, Math.toRadians(90))
                .strafeTo(specTwoPush);

        TrajectoryActionBuilder moveFive = drive.actionBuilder(new Pose2d(specTwoPush, Math.toRadians(90)))
                .strafeTo(specTwoBack)
                .splineToConstantHeading(park, Math.toRadians(270), new TranslationalVelConstraint(100));

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(
                    new ParallelAction(
                            claw.autoMove(servoState.CLOSED),
                            linkage.autoMove(5300),
                            moveOne.build()
                    ),
                    moveForward.build(),
                    linkage.autoMove(4000),
                    claw.autoMove(servoState.OPEN),
                    new ParallelAction(
                            linkage.autoMove(100),
                            moveTwo.build()
                    ),
                    new ParallelAction(
                            linkage.autoMove(1000),
                            moveThree.build()
                    ),
                    claw.autoMove(servoState.CLOSED),
                    new ParallelAction(
                            moveFour.build(),
                            linkage.autoMove(5300)
                    )
                    //linkage.autoMove(100),
                    //claw.autoMove(servoState.OPEN),
                    //moveFive.build()
            ));
            telemetry.update();
        }
    }
}



