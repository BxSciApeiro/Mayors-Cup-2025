package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class tail {
    private final HardwareMap hwMap;
    private final double y = 0.25; // left
    private final double z = 0.75; // right
    private ElapsedTime timer;

    public tail(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void move(Gamepad gamepad)  {
        CRServo tail = hwMap.get(CRServo.class, "tail");

        if (gamepad.share) {
            timer.reset();
            while (timer.seconds()<= 5) {
                if (timer.seconds() == 1.0 || timer.seconds() == 3.0 || timer.seconds() == 5.0) {
                    tail.setPower(-0.05);
                } else if (timer.seconds() == 2.0 || timer.seconds() == 4.0) {
                    tail.setPower(0.05);
                }
            }
    }
    }
}

