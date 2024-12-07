package org.firstinspires.ftc.teamcode.robotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class driveTrain {
    private HardwareMap hwMap;

    public driveTrain(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    double speed = 0.65;
    public void drivePower(Gamepad gamepad) {
        DcMotor frontLeft = hwMap.get(DcMotor.class, "front_left");
        DcMotor frontRight = hwMap.get(DcMotor.class, "front_right");
        DcMotor backLeft = hwMap.get(DcMotor.class, "back_left");
        DcMotor backRight = hwMap.get(DcMotor.class, "back_right");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        double fullSpeed = speed;

        double y = -gamepad.left_stick_y * fullSpeed;
        double x = gamepad.left_stick_x * fullSpeed;
        double rx = gamepad.right_stick_x * fullSpeed;

        double frontLeftPower = y + x + rx;
        double frontRightPower = y - x - rx;
        double backLeftPower = y - x + rx;
        double backRightPower = y + x - rx;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }
}