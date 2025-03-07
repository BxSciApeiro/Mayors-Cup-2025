package org.firstinspires.ftc.teamcode.teleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class tuning extends OpMode {
    private PIDController controller;

    public static double p = 0, i = 0, d = 0;
    public static double f = 0;

    private DcMotorEx motor;
    public int targetPos;
    private final double ticks = 537.7 / 180;

    @Override
    public void init() {
        controller = new PIDController(p, i , d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        motor = hardwareMap.get(DcMotorEx.class, "tune_motor");
    }

    @Override
    public void loop() {
        controller.setPID(p, i , d);
        int motorPos = motor.getCurrentPosition();
        double pid = controller.calculate(motorPos, targetPos);
        double ff = Math.cos(Math.toRadians(targetPos / ticks)) * f;

        double power = pid + ff;

        motor.setPower(power);

        telemetry.addData("currentPos: ", motorPos);
        telemetry.addData("targetPos: ", targetPos);
        telemetry.update();
    }
}
