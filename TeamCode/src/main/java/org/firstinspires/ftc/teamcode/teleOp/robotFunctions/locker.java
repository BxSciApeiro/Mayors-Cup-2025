package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class locker {
    private final HardwareMap hwMap;
    private double lockerPos = 0;
    private double locker2Pos = 0;

    public locker (HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void move(Gamepad gamepad) {
        Servo locker = hwMap.get(Servo.class, "locker");
        Servo locker2 = hwMap.get(Servo.class, "locker2");

        if (gamepad.triangle) {
            lockerPos = 0;
            locker.setPosition(lockerPos);
        }

        if (gamepad.square) {
            locker2Pos = 0;
            locker2.setPosition(locker2Pos);
        }
    }
}