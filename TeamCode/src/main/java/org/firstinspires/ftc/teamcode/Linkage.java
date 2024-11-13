package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Linkage extends LinearOpMode {

    private DcMotor leftLinearMotor,  rightLinearMotor;

    @Override
    public void runOpMode()  {
        leftLinearMotor = hardwareMap.get(DcMotor.class, "leftLinearMotor");
        rightLinearMotor = hardwareMap.get(DcMotor.class, "rightLinearMotor");
        double maxHeight = 53.543;
        double highBasketHeight = 43.0;
        double lowBasketHeight = 25.75;

        waitForStart();

        while(opModeIsActive()) {
            double upLinearTgtPwr = this.gamepad1.right_trigger;
            double downLinearTgtPwr = this.gamepad1.left_trigger;
            
            leftLinearMotor.setPower(upLinearTgtPwr);
            rightLinearMotor.setPower(-upLinearTgtPwr);
            leftLinearMotor.setPower(-downLinearTgtPwr);
            rightLinearMotor.setPower(downLinearTgtPwr);
        }
    }
}
