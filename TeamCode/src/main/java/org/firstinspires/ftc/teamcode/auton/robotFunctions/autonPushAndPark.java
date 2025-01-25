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
public class autonPushAndPark extends LinearOpMode {
    public Pose2d initialPose;
    public Pose2d endPose;

    @Override
    public void runOpMode() {
        initialPose = new Pose2d(25, -65, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose); // Hardwaremap built into library (see MecanumDrive)

        Vector2d rightVector = new Vector2d(40  , -65);
        Vector2d ForwardVector = new Vector2d(40, -10);
        Vector2d block1right = new Vector2d(48, -10);
        Vector2d down1 = new Vector2d(50, -60);
        Vector2d block2right = new Vector2d(58, -10);
        Vector2d down2 = new Vector2d(60, -60);

        TrajectoryActionBuilder block1 = drive.actionBuilder(initialPose)
                .strafeTo(rightVector)
                .strafeTo(ForwardVector)
                .strafeTo(block1right)
                .strafeTo(down1)
                .strafeTo(block1right)
                .strafeTo(block2right)
                .strafeTo(down2);


        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(block1.build()));
        }
    }
}