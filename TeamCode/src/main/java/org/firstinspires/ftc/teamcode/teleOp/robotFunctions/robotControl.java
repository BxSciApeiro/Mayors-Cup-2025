package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.robotFunctions.driveTrain;

public class robotControl {
    private driveTrain drive;
    private linkageControl linkage;
    private locker locker;
    private servoClaw claw;
    private tail tail;

    public void init(HardwareMap hwMap) {
        drive = new driveTrain(hwMap);
        linkage = new linkageControl(hwMap);
        locker = new locker(hwMap);
        claw = new servoClaw(hwMap);
        tail = new tail(hwMap);
    }

    public void run(Gamepad gamePad) {
        drive.drivePower(gamePad);
        linkage.move(gamePad);
        locker.move(gamePad);
        claw.move(gamePad);
        tail.move(gamePad);
    }
}