package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class linkageControl {
    private final HardwareMap hwMap;
    private final Telemetry tele;
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;

    private final Gamepad prevGamePad = new Gamepad();

    private int lockedLeftPos;
    private int lockedRightPos;
    private int leftPos;
    private int rightPos;

    public enum lockStates {
        ON,
        OFF
    }

    lockStates lockState;
    int maxLeft = -9865;
    int maxRight = 7415;

    public linkageControl(HardwareMap hwMap, Telemetry tele) {
        this.hwMap = hwMap;
        this.tele = tele;
    }

    public void move(Gamepad gamepad) {
        leftMotor = hwMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hwMap.get(DcMotorEx.class, "rightMotor");

        lockState = lockStates.OFF;

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if (gamepad.dpad_up) {
            if (!prevGamePad.dpad_up) {
                lockedLeftPos = leftMotor.getCurrentPosition();
                lockedRightPos = rightMotor.getCurrentPosition();
            }

            lockState = lockStates.ON;
        } else if (gamepad.dpad_down) {
            lockState = lockStates.OFF;
        }

        leftPos = leftMotor.getCurrentPosition();
        rightPos = rightMotor.getCurrentPosition();

        controlState(gamepad);
        tele.addData("leftMotor", leftPos);
        tele.addData("rightMotor", rightPos);
    }

    public void controlState(Gamepad gamepad) {
        double upPower = gamepad.right_trigger * 0.75;
        double downPower = gamepad.left_trigger * 0.75;
        double TPS = 2779;

        tele.addData("leftPos", lockedLeftPos);
        tele.addData("rightPos", lockedRightPos);
        tele.addData("lockState", lockState);

        switch (lockState) {
            case ON:
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                leftMotor.setTargetPosition(lockedLeftPos);
                rightMotor.setTargetPosition(lockedRightPos);

                leftMotor.setVelocity(TPS);
                rightMotor.setVelocity(TPS);
                break;
            case OFF:
                leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                if ((leftPos < maxLeft) || (rightPos < maxRight)) {
                    leftMotor.setPower(-upPower + downPower);
                    rightMotor.setPower(upPower + -downPower);
                } else {
                    leftMotor.setPower(downPower);
                    rightMotor.setPower(-downPower);
                }

                break;
        }
    }
}

