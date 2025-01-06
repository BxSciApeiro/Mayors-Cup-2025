package org.firstinspires.ftc.teamcode.teleOp;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.Vector2d;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous
public class autonLeftDrive extends LinearOpMode {
    public Pose2d initialPose;
    public Pose2d endPose;

    @Override
    public void runOpMode() {
        initialPose = new Pose2d(0, 0, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose); // Hardwaremap built into library (see MecanumDrive)

        Vector2d endVector = new Vector2d(0, -50);   // should move left

        TrajectoryActionBuilder park = drive.actionBuilder(initialPose)
                .strafeTo(endVector);

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(park.build()));
        }
    }
}

