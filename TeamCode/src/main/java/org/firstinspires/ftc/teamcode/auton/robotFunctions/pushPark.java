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
public class pushPark extends LinearOpMode {
    public Pose2d initialPose;
    public static int frontY = -15;
    public static int backY = -60;
    public static int initialX = 48;

    @Override
    public void runOpMode() {
        initialPose = new Pose2d(25, -65, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose); // Hardwaremap built into library (see MecanumDrive)

        Vector2d rightPos = new Vector2d(initialX, -65);
        Vector2d forwardPos = new Vector2d(initialX, frontY);
        Vector2d blockRightPos = new Vector2d(initialX + 13, frontY);
        Vector2d blockDownPos = new Vector2d(initialX + 13, backY);
        Vector2d blockUpPos = new Vector2d(initialX + 13, frontY);
        Vector2d blockRightPos2 = new Vector2d(initialX + 25, frontY);
        Vector2d blockDownPos2 = new Vector2d(initialX + 25, backY);
        Vector2d finalForward = new Vector2d(initialX + 25, backY + 2);

        TrajectoryActionBuilder block1 = drive.actionBuilder(initialPose)
                .strafeTo(rightPos)
                .strafeTo(forwardPos)
                .strafeTo(blockRightPos)
                .strafeTo(blockDownPos)
                .strafeTo(blockUpPos)
                .strafeTo(blockRightPos2)
                .strafeTo(blockDownPos2)
                .strafeTo(finalForward);

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(block1.build()));
        }
    }
}