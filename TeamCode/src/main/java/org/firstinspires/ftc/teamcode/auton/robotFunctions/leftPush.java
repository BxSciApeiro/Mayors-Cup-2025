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
public class leftPush extends LinearOpMode {
    public Pose2d initialPose;
    public static int frontY = -5;
    public static int backY = -60;
    public static int initialX = -36;

    @Override
    public void runOpMode() {
        initialPose = new Pose2d(initialX, -65, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Vector2d forwardMove = new Vector2d(initialX, frontY);
        Vector2d leftMove = new Vector2d(initialX - 20, frontY);
        Vector2d downMove = new Vector2d(initialX - 20, backY + 2);
        Vector2d forwardMoveTwo = new Vector2d(initialX - 20, frontY);
        Vector2d leftMoveTwo = new Vector2d(initialX - 30, frontY);
        Vector2d downMoveTwo = new Vector2d(initialX - 30, backY + 2);

        TrajectoryActionBuilder park = drive.actionBuilder(initialPose)
                .strafeTo(forwardMove)
                .strafeTo(leftMove)
                .strafeTo(downMove)
                .strafeTo(forwardMoveTwo)
                .strafeTo(leftMoveTwo)
                .strafeTo(downMoveTwo);

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(park.build()));
        }
    }
}

