package org.firstinspires.ftc.teamcode.auton.robotFunctions;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.auton.MecanumDrive;
import org.firstinspires.ftc.teamcode.teleOp.robotFunctions.linkageControl;

@Disabled
@Autonomous
public class basketAuto extends LinearOpMode {
    public Pose2d initialPose;
    int initialX = -36;
    int initialY = -65;
    int frontY = -5;

    @Override
    public void runOpMode() {
        Vector2d initial2d = new Vector2d(initialX, initialY);
        initialPose = new Pose2d(initial2d, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        linkageControl linkage = new linkageControl(hardwareMap, telemetry);

        Vector2d splineToBasket = new Vector2d(initialX - 32, initialY + 1);

        TrajectoryActionBuilder toBasket = drive.actionBuilder(initialPose)
                .setTangent(Math.toRadians(90))
                .splineTo(splineToBasket, Math.toRadians(220), new TranslationalVelConstraint(100));

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(
                    new ParallelAction(
                            toBasket.build(),
                            linkage.autoMove(6000)
                    )
            ));
        }
    }
}

