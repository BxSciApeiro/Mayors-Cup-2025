package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class motorEncoder extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor" );
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor" );

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();
        while (opModeIsActive()) {
            int positionleft = leftMotor.getCurrentPosition();
            int positionright = rightMotor.getCurrentPosition();
            double upPower = gamepad1.right_trigger * 0.75;
            double downPower = gamepad1.left_trigger * 0.75;

            if (gamepad1.cross) {
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                leftMotor.setTargetPosition(leftMotor.getCurrentPosition());
                rightMotor.setTargetPosition(rightMotor.getCurrentPosition());

            }

            leftMotor.setPower(-upPower + downPower);
            rightMotor.setPower(upPower + -downPower);
            telemetry.addData("Left Motor is at ", positionleft);
            telemetry.addData("Right Motor is at ", positionright);
            telemetry.addData("left mode is at", leftMotor.getMode());
            telemetry.addData("right mode ist at", rightMotor.getMode());
            telemetry.addData("To lock click X", true);
            telemetry.update();


        }
    }
}