package org.firstinspires.ftc.teamcode.robotFunctions;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Control {
    private driveTrain drive;
    private linkageControl linkage;
    private servoIntake intake;
    private Gamepad gamePad;

    public Control(Gamepad gamePad) {
        this.gamePad = gamePad;
    }

    public void init(HardwareMap hwMap) {
        drive = new driveTrain(hwMap);
        linkage = new linkageControl(hwMap);
        intake = new servoIntake(hwMap);
    }

    public void run() {
        drive.drivePower(gamePad);
        linkage.move(gamePad);
        intake.move(gamePad);
    }
}