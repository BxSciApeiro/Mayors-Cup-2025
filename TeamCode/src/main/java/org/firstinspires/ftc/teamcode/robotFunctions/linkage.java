package org.firstinspires.ftc.teamcode.robotFunctions;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mainControl;

public class linkage extends mainControl {
    private DcMotor leftMotor,  rightMotor;

    public void runFunction()  {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        double maxHeight = 53.543;
        double highBasketHeight = 43.0;
        double lowBasketHeight = 25.75;

        waitForStart();

        while(opModeIsActive()) {
            double rightTriggerPower = (this.gamepad1.right_trigger / 2) * -1;
            double leftTriggerPower = (this.gamepad1.left_trigger / 2) * -1;

            // clock & counterclock
            leftMotor.setPower(rightTriggerPower);
            rightMotor.setPower(rightTriggerPower);

            // counterclock & clock
            leftMotor.setPower(leftTriggerPower);
            rightMotor.setPower(leftTriggerPower);

            telemetry.addData("Left Power", leftMotor.getPower());
            telemetry.addData("Right Power", rightMotor.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
