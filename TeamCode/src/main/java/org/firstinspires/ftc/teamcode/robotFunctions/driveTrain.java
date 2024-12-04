package org.firstinspires.ftc.teamcode.robotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class driveTrain {
    private HardwareMap hwMap;
    private DcMotor frontLeft, frontRight, backLeft, backRight;

    public driveTrain(HardwareMap hwMap) {
        this.hwMap = hwMap;
        init();
    }

    private void init() {
        frontLeft = hwMap.get(DcMotor.class, "front_left");
        frontRight = hwMap.get(DcMotor.class, "front_right");
        backLeft = hwMap.get(DcMotor.class, "back_left");
        backRight = hwMap.get(DcMotor.class, "back_right");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    double speed = 0.65;
    public void drivePower(Gamepad gamepad) {
        boolean speedTrigger = gamepad.a;
        double extraSpeed = speedTrigger ? 0.15 : 0.0;
        double fullSpeed = speed + extraSpeed;

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