package org.firstinspires.ftc.teamcode;


import com.disnodeteam.dogecv.detectors.skystone.SkystoneDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.Locale;

@Autonomous(group="DogeCV")
public class BlueCV extends LinearOpMode {
    private OpenCvCamera phoneCam;
    private SkystoneDetector skyStoneDetector;
    Robot robot = new Robot();


    @Override
    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        phoneCam.openCameraDevice();

        skyStoneDetector = new SkystoneDetector();
        phoneCam.setPipeline(skyStoneDetector);

        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

        waitForStart();
        robot.hardwareMap(hardwareMap);

        while (opModeIsActive()) {


            telemetry.addData("Stone Position X", skyStoneDetector.getScreenPosition().x);
            telemetry.addData("Stone Position Y", skyStoneDetector.getScreenPosition().y);
            telemetry.addData("Frame Count", phoneCam.getFrameCount());
            telemetry.addData("FPS", String.format(Locale.US, "%.2f", phoneCam.getFps()));
            telemetry.addData("Total frame time ms", phoneCam.getTotalFrameTimeMs());
            telemetry.addData("Pipeline time ms", phoneCam.getPipelineTimeMs());
            telemetry.addData("Overhead time ms", phoneCam.getOverheadTimeMs());
            telemetry.addData("Theoretical max FPS", phoneCam.getCurrentPipelineMaxFps());
            telemetry.update();

            if (skyStoneDetector.getScreenPosition().x > 90) {
                robot.forward(0.9, 50);
            }
            else if (skyStoneDetector.getScreenPosition().x < 80) {
                robot.forward(-0.9, 50);
            }
            else {
                robot.strafe(-0.5, 250);
                robot.TurnLeft(0.5, 900);
                robot.forward(0.5, 300);
                robot.TurnLeft(0.5, 100);
                robot.Intake(0.5, 750);
                robot.TurnRight(0.5,100);
                robot.forward(-0.5, 200);
                robot.TurnRight(0.5, 900);
                robot.forward(0.5,1500);
                robot.Intake(-0.5, 750);
                robot.forward(-0.5, 500);
                /*robot.TurnRight(0.5,900);
                robot.forward(-0.5, 50);
                robot.rotateout(0.5, 250);
                robot.virtualFourBar.toggleGripper();
                robot.virtualFourBar.toggleGripper();
            */
            }
            robot.Forward(0);
        }
    }
}