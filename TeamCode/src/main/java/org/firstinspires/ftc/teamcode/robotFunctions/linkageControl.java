package org.firstinspires.ftc.teamcode.robotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class linkageControl {
    private HardwareMap hwMap;
    private ElapsedTime elapsedTime = null;
    private double seconds = 0.0;

    public linkageControl(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void move(Gamepad gamepad) {
        DcMotor leftMotor = hwMap.get(DcMotor.class, "leftMotor");
        DcMotor rightMotor = hwMap.get(DcMotor.class, "rightMotor");

        double maxHeight = 53.543;
        double highBasketHeight = 43.0;
        double lowBasketHeight = 25.75;

        double upPower = gamepad.right_trigger * 0.5;
        double downPower = 0;

        if (gamepad.left_trigger > 0) {
            elapsedTime = new ElapsedTime();

            if (seconds < 1.0) {
                downPower = gamepad.left_trigger * 0.5;
            } else if (seconds > 1.0) {
                downPower = 0;
            }

            if (upPower == 1.0) {
                seconds += elapsedTime.seconds();
                leftMotor.setPower(-upPower);
                rightMotor.setPower(upPower);
            } else if (downPower > 0) {
                seconds -= elapsedTime.seconds();
                leftMotor.setPower(downPower);
                rightMotor.setPower(-downPower);
            } else {
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }
        } else {
            elapsedTime = null;
        }

        leftMotor.setPower(upPower);
        rightMotor.setPower(-upPower);
        leftMotor.setPower(-downPower);
        leftMotor.setPower(downPower);
    }

}
