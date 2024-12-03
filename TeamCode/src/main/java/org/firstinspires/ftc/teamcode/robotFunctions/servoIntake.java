package org.firstinspires.ftc.teamcode.robotFunctions;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class servoIntake {
    private HardwareMap hwMap;
    private CRServo servo;

    public servoIntake(HardwareMap hwMap) {
        this.hwMap = hwMap;
        servo = hwMap.get(CRServo.class, "intakeServo");
    }

    public void move(Gamepad gamepad) {
        double buttonPower = -gamepad.left_stick_y;
        servo.setPower(buttonPower);
    }
}

