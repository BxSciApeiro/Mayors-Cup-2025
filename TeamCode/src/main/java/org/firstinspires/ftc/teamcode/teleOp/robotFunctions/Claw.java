package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    private final HardwareMap hwMap;
    private double armPosition = 0.7;

    public Claw (HardwareMap hwMap) {
            this.hwMap = hwMap; }

    public void move(Gamepad gamepad) {
        Servo arm = hwMap.get(Servo.class, "armServo");

        if (gamepad.y) {
            armPosition = 0.5;
        } else if (gamepad.b) {
            armPosition = 1;
        } else {
            armPosition = 0.0;
        }

        arm.setPosition(armPosition);
    }
}
