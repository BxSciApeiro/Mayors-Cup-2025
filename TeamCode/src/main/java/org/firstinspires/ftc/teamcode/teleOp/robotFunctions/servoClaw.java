package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class servoClaw {
    private final HardwareMap hwMap;
    private double closePos = 0.2; //0.7 close 0.5 open for og claw, 0.9 close 0.8 open for second claw, 0.4 for third claw
    private double openPos = 0.6;  //0.9 close, 0.7 open for 1st qualifer claw

    public servoClaw(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void move(Gamepad gamepad) {
        Servo claw = hwMap.get(Servo.class, "claw");

        if (gamepad.right_bumper) {
            claw.setPosition(closePos);
        }

        if (gamepad.left_bumper) {
            claw.setPosition(openPos);
        }
    }
}
