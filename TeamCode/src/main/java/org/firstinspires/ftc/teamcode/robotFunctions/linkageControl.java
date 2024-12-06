package org.firstinspires.ftc.teamcode.robotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class linkageControl {
    private DcMotor leftMotor, rightMotor;
    private ElapsedTime elapsedTime = new ElapsedTime();
    private double seconds = 0.0;

    public linkageControl(HardwareMap hwMap) {
        leftMotor = hwMap.get(DcMotor.class, "leftMotor");
        rightMotor = hwMap.get(DcMotor.class, "rightMotor");
    }

    public void move(Gamepad gamepad)  {
        double maxHeight = 53.543;
        double highBasketHeight = 43.0;
        double lowBasketHeight = 25.75;

        double upPower = gamepad.right_trigger * 0.1;
        double downPower = 0;

        if (gamepad.left_trigger > 0) {
            seconds += elapsedTime.seconds();
            elapsedTime.reset();

            if (seconds < 2.0) {
                downPower = gamepad.left_trigger * 0.1;
            } else {
                downPower = 0;
            }
        } else if (seconds > 0) {
            seconds -= elapsedTime.seconds();
            elapsedTime.reset();
        }

        leftMotor.setPower(-downPower);
        rightMotor.setPower(upPower);
        leftMotor.setPower(downPower);
        rightMotor.setPower(-upPower);
    }
}
