package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name= "TestTele2")
public class TestOp2 extends OpMode {
    //Robot robot = new Robot();
    Robot robot = new Robot();

    @Override
    public void init() {
        gamepad1.setJoystickDeadzone(0.1f);
        gamepad2.setJoystickDeadzone(0.1f);

        robot.init(hardwareMap);

        telemetry.addData("Joystick X:", gamepad1.left_stick_x);
        telemetry.addData("Joystick Y:", gamepad1.left_stick_y);
        telemetry.addData("FrontLeft Position", robot.frontLeft.getCurrentPosition());
        telemetry.addData("BackLeft Position", robot.backLeft.getCurrentPosition());
        telemetry.addData("FrontRight Position", robot.frontRight.getCurrentPosition());
        telemetry.addData("BackRight Position", robot.backRight.getCurrentPosition());
        telemetry.addData("IntakeL Position", robot.intakeL.getPower());
        telemetry.addData("IntakeR Position", robot.intakeR.getPower());
    }

    @Override
    public void loop() {
        double y = gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double z = gamepad1.right_stick_x;
        double m = 1; // modifier

        if (robot.frontLeft.getPower() > 1 || robot.frontRight.getPower() > 1 || robot.backLeft.getPower() > 1 || robot.backRight.getPower() > 1) {
            m = Math.max(robot.frontLeft.getPower(), robot.frontRight.getPower());
            m = Math.max(robot.backLeft.getPower(), m);
            m = Math.max(robot.backRight.getPower(), m);

        }
        else if (gamepad1.x){
            m = 2;
        }
        
        if (y > 0 && -0.3 < x && x < 0.3) { // forward region
            robot.frontLeft.setPower((y - z) / m);
            robot.backLeft.setPower((y - z) / m);
            robot.frontRight.setPower((y + z) / m);
            robot.backRight.setPower((y + z) / m);
        } else if (y < 0 && -0.3 < x && x < 0.3) { // backward region
            robot.frontLeft.setPower((y - z) / m);
            robot.backLeft.setPower((y - z) / m);
            robot.frontRight.setPower((y + z) / m);
            robot.backRight.setPower((y + z) / m);
        } else if (x > 0 && -0.3 < y && y < 0.3) {
            // right region
            robot.frontLeft.setPower((-x - z) / m);
            robot.backLeft.setPower((x - z) / m);
            robot.frontRight.setPower((x + z) / m);
            robot.backRight.setPower((-x + z) / m);
        } else if (x < 0 && -0.3 < y && y < 0.3) {
            // left position
            robot.frontLeft.setPower((-x - z) / m);
            robot.backLeft.setPower((x - z) / m);
            robot.frontRight.setPower((x + z) / m);
            robot.backRight.setPower((-x + z) / m);
        } else { // diagonals
            robot.frontLeft.setPower((y - x - z) / m);
            robot.backLeft.setPower((y + x - z) / m);
            robot.frontRight.setPower((y + x + z) / m);
            robot.backRight.setPower((y - x + z) / m);
        }


    }
}