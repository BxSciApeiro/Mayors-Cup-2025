package org.firstinspires.ftc.teamcode.robotFunctions;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.mainControl;

public class servoIntake extends mainControl {
    public CRServo servoTest;

    public void runFunction() {
        servoTest = hardwareMap.get(CRServo.class, "servoTest");
        waitForStart();

        while (opModeIsActive()) {
            double buttonPower = -this.gamepad1.left_stick_y;
            servoTest.setPower(buttonPower);

            telemetry.addData("Target Power", buttonPower);
            telemetry.addData("Motor Power", servoTest.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}

