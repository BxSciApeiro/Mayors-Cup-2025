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
    public Pose2d currentPose;
    public int initialX = 25;
    public int initialY = -65;

    @Override
    public void runOpMode() {
        Vector2d initial2d = new Vector2d(initialX, initialY);
        currentPose = new Pose2d(initial2d, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, currentPose);

        linkageControl linkage = new linkageControl(hardwareMap, telemetry);
        servoClaw claw = new servoClaw(hardwareMap, telemetry);

        Vector2d firstLeft = new Vector2d(initialX - 15, initialY);
        Vector2d rightForward = new Vector2d(initialX - 15, initialY + 25);
        Vector2d moreForward = new Vector2d(initialX - 15, initialY + 31);
        Vector2d backward = new Vector2d(initialX - 15, initialY + 25);

        Vector2d leftMove = new Vector2d(initialX + 18, initialY + 25);
        Vector2d leftForward = new Vector2d(initialX + 18, initialY + 50);
        Vector2d leftMoveTwo = new Vector2d(initialX + 28, initialY + 50);
        Vector2d backLeftMove = new Vector2d(initialX + 28, initialY + 5);
        Vector2d frontMove = new Vector2d(initialX + 28, initialY + 50);
        Vector2d frontLeftTwo = new Vector2d(initialX + 38, initialY + 50);
        Vector2d backLeft = new Vector2d(initialX + 38, initialY + 5);
        Vector2d frontMoveTwo = new Vector2d(initialX + 38, initialY + 2);

        TrajectoryActionBuilder oneMove = drive.actionBuilder(currentPose)
                .strafeTo(firstLeft)
                .strafeTo(rightForward);

        TrajectoryActionBuilder twoMove = drive.actionBuilder(new Pose2d(rightForward, Math.toRadians(90)))
                .strafeTo(moreForward);

        TrajectoryActionBuilder threeMove = drive.actionBuilder(new Pose2d(moreForward, Math.toRadians(90)))
                .strafeTo(backward);

        TrajectoryActionBuilder fourMove = drive.actionBuilder(new Pose2d(backward, Math.toRadians(90)))
                .strafeTo(leftMove)
                .strafeTo(leftForward)
                .strafeTo(leftMoveTwo)
                .strafeTo(backLeftMove)
                .strafeTo(frontMove)
                .strafeTo(frontLeftTwo)
                .strafeTo(backLeft)
                .strafeTo(frontMoveTwo);

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(
                    claw.autoMove(servoState.CLOSED),
                    oneMove.build(),
                    linkage.autoMove(5500),
                    twoMove.build(),
                    linkage.autoMove(4200),//5600 -> 5200
                    claw.autoMove(servoState.OPEN),
                    threeMove.build(),
                    linkage.autoMove(50),
                    fourMove.build()
            ));
            telemetry.update();
        }
    }
}



