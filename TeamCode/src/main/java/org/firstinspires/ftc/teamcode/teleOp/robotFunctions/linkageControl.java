package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class linkageControl {
    private HardwareMap hwMap;
    private Telemetry tele;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    public int positionleft;
    public int positionright;

    public linkageControl(HardwareMap hwMap, Telemetry tele) {
        this.hwMap = hwMap;
        this.tele = tele;
    }

    public void move(Gamepad gamepad) {
        leftMotor =  hwMap.get(DcMotor.class, "leftMotor");
        rightMotor = hwMap.get(DcMotor.class, "rightMotor");

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // double maxHeight = 53.543;
        // double highBasketHeight = 43.0;
        // double lowBasketHeight = 25.75;
        double upPower = gamepad.right_trigger * 0.75;
        double downPower = gamepad.left_trigger * 0.75;

        int leftPos = leftMotor.getCurrentPosition();
        int rightPos = rightMotor.getCurrentPosition();

        tele.addData("left motor is ", leftPos);
        tele.addData("right motor is ", rightPos);

        leftMotor.setPower(-upPower + downPower);
        rightMotor.setPower(upPower + -downPower);
        }
}

