package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "redbuild")
    public class RedBuild extends LinearOpMode {

        Robot robot = new Robot();

        @Override
        public void runOpMode() throws InterruptedException {

            waitForStart();
            robot.init(hardwareMap);
            robot.strafe(0.5,1900);
            robot.Stop();
            sleep(10);
            robot.Forward(0.5);
            sleep(250);
            robot.Stop();
            sleep(10);
            robot.setGrabber();

            sleep(500);
            robot.Stop();
            sleep(10);
            robot.strafe(-0.5, 1900);
            sleep(1900);
            robot.Stop();
            sleep(10);
            robot.setGrabber();
            sleep(500);
            robot.Stop();
            sleep(10);
            robot.strafe(0.5, 1900);
            robot.Forward(-0.5);
            sleep(1000);
        }
    }
