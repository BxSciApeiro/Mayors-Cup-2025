package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.jetbrains.annotations.NotNull;

public class linkageControl {
    private final HardwareMap hwMap;
    private final Telemetry tele;
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;
    private int leftLimit = -50;
    private int rightLimit = 50;

    private final Gamepad prevGamePad = new Gamepad();

    private int lockedLeftPos;
    private int lockedRightPos;

    private double TPS = 2779;
    private double limitPos = 50;

    private lockStates lockState;
    public enum lockStates {
        ON,
        OFF
    }

    public linkageControl(HardwareMap hwMap, Telemetry tele) {
        this.hwMap = hwMap;
        this.tele = tele;
        init();
    }

    public void init() {
        leftMotor = hwMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hwMap.get(DcMotorEx.class, "rightMotor");
        lockState = lockStates.OFF;

        assert leftMotor != null;
        assert rightMotor != null;

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void move(Gamepad gamepad) {
        int leftPos = leftMotor.getCurrentPosition();
        int rightPos = rightMotor.getCurrentPosition();

        tele.addData("leftMotor", leftPos);
        tele.addData("rightMotor", rightPos);
        tele.addData("lockState", lockState);

        if (gamepad.share) {
            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            limitPos = -200;
        } // Temporary solution to faulty encoders values

        if (gamepad.dpad_up) {
            if (!prevGamePad.dpad_up) {
                lockedLeftPos = leftMotor.getCurrentPosition();
                lockedRightPos = rightMotor.getCurrentPosition();
            }

            lockState = lockStates.ON;
        } else if (gamepad.dpad_down) {
            lockState = lockStates.OFF;
        }

        controlState(gamepad);
    }

    public void controlState(Gamepad gamepad) {
        double upPower = gamepad.right_trigger * 0.8;
        double downPower = gamepad.left_trigger * 0.8;

        switch (lockState) {
            case ON:
                leftMotor.setTargetPosition(lockedLeftPos);
                rightMotor.setTargetPosition(lockedRightPos);

                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                leftMotor.setVelocity(TPS);
                rightMotor.setVelocity(TPS);
                break;
            case OFF:
                leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                if (leftMotor.getCurrentPosition() <= -limitPos && rightMotor.getCurrentPosition() >= limitPos) {
                    leftMotor.setPower(-upPower + downPower);
                    rightMotor.setPower(upPower + -downPower);
                } else {
                    leftMotor.setPower(-upPower);
                    rightMotor.setPower(upPower);
                }
                break;
        }
    }

    public class AutoMove implements Action {
        private final int pos;
        private final double power;

        public AutoMove(int pos, double power) {
            this.pos = pos;
            this.power = power;
            init();
        }

        @Override
        public boolean run(@NotNull TelemetryPacket packet) {
            leftMotor.setTargetPosition(-pos);
            rightMotor.setTargetPosition(pos);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftMotor.setVelocity(TPS * power);
            rightMotor.setVelocity(TPS * power);

            double leftPos = leftMotor.getCurrentPosition();
            double rightPos = rightMotor.getCurrentPosition();

            tele.addData("leftMotor", leftPos);
            tele.addData("rightMotor", rightPos);

            if (leftPos < -pos || rightPos < pos) {
                return true;
            } else {
                leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                return false;
            }
        }
    }

    public Action autoMove(int pos, double power) {
        return new AutoMove(pos, power);
    }
}

