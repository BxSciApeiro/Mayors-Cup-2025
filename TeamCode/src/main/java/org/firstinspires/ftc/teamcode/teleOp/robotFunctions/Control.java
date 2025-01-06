package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Control {
    private driveTrain drive;
    private linkageControl linkage;
    private servoIntake intake;
    private Claw claw;

    public void init(HardwareMap hwMap) {
        drive = new driveTrain(hwMap);
        linkage = new linkageControl(hwMap);
        intake = new servoIntake(hwMap);
        claw = new Claw(hwMap);
    }

    public void run(Gamepad gamePad) {
        drive.drivePower(gamePad);
        linkage.move(gamePad);
        intake.move(gamePad);
        claw.move(gamePad);
    }
}