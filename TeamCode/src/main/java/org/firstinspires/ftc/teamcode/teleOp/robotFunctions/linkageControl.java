package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class linkageControl {
    private HardwareMap hwMap;
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public linkageControl(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void move(Gamepad gamepad) {
        leftMotor =  hwMap.get(DcMotor.class, "leftMotor");
        rightMotor = hwMap.get(DcMotor.class, "rightMotor");
        // double maxHeight = 53.543;
        // double highBasketHeight = 43.0;
        // double lowBasketHeight = 25.75;
        double upPower = gamepad.right_trigger * 0.75;
        double downPower = gamepad.left_trigger * 0.75;

        leftMotor.setPower(-upPower + downPower);
        rightMotor.setPower(upPower + -downPower);

        }
    }

