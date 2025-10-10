package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

import java.util.List;

public class VisionSubsystem extends SubsystemBase{
    Limelight3A vision;
    LLResult result;


     int aprilTagID;
    //TODO Fill in information for finding distance
    private double targetx, targety;

    double angleToGoalDegrees = Constants.vision.kLimeLightMountAngleDegree + targety;
    double angleToGoalRadian = Math.toRadians(angleToGoalDegrees);
    double distance;

    public VisionSubsystem(Limelight3A vision)
    {
        this.vision = vision;

        vision.pipelineSwitch(0);
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

    public int getPipeline()
    {
        return result.getPipelineIndex();
    }

    //TODO Add ratio to get target x in inches

    public double getTargetX(measurement measurement)
    {
        switch(measurement)
        {
            case PIXELS:
                return targetx;
            case INCHES:
                return targetx;
            default:
                throw new IllegalStateException("Unexpected value: " + measurement);
        }
    }

    public double getTargetY(measurement measurement)
    {
        switch(measurement) {
            case PIXELS:
                return targety;
            case INCHES:
                return Constants.vision.kTargetHeight - Constants.vision.kLimeLightHeightInches;
            default:
                throw new IllegalStateException("Unexpected value: " + measurement);
        }
    }

    public double getTargetDistance()

    {
        return distance;
    }

    public void getAprilTags()
    {
        List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
        for (LLResultTypes.FiducialResult fiducial : fiducials) {
            aprilTagID = fiducial.getFiducialId(); // The ID number of the fiducial
            targetx = fiducial.getTargetXDegrees(); // Where it is (left-right)
            targety = fiducial.getTargetYDegrees(); // Where it is (up-down)
            distance = getTargetY(measurement.INCHES) / Math.tan(angleToGoalRadian); // Find distance to fiducial
        }
    }

    public enum measurement
    {
        INCHES,
        PIXELS
    }
}
