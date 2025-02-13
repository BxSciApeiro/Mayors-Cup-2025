package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.teleOp.mainControl.servoState;
import org.jetbrains.annotations.NotNull;

public class servoClaw {
    private final HardwareMap hwMap;
    private final Telemetry tele;
    private Servo claw;
    private double closePos = 0.2; //0.7 close 0.5 open for og claw, 0.9 close 0.8 open for second claw, 0.4 for third claw
    private double openPos = 0.6;  //0.9 close, 0.7 open for 1st qualifer claw

    servoState state;

    public servoClaw(HardwareMap hwMap, Telemetry tele) {
        this.hwMap = hwMap;
        this.tele = tele;
    }

    public void init() {
        claw = hwMap.get(Servo.class, "claw");
        state = servoState.OPEN;
    }

    public void move(Gamepad gamepad) {
        init();

        if (gamepad.right_bumper) {
            claw.setPosition(closePos);
        }

        if (gamepad.left_bumper) {
            claw.setPosition(openPos);
        }
    }

    public class AutoMove implements Action {
        private boolean initialized = false;
        private final servoState state;
        private double pos;

        public AutoMove(servoState state) {
            this.state = state;
            init();
        }

        @Override
        public boolean run(@NotNull TelemetryPacket packet) {
            switch (state) {
                case CLOSED:
                    pos = openPos;
                    break;
                case OPEN:
                    pos = closePos;
                    break;
            }

            if (!initialized) {
                claw.setPosition(pos);
                initialized = true;
            }

            double currentPos = claw.getPosition();
            tele.addData("clawPos", currentPos);
            tele.addData("neededPos", pos);
            tele.addData("state", state);

            return currentPos != pos;
        }
    }

    public Action autoMove(servoState state) {
        return new AutoMove(state);
    }
}
