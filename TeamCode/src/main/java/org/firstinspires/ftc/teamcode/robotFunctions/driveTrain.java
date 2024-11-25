package org.firstinspires.ftc.teamcode.robotFunctions;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.mainControl;

public class driveTrain extends mainControl {
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private double speed = 0.65;

    public void runFunction() {
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            boolean speedTrigger = gamepad1.a;
            double extraSpeed = speedTrigger ? 0.15 : 0.0;
            double fullSpeed = speed + extraSpeed;

            double y = -gamepad1.left_stick_y * fullSpeed; // Forward/backward
            double x = gamepad1.left_stick_x * fullSpeed; // Strafe
            double rx = gamepad1.right_stick_x * fullSpeed; // Rotate

            double frontLeftPower = y + x + rx;
            double frontRightPower = y - x - rx;
            double backLeftPower = y - x + rx;
            double backRightPower = y + x - rx;

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);

            telemetry.addData("speedTrigger", speedTrigger);
            telemetry.addData("ySpeed", y);
            telemetry.addData("xSpeed", x);
            telemetry.addData("rxSpeed", rx);
            telemetry.update();
        }
    }
}

