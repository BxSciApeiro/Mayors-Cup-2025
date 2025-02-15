package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class robotControl {
    private driveTrain drive;
    private linkageControl linkage;
    private locker locker;
    private servoClaw claw;
    private backArm arm;

    public void init(HardwareMap hwMap, Telemetry tele) {
        drive = new driveTrain(hwMap);
        linkage = new linkageControl(hwMap, tele);
        locker = new locker(hwMap);
        claw = new servoClaw(hwMap, tele);
        arm = new backArm(hwMap, tele);
    }

    public void run(Gamepad gamePad, Gamepad gamePad2) {
        drive.move(gamePad);
        linkage.move(gamePad);
        locker.move(gamePad);
        claw.move(gamePad);
        arm.move(gamePad2);
    }
}