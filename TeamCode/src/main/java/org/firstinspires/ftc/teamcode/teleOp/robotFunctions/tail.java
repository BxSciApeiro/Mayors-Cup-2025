package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class tail {
    private final HardwareMap hwMap;
    private ElapsedTime timer;
    private boolean goReturn = false;

    public tail(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void move(Gamepad gamepad)  {
        CRServo tail = hwMap.get(CRServo.class, "tail");

        if (gamepad.share) {
            timer = new ElapsedTime();
        }

        if (timer != null && timer.seconds() > 1.0) {
            goReturn = !goReturn;
            timer.reset();
        }

        double statement = goReturn ? 0.15 : -0.15;
        tail.setPower(statement);
    }
}

