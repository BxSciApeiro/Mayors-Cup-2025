package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.teleOp.mainControl.servoState;

public class backArm extends servoClaw {
    private final HardwareMap hwMap;
    private final Telemetry tele;

    private Servo backClaw;
    private DcMotorEx backArm;
    private CRServo backRotator;

    private final int TPS = 100; // TODO change value to make arm resist gravity, but not stutter
    private int armUp = -3;
    private int armSpecUp = -170;
    private int armsSpecGrab = -215;

    public backArm(HardwareMap hwMap, Telemetry tele) {
        super(hwMap, tele);
        this.hwMap = hwMap;
        this.tele = tele;
    }

    public void init() {
        backClaw = hwMap.get(Servo.class, "backClaw");
        backArm = hwMap.get(DcMotorEx.class, "backArm");
        backRotator = hwMap.get(CRServo.class, "backRotator");
        backArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        closePos = 0.1;
        openPos = 0.5;
    }

    public void move(Gamepad gamepad) {
        init();

        if (gamepad.dpad_up) {
            setArmPos(armUp);
        }

        if (gamepad.dpad_left) {
            setArmPos(armSpecUp);
        }

        if (gamepad.dpad_down) {
            setArmPos(armsSpecGrab);
        }

        if (gamepad.right_bumper) {
            setPos(servoState.CLOSED);
            backClaw.setPosition(newPos);
        }

        if (gamepad.left_bumper) {
            setPos(servoState.OPEN);
            backClaw.setPosition(newPos);
        }

        double rotatorPower = gamepad.right_stick_y * 0.25;
        backRotator.setPower(rotatorPower);
    }

    public void setArmPos(int armPos) {
        backArm.setTargetPosition(armPos);
        backArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backArm.setVelocity(TPS);
    }
}


