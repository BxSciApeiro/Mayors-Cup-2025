package org.firstinspires.ftc.teamcode.teleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.util.constants;

@Config
@TeleOp
public class tuning extends LinearOpMode {
    private PIDController controller;

    public static double p = 0, i = 0, d = 0;
    public static double targetPos = 0;

    private DcMotorEx motor;
    private DcMotorEx motorTwo;

    @Override
    public void runOpMode() {
        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        motor = hardwareMap.get(DcMotorEx.class, constants.slides.LEFTLINKAGE);
        motorTwo = hardwareMap.get(DcMotorEx.class, constants.slides.RIGHTLINKAGE);
        motor.setDirection(DcMotorEx.Direction.REVERSE);
        waitForStart();

        while (opModeIsActive()) {

            controller.setPID(p, i, d);
            int motorPos = motor.getCurrentPosition();
            double pid = controller.calculate(motorPos, targetPos);

            motor.setPower(pid);
            motorTwo.setPower(pid);

            if (gamepad1.dpad_up) {
                targetPos += 1;
            }
            if (gamepad1.dpad_down) {
                targetPos -= 1;
            }

            telemetry.addData("currentPos", motorPos);
            telemetry.addData("targetPos", targetPos);
            telemetry.addData("PID Output", pid);
            telemetry.update();
        }
    }
}
