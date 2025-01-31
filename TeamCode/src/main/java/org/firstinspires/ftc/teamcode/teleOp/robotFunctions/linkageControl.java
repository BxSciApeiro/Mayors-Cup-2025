package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class linkageControl {
    private HardwareMap hwMap;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    public int positionleft;
    public int positionright;

    public linkageControl(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void move(Gamepad gamepad) {
        leftMotor =  hwMap.get(DcMotor.class, "leftMotor");
        rightMotor = hwMap.get(DcMotor.class, "rightMotor");
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // double maxHeight = 53.543;
        // double highBasketHeight = 43.0;
        // double lowBasketHeight = 25.75;
        double upPower = gamepad.right_trigger * 0.75;
        double downPower = gamepad.left_trigger * 0.75;

        positionleft = leftMotor.getCurrentPosition();
        positionright = rightMotor.getCurrentPosition();


        leftMotor.setPower(-upPower + downPower);
        rightMotor.setPower(upPower + -downPower);
        }

    public int getPositionLeft() {
        return positionleft;
    }
    public int getPositionRight() {
        return positionright;
    }
}

