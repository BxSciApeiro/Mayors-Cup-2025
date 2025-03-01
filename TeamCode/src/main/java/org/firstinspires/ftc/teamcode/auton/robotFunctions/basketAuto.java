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
public class basketAuto extends LinearOpMode {
    public Pose2d initialPose;
    public static int frontY = -5;
    public static int initialX = 20;
    public static int initialY = -65;

    public static Vector2d reflect(Vector2d a) {
        double reflectedx = -a.x;
        return new Vector2d(reflectedx, a.y);
    }
    @Override
    public void runOpMode() {
        initialPose = new Pose2d(initialX, initialY, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Vector2d sampleOnePush = new Vector2d(initialX + 30, initialY + 20);
        Vector2d basket = new Vector2d(-60, -60);
        Vector2d basketOut = new Vector2d(-60, -40);
        Vector2d toSamplesOne = new Vector2d(initialX + 5, initialY + 30);
        Vector2d toSamplesTwo = new Vector2d(initialX + 10, frontY);
        Vector2d LeftToSamplesTwo = new Vector2d(-35, -40);




        TrajectoryActionBuilder testBakset = drive.actionBuilder(initialPose)                        .setTangent(Math.toRadians(90))
                .splineTo(basket, Math.toRadians(270))
                .strafeTo(basketOut)
                .setTangent(0)
                .strafeTo(LeftToSamplesTwo)
                .splineToConstantHeading(reflect(toSamplesTwo), Math.toRadians(90))
                .splineToConstantHeading(reflect(sampleOnePush), Math.toRadians(90));


        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(testBakset.build()));
        }
    }
}

