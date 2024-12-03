package org.firstinspires.ftc.teamcode.robotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mainControl;

public class linkageControl {
    private HardwareMap hwMap;
    private DcMotor leftMotor, rightMotor;

    public linkageControl(HardwareMap hwMap) {
        this.hwMap = hwMap;
        init();
    }

    public void init() {
        leftMotor = hwMap.get(DcMotor.class, "leftMotor");
        rightMotor = hwMap.get(DcMotor.class, "rightMotor");
    }

    public void move(Gamepad gamepad)  {
        double maxHeight = 53.543;
        double highBasketHeight = 43.0;
        double lowBasketHeight = 25.75;

        double rightTriggerPower = (gamepad.right_trigger / 2) * -1;
        double leftTriggerPower = (gamepad.left_trigger / 2) * -1;

        // clock & counterclock
        leftMotor.setPower(rightTriggerPower);
        rightMotor.setPower(rightTriggerPower);

        // counterclock & clock
        leftMotor.setPower(leftTriggerPower);
        rightMotor.setPower(leftTriggerPower);
    }
}
