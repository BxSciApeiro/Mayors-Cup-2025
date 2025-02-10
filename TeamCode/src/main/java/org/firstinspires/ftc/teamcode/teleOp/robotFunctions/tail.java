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
<<<<<<< HEAD
    private boolean goReturn = false;
    private final Gamepad prevGamePad = new Gamepad();
    private boolean tailState = false;
=======
>>>>>>> parent of bea4c3e (WIP: tail movement)

    public tail(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void move(Gamepad gamepad)  {
        CRServo tail = hwMap.get(CRServo.class, "tail");

        if (gamepad.share) {
<<<<<<< HEAD
            if (!prevGamePad.share) {
                tailState = !tailState;
            }
        }

        if (tailState) {
            timer = new ElapsedTime();
            tail.setPower(goReturn ? 0.15 : -0.15);

            if (timer.seconds() > 1.0) {
                goReturn = !goReturn;
                timer.reset();
            }
        } else {
            timer = null;
        }
=======
            timer.reset();
            while (timer.seconds()<= 5) {
                if (timer.seconds() == 1.0 || timer.seconds() == 3.0 || timer.seconds() == 5.0) {
                    tail.setPower(-0.05);
                } else if (timer.seconds() == 2.0 || timer.seconds() == 4.0) {
                    tail.setPower(0.05);
                }
            }
    }
>>>>>>> parent of bea4c3e (WIP: tail movement)
    }
}

