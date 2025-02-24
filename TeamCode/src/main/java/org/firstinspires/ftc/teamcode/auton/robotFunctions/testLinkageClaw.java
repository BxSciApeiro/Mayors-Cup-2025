package org.firstinspires.ftc.teamcode.auton.robotFunctions;

import com.acmerobotics.roadrunner.ParallelAction;
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
    public Pose2d currentPose;
    int initialX = 15;
    int initialY = -65;
    int frontY = -15;

    @Override
    public void runOpMode() {
        Vector2d initial2d = new Vector2d(initialX, initialY);
        currentPose = new Pose2d(initial2d, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, currentPose);

        linkageControl linkage = new linkageControl(hardwareMap, telemetry);
        servoClaw claw = new servoClaw(hardwareMap, telemetry);

        Vector2d specPos = new Vector2d(initialX - 10, initialY + 30);
        Vector2d spec2Pos = new Vector2d(initialX -15, initialY + 30);
        Vector2d specPush = new Vector2d(initialX - 10, initialY + 40);
        Vector2d specBack = new Vector2d(initialX - 10, initialY + 25);

        Vector2d sampleSplineOne = new Vector2d(initialX + 19, initialY + 31);
        Vector2d rightSampleOne = new Vector2d(initialX+25, initialY+31);
        Vector2d sampleSplineTwo = new Vector2d(initialX + 20, frontY);
        Vector2d sampleOneSpline = new Vector2d(initialX + 40, frontY+10);
        Vector2d sampleOnePush = new Vector2d(initialX + 40, initialY + 5);
        Vector2d sampleOneBack = new Vector2d(initialX + 35, frontY+10);
        Vector2d sampleTwoSpline = new Vector2d(initialX + 50, frontY+10);
        Vector2d sampleTwoPush = new Vector2d(initialX + 50, initialY + 5);
        Vector2d specLineUp = new Vector2d(initialX+40, initialY+20);
        Vector2d spec = new Vector2d(initialX+40, initialY-5);
        Vector2d specGrabBack = new Vector2d(initialX+40, initialY+10);

        Vector2d specPosTwo = new Vector2d(initialX + 25, initialY + 5);
        Vector2d specTwoGet = new Vector2d(initialX + 25, initialY + 3);
        Vector2d park = new Vector2d(initialX + 25, initialY + 7);

        TrajectoryActionBuilder oneMove = drive.actionBuilder(currentPose)
                .splineToConstantHeading(specPos, Math.toRadians(90));

        TrajectoryActionBuilder forwardMove = drive.actionBuilder(new Pose2d (specPos, Math.toRadians(90)))
                .strafeTo(specPush);

        TrajectoryActionBuilder twoMove = drive.actionBuilder(new Pose2d(specPush, Math.toRadians(90)))
                .strafeTo(specBack);

        TrajectoryActionBuilder threeMove = drive.actionBuilder(new Pose2d(specBack, Math.toRadians(90)))
                .splineToConstantHeading(sampleSplineOne, Math.toRadians(5))
                .splineToConstantHeading(sampleOneSpline, Math.toRadians(350))
                .strafeTo(sampleOnePush)
                .strafeTo(sampleOneBack)
                .splineToConstantHeading(sampleTwoSpline, Math.toRadians(270))
                .splineToConstantHeading(sampleTwoPush, Math.toRadians(90))
                .splineTo(specLineUp, Math.toRadians(270))
                .strafeTo(spec);

        TrajectoryActionBuilder fourMove = drive.actionBuilder(new Pose2d(spec, Math.toRadians(-90)))
                .strafeTo(specGrabBack)
                .setTangent(Math.toRadians(-90))
                .splineTo(spec2Pos, Math.toRadians(90));


        TrajectoryActionBuilder fiveMove = drive.actionBuilder(new Pose2d(specPush, Math.toRadians(90)))
                .strafeTo(specBack)
                .strafeTo(park);

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(
                    new ParallelAction(
                            claw.autoMove(servoState.CLOSED),
                            linkage.autoMove(5500),
                            oneMove.build()
                    ),
                    forwardMove.build(),

                    linkage.autoMove(4100),
                    claw.autoMove(servoState.OPEN),

                    new ParallelAction(
                            twoMove.build(),
                            linkage.autoMove(1000)
                    ),
                    threeMove.build(),
                    claw.autoMove(servoState.CLOSED),
                    new ParallelAction(
                            fourMove.build(),
                            linkage.autoMove(5500)
                    ),
                    linkage.autoMove(4100),
                    claw.autoMove(servoState.OPEN)
                    )
            );
            telemetry.update();
        }
    }
}



