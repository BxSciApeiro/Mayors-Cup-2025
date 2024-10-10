package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class TestingClass extends LinearOpMode {
    private Servo servo;

    @Override
    public void runOpMode() {
        servo = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            Gamepad gamepad = this.gamepad1;
            int i = 0;
            double position = (double) i / 10;

            while (gamepad.y) {
                servo.setPosition((position));
                i++;
            }
            
            while (gamepad.a) {
                servo.setPosition((position));
                i--;
            }

            telemetry.addData("Servo Position", servo.getPosition());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
