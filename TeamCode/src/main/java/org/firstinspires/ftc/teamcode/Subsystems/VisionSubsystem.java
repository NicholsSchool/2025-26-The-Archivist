package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.Constants;

import java.util.List;

public class VisionSubsystem extends SubsystemBase{
    // IP Address http://172.28.0.1:5801/
    private Limelight3A vision;
    private LLResult result;


     int aprilTagID, aprilTagDecode;
    //TODO Fill in information for finding distance
    private double aprilTagtargetx, aprilTagtargety, aprilTagtargeta, aprilTagtargetLength, aprilTagtargetHeight;
    private double balltargetx, balltargety, balltargeta, balltargetLength, balltargetHeight;

    double angleToGoalDegrees = Constants.vision.kLimeLightMountAngleDegree + aprilTagtargety;
    double angleToGoalRadian = angleToGoalDegrees * (3.14159 / 180.0);
    double AprilTagdistance;

    public VisionSubsystem(Limelight3A vision)
    {
        this.vision = vision;

        setPipeline(0);
        vision.start();
    }

    @Override
    public void periodic()
    {
        angleToGoalRadian = (Constants.vision.kLimeLightMountAngleDegree + aprilTagtargety) * (3.14159 / 180.0);
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

    public double getTargetX(Measurement measurement)
    {
        switch(measurement)
        {
            case PIXELS:
                return aprilTagtargetx;
            case INCHES:
                return (Constants.aprilTagID.kAprilTagDimension / aprilTagtargetLength) * aprilTagtargetx;
            default:
                throw new IllegalStateException("Unexpected value: " + measurement);
        }
    }

    public double getTargetY(Measurement measurement)
    {
        switch(measurement) {
            case PIXELS: return aprilTagtargety;
            case INCHES:
                return Constants.vision.kTargetHeight - Constants.vision.kLimeLightHeightInches;
            default:
                throw new IllegalStateException("Unexpected value: " + measurement);
        }
    }

    public double getTargetLength(Measurement measurement)
    {
        switch (measurement)
        {
            case PIXELS: return aprilTagtargetLength;
            case INCHES: return Constants.aprilTagID.kAprilTagDimension; // Length of AprilTag
            default: throw new IllegalStateException("Unexpected value: " + measurement);
        }
    }

    public double getTargetHeight(Measurement measurement)
    {
        switch (measurement)
        {
            case PIXELS: return aprilTagtargetHeight;
            case INCHES: return Constants.aprilTagID.kAprilTagDimension; // Length of AprilTag
            default: throw new IllegalStateException("Unexpected value: " + measurement);
        }
    }

    public double getTargetArea(Measurement measurement)
    {
        switch (measurement)
        {
            case PIXELS: return aprilTagtargeta;
            case INCHES: return Constants.aprilTagID.kAprilTagDimension * 2; // Length of AprilTag
            default: throw new IllegalStateException("Unexpected value: " + measurement);
        }
    }
    // Returns Currently tracked AprilTagID
    public int getAprilTagID()
    {
        return aprilTagID;
    }

    // Returns the ID of the Current Decode AprilTag
    public int getCurrentAprilTagDecode()
    {
        return aprilTagDecode;
    }

    public double getTargetDistance()
    {
        return AprilTagdistance;
    }

    public void getAprilTags()
    {
        List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
        for (LLResultTypes.FiducialResult fiducial : fiducials) {
            aprilTagID = fiducial.getFiducialId(); // The ID number of the fiducial
            aprilTagtargetx = fiducial.getTargetXDegrees(); // Where it is (left-right)
            aprilTagtargety = fiducial.getTargetYDegrees(); // Where it is (up-down)
            aprilTagtargeta = fiducial.getTargetYPixels() * fiducial.getTargetXPixels(); // Area of it
            aprilTagtargetLength = fiducial.getTargetXPixels();
            aprilTagtargetHeight = fiducial.getTargetYPixels();
            AprilTagdistance = getTargetY(Measurement.INCHES) / Math.tan(angleToGoalRadian); // Find distance to fiducial
        }
    }

    public boolean isAprilTag(int AprilTagID)
    {
        return (AprilTagID == aprilTagID);
    }

    // PipeLine 1 is Purple P
    // PipeLine 2 is Green G
    public void getBallColorDetector(char BallColor)
    {
        if (BallColor == 'P')
        {
            setPipeline(1);
        }
        else if (BallColor == 'G')
        {
            setPipeline(2);
        }
            List<LLResultTypes.ColorResult> balls = result.getColorResults();
            for (LLResultTypes.ColorResult ball : balls) {
                balltargetx = ball.getTargetXDegrees();
                balltargety = ball.getTargetYDegrees();
            }
    }

    public void setDecodeID(int AprilTageDecodeID)
    {
        aprilTagDecode = AprilTageDecodeID;
    }

    public int getDecodeID()
    {
        return aprilTagDecode;
    }

    public char[] getDecodeSequence()
    {
        switch(getDecodeID())
        {
            case Constants.aprilTagID.kAprilTagDecode1: return new char[] {'G', 'P', 'P'};
            case Constants.aprilTagID.kAprilTagDecode2: return new char[] {'P', 'G', 'P'};
            case Constants.aprilTagID.kAprilTagDecode3: return new char[] {'P', 'P', 'G'};
            default: throw new IllegalStateException("Decode ID is Unknown");
        }
    }

    // Different Measurements for getting Vision Data
    public enum Measurement
    {
        INCHES,
        PIXELS
    }
    }
