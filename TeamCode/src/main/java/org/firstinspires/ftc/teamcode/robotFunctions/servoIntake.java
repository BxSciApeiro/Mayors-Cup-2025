package org.firstinspires.ftc.teamcode.robotFunctions;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class servoIntake {
    private final HardwareMap hwMap;

    public servoIntake(HardwareMap hwMap) {
        this.hwMap = hwMap;

    }

    public void move(Gamepad gamepad) {
        CRServo intake = hwMap.get(CRServo.class, "intake");
        Servo arm = hwMap.get(Servo.class, "armServo");
        double buttonPower = 0;

        if (gamepad.y) {
            buttonPower = 1.0;
        } else if (gamepad.b) {
            buttonPower = -1.0;
        } else if (gamepad.a) {
            arm.setPosition(5.0);
        } else if (gamepad.x) {
            arm.setPosition(75);
        }

        intake.setPower(buttonPower);
    }
}

