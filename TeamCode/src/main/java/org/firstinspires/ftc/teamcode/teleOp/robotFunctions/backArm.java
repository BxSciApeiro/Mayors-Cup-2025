package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.teleOp.mainControl.servoState;

public class backArm extends servoClaw {
    private final HardwareMap hwMap;
    private final Telemetry tele;

    private Servo backClaw;
    private DcMotor backArm;
    private CRServo backRotator;

    public backArm(HardwareMap hwMap, Telemetry tele) {
        super(hwMap, tele);
        this.hwMap = hwMap;
        this.tele = tele;
    }

    @Override
    public void init() {
        backClaw = hwMap.get(Servo.class, "backClaw");
        backArm = hwMap.get(DcMotor.class, "backArm");
        backRotator = hwMap.get(CRServo.class, "backRotator");

        backArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        closePos = 0.4;
        openPos = 0.7;
    }

    @Override
    public void move(Gamepad gamepad) {
        init();

        if (gamepad.right_bumper) {
            setPos(servoState.CLOSED);
            backClaw.setPosition(newPos);

        }

        if (gamepad.left_bumper) {
            setPos(servoState.OPEN);
            backClaw.setPosition(newPos);
        }

        double armPower = gamepad.right_stick_y * 0.15;
        double rotatorPower = gamepad.left_stick_y * 0.25;

        backArm.setPower(-armPower);
        backRotator.setPower(rotatorPower);
    }
}


