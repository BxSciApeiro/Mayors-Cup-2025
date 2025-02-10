package org.firstinspires.ftc.teamcode.auton.robotFunctions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.auton.MecanumDrive;

import java.util.Vector;

@Autonomous
public class autonFullMaybe extends LinearOpMode {
    public Pose2d initialPose;
    public Pose2d secondPose;

    public class LinkageAuto {
        private DcMotor leftMotor;
        private DcMotor rightMotor;

        public LinkageAuto(HardwareMap hardwareMap) {
            leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
            rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        }

        public class moveUp implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                leftMotor.setPower(-0.15);
                rightMotor.setPower(0.15);
                sleep(500);
                leftMotor.setPower(0);
                rightMotor.setPower(0);
                sleep(4000); //TODO COMMENT THIS OUT, PURELY FOR TESTING
                return false;
            }
        }

        public Action moveUp() {
            return new moveUp();
        }

        public class moveDown implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                leftMotor.setPower(0.35);
                rightMotor.setPower(-0.35);
                sleep(1000);
                leftMotor.setPower(0);
                rightMotor.setPower(0);
                sleep(4000); //TODO COMMENT THIS OUT, PURELY FOR TESTING
                return false;
            }
        }

        public Action moveDown() {
            return new moveDown();
        }
    }
        public class ClawAuto {
            private Servo claw;

            public ClawAuto(HardwareMap hardwareMap) {
                claw =  hardwareMap.get(Servo.class, "claw");
            }
            public class close implements Action {
                @Override
                public boolean run(@NonNull TelemetryPacket packet) {
                    claw.setPosition(0.9);
                    return false;
                }
            }
            public Action close() {
                return new close();
            }

            public class open implements Action {
                @Override
                public boolean run(@NonNull TelemetryPacket packet) {
                    claw.setPosition(0.7);
                    return false;
                }
            }
            public Action open() {
                return new open();
            }



        }

    @Override
    public void runOpMode() {
        initialPose = new Pose2d(25, -65, Math.toRadians(90));
        secondPose = new Pose2d(0, -40, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        LinkageAuto linkage = new LinkageAuto(hardwareMap);
        ClawAuto claw = new ClawAuto(hardwareMap); // Hardwaremap built into library (see MecanumDrive)

        Vector2d Poles = new Vector2d(0, -40);// should move left
        Vector2d Forward = new Vector2d(0, -30);

        TrajectoryActionBuilder gotoPoles = drive.actionBuilder(initialPose)
                .strafeTo(Poles);
        TrajectoryActionBuilder forward = drive.actionBuilder(secondPose).strafeTo(Forward);

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(
                    new SequentialAction(
                            gotoPoles.build(),
                            linkage.moveUp(),
                           // forward.build(),
                            claw.open(),
                            linkage.moveDown()));

        }
    }
}

