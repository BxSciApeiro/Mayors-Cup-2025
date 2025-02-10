package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class robotControl {
    private driveTrain drive;
    private linkageControl linkage;
    private locker locker;
    private servoClaw claw;
    private tail tail;

    public void init(HardwareMap hwMap, Telemetry tele) {
        drive = new driveTrain(hwMap);
        linkage = new linkageControl(hwMap, tele);
        locker = new locker(hwMap);
        claw = new servoClaw(hwMap);
        tail = new tail(hwMap);
        linkage.init();
    }

    public void run(Gamepad gamePad) {
        drive.drivePower(gamePad);
        linkage.move(gamePad);
        locker.move(gamePad);
        claw.move(gamePad);
        tail.move(gamePad);
    }
}