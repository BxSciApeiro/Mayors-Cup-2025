package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class servoAngleFinder extends LinearOpMode {
    private Servo servo;
    private double angle = 0;
    private double neededAngle;

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, "servotest" );
        waitForStart();
        while (opModeIsActive()) {
            angle = angle +0.05;
            servo.setPosition(angle);
            telemetry.addData("servo is currently at: ", servo.getPosition());
            telemetry.update();
            Thread.sleep(250);
        }
    }
}
