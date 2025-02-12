package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class robotControl {
    private driveTrain drive;
    private linkageControl linkage;
    private locker locker;
    private servoClaw claw;

    public void init(HardwareMap hwMap, Telemetry tele) {
        drive = new driveTrain(hwMap);
        linkage = new linkageControl(hwMap, tele);
        locker = new locker(hwMap);
        claw = new servoClaw(hwMap);
        linkage.init();
    }

    public void run(Gamepad gamePad) {
        drive.move(gamePad);
        linkage.move(gamePad);
        locker.move(gamePad);
        claw.move(gamePad);
    }
}