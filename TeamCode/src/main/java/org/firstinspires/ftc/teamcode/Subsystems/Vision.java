package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

import java.util.List;

public class Vision extends SubsystemBase{
    Limelight3A vision;
    LLResult result;
    Telemetry telemetry;

     int aprilTagID;
    //TODO Fill in information for finding distance
    private double targetx, targety,
            limeLightMountAngleDegree = Constants.vision.kLimeLightMountAngleDegree,
            limeLightHeightInches = Constants.vision.kLimeLightHeightInches,
            targetHeight = Constants.vision.kTargetHeight;
    double angleToGoalDegrees = limeLightMountAngleDegree + targety;
    double angleToGoalRadian = Math.toRadians(angleToGoalDegrees);
    double distance;

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

    //TODO Add ratio to get target x in inches

    public double getTargetX(String measurement)
    {
        switch(measurement)
        {
            case "PIXELS":
                return targetx;
            case "INCHES":
                return targetx;
            default:
                throw new IllegalStateException("Unexpected value: " + measurement);
        }
    }

    public double getTargetY(String measurement)
    {
        switch(measurement) {
            case "PIXELS":
                return targety;
            case "INCHES":
                return targetHeight - limeLightHeightInches;
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
            distance = (targetHeight - limeLightHeightInches) / Math.tan(angleToGoalRadian); // Find distance to fiducial
            telemetry.addData("AprilTag is: ", aprilTagID);
            telemetry.addData("AprilTag X: ", targetx); telemetry.addData("AprilTag Y: ", targety);
            telemetry.addData("AprilTag Distance: ", distance);
        }
    }
}
