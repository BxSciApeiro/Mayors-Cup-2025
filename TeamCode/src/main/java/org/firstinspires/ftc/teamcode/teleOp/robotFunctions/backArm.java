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

    private final int TPS = 50; // TODO change value to make arm resist gravity, but not stutter
    private int armsSpecGrab;
    private int armSpecUp;
    private int armUp;

    public backArm(HardwareMap hwMap, Telemetry tele) {
        super(hwMap, tele);
        this.hwMap = hwMap;
        this.tele = tele;
    }

    public void init() {
        backClaw = hwMap.get(Servo.class, "backClaw");
        backArm = hwMap.get(DcMotorEx.class, "backArm");
        backRotator = hwMap.get(CRServo.class, "backRotator");
        backArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        closePos = 0.1;
        openPos = 0.5;
        armsSpecGrab = -210;
        armSpecUp = -185;
        armUp = -5;
    }

    public void move(Gamepad gamepad) {
        init();

        if(gamepad.dpad_down) {
            backArm.setTargetPosition(armsSpecGrab);
            backArm.setVelocity(TPS);
        }
        if(gamepad.dpad_up) {
            backArm.setTargetPosition(armUp);
            backArm.setVelocity(TPS);
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

        tele.addData("current position of back arm", backArm.getCurrentPosition());
        tele.addData("mode is ", backArm.getMode());
        tele.addData("power is", backArm.getPower());
        tele.addData("gamepad value", gamepad.left_stick_y);
    }
}


