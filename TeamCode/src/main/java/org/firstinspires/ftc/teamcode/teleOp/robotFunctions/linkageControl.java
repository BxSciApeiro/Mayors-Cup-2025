package org.firstinspires.ftc.teamcode.teleOp.robotFunctions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class linkageControl {
    private HardwareMap hwMap;
    private ElapsedTime elapsedTime = null;
    private double seconds = 0.0;
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public linkageControl(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }



    public void move(Gamepad gamepad) {
        leftMotor =  hwMap.get(DcMotor.class, "leftMotor");
        rightMotor = hwMap.get(DcMotor.class, "rightMotor");
        double maxHeight = 53.543;
        double highBasketHeight = 43.0;
        double lowBasketHeight = 25.75;
        double upPower = gamepad.right_trigger * 0.50;
        double downPower = gamepad.left_trigger * 0.50;

        leftMotor.setPower(-upPower + downPower);
        rightMotor.setPower(upPower + -downPower);

        }


    }

