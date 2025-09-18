package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.ftccommon.external.OnCreate;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

public class Vision extends SubsystemBase{
    //TODO Make Vision
    Limelight3A vision;
    LLResult result;
    public Telemetry telemetry;

    public Vision()
    {
        vision = hardwareMap.get(Limelight3A.class, "Vision");
        vision.setPollRateHz(100);
        vision.start();
    }

    @Override
    public void periodic()
    {
        result = vision.getLatestResult();

    }

    public void setPipeline(int pipeline)
    {
        vision.pipelineSwitch(pipeline);
    }

    public void getAprilTags()
    {
        List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
        for (LLResultTypes.FiducialResult fiducial : fiducials) {
            int id = fiducial.getFiducialId(); // The ID number of the fiducial
            double x = fiducial.getTargetXDegrees(); // Where it is (left-right)
            double y = fiducial.getTargetYDegrees(); // Where it is (up-down)
            telemetry.addData("AprilTag is: ", id);
        }
    }
}
