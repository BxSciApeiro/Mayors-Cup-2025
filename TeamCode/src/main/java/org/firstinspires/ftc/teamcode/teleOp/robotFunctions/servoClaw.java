package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.teleOp.mainControl.servoState;
import org.jetbrains.annotations.NotNull;

public class servoClaw {
    private final HardwareMap hwMap;
    private final Telemetry tele;
    private ElapsedTime timer;
    private Servo claw;
    private double closePos = 0.3; // 0.2 close 0.6 open crab claw 0.3 close 0.7 open arm claw
    private double openPos = 0.7;
    private double newPos;

    public servoClaw(HardwareMap hwMap, Telemetry tele) {
        this.hwMap = hwMap;
        this.tele = tele;
    }

    public void init() {
        claw = hwMap.get(Servo.class, "claw");
    }

    public void move(Gamepad gamepad) {
        init();

        if (gamepad.right_bumper) {
            setPos(servoState.CLOSED);
        }

        if (gamepad.left_bumper) {
            setPos(servoState.OPEN);
        }

        claw.setPosition(newPos);
        tele.addData("clawPos", claw.getPosition());
    }

    public void setPos(servoState newState) {
            switch (newState) {
                case OPEN:
                    newPos = openPos;
                    break;
                case CLOSED:
                    newPos = closePos;
                    break;
            }
    }

    public class AutoMove implements Action {
        private final servoState state;

        public AutoMove(servoState state) {
            this.state = state;
            init();
        }

        @Override
        public boolean run(@NotNull TelemetryPacket packet) {
            setPos(state);
            claw.setPosition(newPos);

            if (timer == null) {
                timer = new ElapsedTime();
            }

            return timer.seconds() < 0.5;
        }
    }

    public Action autoMove(servoState state) {
        return new AutoMove(state);
    }
}
