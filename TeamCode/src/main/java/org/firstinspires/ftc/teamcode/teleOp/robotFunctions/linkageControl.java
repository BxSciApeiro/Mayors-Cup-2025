package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class linkageControl {
    private HardwareMap hwMap;
    private Telemetry tele;
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;


    public linkageControl(HardwareMap hwMap, Telemetry tele) {
        this.hwMap = hwMap;
        this.tele = tele;
    }

    public void move(Gamepad gamepad) {
        leftMotor =  hwMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hwMap.get(DcMotorEx.class, "rightMotor");

        // double maxHeight = 53.543;
        // double highBasketHeight = 43.0;
        // double lowBasketHeight = 25.75;
        double upPower = gamepad.right_trigger * 0.75;
        double downPower = gamepad.left_trigger * 0.75;

        int leftPos = leftMotor.getCurrentPosition();
        int rightPos = rightMotor.getCurrentPosition();


        if (gamepad.dpad_down) {
            leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        }
        if (gamepad.dpad_left) {
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            int hangLeft = leftMotor.getCurrentPosition();
            int hangRight = rightMotor.getCurrentPosition();
            tele.addData("this values should remain constant", hangLeft);
            tele.addData("this values should remain constant", hangRight);
            leftMotor.setTargetPosition(hangLeft);
            rightMotor.setTargetPosition(hangRight);
            leftMotor.setVelocity(2778.945);
            rightMotor.setVelocity(2778.945);

        }
        tele.addData("left zero power is currently", leftMotor.getZeroPowerBehavior());
        tele.addData("right zero power is currently", rightMotor.getZeroPowerBehavior());
        tele.addData("left motor is ", leftPos);
        tele.addData("right motor is ", rightPos);

        leftMotor.setPower(-upPower + downPower);
        rightMotor.setPower(upPower + -downPower);
        }
}

