package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name = "SuperMarioWorld")
public class SuperMarioWorld extends LinearOpMode {

    Robot robot = new Robot();

    Servo bob;
    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart();
        robot.hardwareMap(hardwareMap);
        robot.Forward(0.5);
        sleep(1000);
        robot.Stop();
        sleep(10);
        robot.strafe(-0.5,1500);


        bob.setPosition(0.5);
    }

}

