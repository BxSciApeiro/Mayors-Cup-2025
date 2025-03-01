package org.firsdonepires.ftc.teamcode.auton.robotFunctions;

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
    int frontY = 8;

    @Override
    public void runOpMode() {
        Vector2d initial2d = new Vector2d(initialX, initialY);
        currentPose = new Pose2d(initial2d, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, currentPose);

        linkageControl linkage = new linkageControl(hardwareMap, telemetry);
        servoClaw claw = new servoClaw(hardwareMap, telemetry);

        Vector2d specPos = new Vector2d(initialX - 25, initialY + 41);
        Vector2d specBack = new Vector2d(initialX - 25, initialY + 25);

        Vector2d toSamplesOne = new Vector2d(initialX + 5, initialY + 30);
        Vector2d toSamplesTwo = new Vector2d(initialX + 15, frontY);
        Vector2d toSamplesThree = new Vector2d(initialX + 32, frontY);
        Vector2d sampleOnePush = new Vector2d(initialX + 32, initialY + 24);
        Vector2d sampleOneReturn = new Vector2d(initialX + 32, frontY);
        Vector2d sampleOneToSampleTwo = new Vector2d(initialX + 49, frontY + 10);
        Vector2d sampleTwoPush = new Vector2d(initialX + 49, initialY + 32);
        Vector2d specLineUpOne = new Vector2d(initialX + 35, initialY + 32);
        Vector2d specLineUpTwo = new Vector2d(initialX + 35, initialY + 3);

        Vector2d specTwoPosOne = new Vector2d(initialX + 35, initialY + 20);
        Vector2d specTwoPosTwo = new Vector2d(initialX - 20, frontY - 1);
        Vector2d specTwoBack = new Vector2d(initialX - 20, frontY - 10);

        Vector2d park = new Vector2d(initialX + 30, initialY + 40);

        TrajectoryActionBuilder moveOne = drive.actionBuilder(currentPose)
                .splineToConstantHeading(specPos, Math.toRadians(90), new TranslationalVelConstraint(30));

        TrajectoryActionBuilder moveTwo = drive.actionBuilder(new Pose2d(specPos, Math.toRadians(90)))
                .strafeTo(specBack)
                .splineToConstantHeading(toSamplesOne, Math.toRadians(5))
                .splineToConstantHeading(toSamplesTwo, Math.toRadians(90))
                .splineToConstantHeading(toSamplesThree, Math.toRadians(270))
                .splineToConstantHeading(sampleOnePush, Math.toRadians(90), new TranslationalVelConstraint(100))
                .splineToConstantHeading(sampleOneReturn, Math.toRadians(90), new TranslationalVelConstraint(100))
                .splineToConstantHeading(sampleOneToSampleTwo, Math.toRadians(270))
                .splineToConstantHeading(sampleTwoPush, Math.toRadians(90), new TranslationalVelConstraint(100));

        TrajectoryActionBuilder moveThree = drive.actionBuilder(new Pose2d(sampleTwoPush, Math.toRadians(90)))
                .setTangent(Math.toRadians(90))
                .splineTo(specLineUpOne, Math.toRadians(270))
                .strafeTo(specLineUpTwo);

        TrajectoryActionBuilder moveFour = drive.actionBuilder(new Pose2d(specLineUpTwo, Math.toRadians(-90)))
                .strafeTo(specTwoPosOne)
                .setTangent(Math.toRadians(-90))
                .splineTo(specTwoPosTwo, Math.toRadians(90), new TranslationalVelConstraint(100));

        TrajectoryActionBuilder moveFive = drive.actionBuilder(new Pose2d(specTwoPosTwo, Math.toRadians(90)))
                .strafeTo(specTwoBack)
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(park, Math.toRadians(270), new TranslationalVelConstraint(100));

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(
                    new ParallelAction(
                            linkage.autoMove(5300, 1.5),
                            claw.autoMove(servoState.CLOSED),
                            moveOne.build()
                    ),
                    linkage.autoMove(4000, 1.5),
                    claw.autoMove(servoState.OPEN),
                    new ParallelAction(
                            linkage.autoMove(100, 1),
                            moveTwo.build()
                    ),
                    new ParallelAction(
                            linkage.autoMove(900, 1),
                            moveThree.build()
                    ),
                    claw.autoMove(servoState.CLOSED),
                    new ParallelAction(
                            moveFour.build(),
                            linkage.autoMove(5300, 1)
                    ),
                    linkage.autoMove(4000, 1.5),
                    claw.autoMove(servoState.OPEN),
                    new ParallelAction(
                        linkage.autoMove(100, 1),
                        moveFive.build()
                    )
            ));
            telemetry.update();
        }
    }
}



