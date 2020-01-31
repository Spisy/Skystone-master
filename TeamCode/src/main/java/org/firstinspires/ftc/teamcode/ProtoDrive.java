package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "TestDrive")
public class ProtoDrive extends OpMode {

    Robot robot = new Robot();

    @Override
    public void init() {
        gamepad1.setJoystickDeadzone(0.1f);
        gamepad2.setJoystickDeadzone(0.1f);

        robot.init(hardwareMap);

        telemetry.addData("FrontLeft Position", robot.frontLeft.getCurrentPosition());
        telemetry.addData("BackLeft Position", robot.backLeft.getCurrentPosition());
        telemetry.addData("FrontRight Position", robot.frontRight.getCurrentPosition());
        telemetry.addData("BackRight Position", robot.backRight.getCurrentPosition());
        telemetry.addData("IntakeL Position", robot.intakeL.getPower());
        telemetry.addData("IntakeR Position", robot.intakeR.getPower());
    }

    @Override
    public void loop(){
        //variables
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;

        double z = Math.sin(Math.atan(gamepad1.right_stick_x));
        double max;

        double Θ;
        double r;

        //radius logic
        if ((Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))) > 1){
            r = 1;
        } else {
            r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }

        //angle logic
        if (y >= 0 && x > 0) {
            Θ = Math.atan(y / x);
        } else if (x < 0 && y!=0) {
            Θ = Math.atan(y / x) + Math.PI;
        } else if (y <= 0 && x > 0) {
            Θ = Math.atan(y / x) + 2 * Math.PI;
        } else if (x == 0 && y!=0) {
            Θ = Math.asin(y/Math.abs(y));
        } else {
            Θ = Math.PI;
        }

        if (r!=0) {
            if (Math.abs(Math.cos(Θ - 0.25 * Math.PI)) > Math.abs(Math.sin(Θ - 0.25 * Math.PI))) {
                max = Math.abs(Math.cos(Θ - 0.25 * Math.PI)) + Math.abs(z);
            } else {
                max = Math.abs(Math.sin(Θ - 0.25 * Math.PI)) + Math.abs(z);
            }
        } else {
            max = Math.abs(z);
        }


        //fastMode logic
        if (gamepad1.left_stick_button){
            robot.fastMode = false;
        } else if  (gamepad1.right_stick_button) {
            robot.fastMode = true;
        }

        //drivetrain logic
        if (robot.fastMode) {
            robot.frontLeft.setPower(Math.pow(robot.roundHun((r * Math.cos(Θ - 0.25 * Math.PI) + z) / max), 3));
            robot.backLeft.setPower(Math.pow(robot.roundHun((r * Math.sin(Θ - 0.25 * Math.PI) + z) / max), 3));
            robot.frontRight.setPower(Math.pow(robot.roundHun((r * Math.sin(Θ - 0.25 * Math.PI) - z) / max), 3));
            robot.backRight.setPower(Math.pow(robot.roundHun((r * Math.cos(Θ - 0.25 * Math.PI) - z) / max), 3));
        } else {
            robot.frontLeft.setPower(Math.pow(robot.roundHun((r * Math.cos(Θ - 0.25 * Math.PI) + z) / max), 1/3));
            robot.backLeft.setPower(Math.pow(robot.roundHun((r * Math.sin(Θ - 0.25 * Math.PI) + z) / max), 1/3));
            robot.frontRight.setPower(Math.pow(robot.roundHun((r * Math.sin(Θ - 0.25 * Math.PI) - z) / max), 1/3));
            robot.backRight.setPower(Math.pow(robot.roundHun((r * Math.cos(Θ - 0.25 * Math.PI) - z) / max), 1/3));
        }

        if (gamepad2.right_bumper){
            robot.intakeL.setPower(0.65);
            robot.intakeR.setPower(0.65);
        }else if (gamepad2.left_bumper){
            robot.intakeL.setPower(-0.65);
            robot.intakeR.setPower(-0.65);
        }else {
            robot.intakeL.setPower(0);
            robot.intakeR.setPower(0);
        }

        if (gamepad1.a) {
            robot.grabberL.setPosition(0);
            robot.grabberR.setPosition(0);
        } else if (gamepad1.b){
            robot.grabberL.setPosition(1);
            robot.grabberR.setPosition(1);
        }
    }

}