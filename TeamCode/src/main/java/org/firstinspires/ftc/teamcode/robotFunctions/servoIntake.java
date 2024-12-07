package org.firstinspires.ftc.teamcode.robotFunctions;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class servoIntake {
    private final HardwareMap hwMap;
    private double buttonPower = 0.0;
    private double armPosition = 0.7;

    public servoIntake(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void move(Gamepad gamepad) {
        CRServo intake = hwMap.get(CRServo.class, "intake");
        Servo arm = hwMap.get(Servo.class, "armServo");

        if (gamepad.y) {
            buttonPower = 1.0;
        } else if (gamepad.b) {
            buttonPower = -1.0;
        } else {
            buttonPower = 0.0;
        }

        if (gamepad.x) {
            armPosition = 0.45;
        } else if (gamepad.left_bumper) {
            armPosition = 0.7;
        } else if (gamepad.a) {
            armPosition = 0.145;
        }

        intake.setPower(buttonPower);
        arm.setPosition(armPosition);
    }
}

