package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class servoClaw {
    private final HardwareMap hwMap;
    private double closePos = 0.7;
    private double openPos = 0.5;

    public servoClaw(HardwareMap hwMap) {
            this.hwMap = hwMap; }

    public void move(Gamepad gamepad) {
        Servo claw = hwMap.get(Servo.class, "claw");

        if(gamepad.right_bumper) {
            claw.setPosition(closePos);
        }
        if(gamepad.left_bumper) {
            claw.setPosition(openPos);
        }

    }
}
