package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class intakeTest extends LinearOpMode {
    public CRServo servoTest;

    @Override
    public void runOpMode() {
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

