package org.firstinspires.ftc.teamcode.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Control {
    private driveTrain drive;
    private linkageControl linkage;
    private servoIntake intake;
    private Telemetry telemetry;

    public Control(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public void init(HardwareMap hwMap) {
//        drive = new driveTrain(hwMap);
        linkage = new linkageControl(hwMap);
//        intake = new servoIntake(hwMap);
    }

    public void runOne(Gamepad gamepad) {
        telemetry.addData("gamepad1", true);
        telemetry.update();
        drive.drivePower(gamepad);
    }

    public void runTwo(Gamepad gamepad) {
        linkage.move(gamepad);
//        intake.move(gamepad);
    }
}