package org.firstinspires.ftc.teamcode.teleOp;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
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
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.teleOp.robotFunctions.linkageControl;

@Autonomous
public class autonFullMaybe extends LinearOpMode {
    public Pose2d initialPose;
    public Pose2d endPose;

    public class LinkageAuto {
        private DcMotor leftMotor;
        private DcMotor rightMotor;

        public LinkageAuto(HardwareMap hardwareMap) {
            leftMotor =  hardwareMap.get(DcMotor.class, "leftMotor");
            rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        }
        public class moveUp implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                leftMotor.setPower(-0.35);
                rightMotor.setPower(0.35);
                sleep(1000);
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

    @Override
    public void runOpMode() {
        initialPose = new Pose2d(0, 0, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        LinkageAuto linkage = new LinkageAuto(hardwareMap);// Hardwaremap built into library (see MecanumDrive)

        Vector2d leftVector = new Vector2d(35, 25);
        Vector2d ForwardVector = new Vector2d(35, 0);// should move left

        TrajectoryActionBuilder gotoPoles = drive.actionBuilder(initialPose)
                .strafeTo(ForwardVector).strafeTo(leftVector);

        waitForStart();
        if (opModeIsActive()) {
            Actions.runBlocking(
                    new SequentialAction(
                            gotoPoles.build(),
                            linkage.moveUp(),
                            linkage.moveDown()));

        }
    }
}

