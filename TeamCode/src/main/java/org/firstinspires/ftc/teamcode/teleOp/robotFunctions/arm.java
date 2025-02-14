package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class arm {
    private final HardwareMap hwMap;
    private CRServo arm;

    public arm(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void move(Gamepad gamepad)  {
        CRServo arm = hwMap.get(CRServo.class, "arm");
        double y = gamepad.left_stick_y*0.75;
        arm.setPower(-y);

    }
}


