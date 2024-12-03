package org.firstinspires.ftc.teamcode.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class mainControl {
    private driveTrain drive;
    private linkageControl linkage;
    private servoIntake intake;

    private Gamepad gamepad1 = new Gamepad();
    private Gamepad gamepad2 = new Gamepad();

    public void init(HardwareMap hwMap) {
        drive = new driveTrain(hwMap);
        linkage = new linkageControl(hwMap);
        intake = new servoIntake(hwMap);
    }

    public void run(Gamepad gamepad) {
        gamepad1.copy(gamepad);
        move();
    }

    private void move() {
        drive.setWeightedDrivePower(gamepad1);
        linkage.move(gamepad2);
        intake.move(gamepad2);
    }

}