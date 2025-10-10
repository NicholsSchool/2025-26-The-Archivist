package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.PodOdometry;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

public class GetFieldPos extends CommandBase
{
    VisionSubsystem vision;
    Drivetrain driveTrain;
    PodOdometry odometry;
    Rotation2d robotAngle;
    Pose2d robotPos;

    public GetFieldPos(VisionSubsystem vision, Drivetrain driveTrain, PodOdometry odometry)
    {
        this.vision = vision;
        this.driveTrain = driveTrain;
        this.odometry = odometry;

        addRequirements(vision, driveTrain, odometry);
    }

    @Override
    public void initialize()
    {
        robotAngle = new Rotation2d(0);
        // 0, 0 Is center of field
        robotPos = new Pose2d(0, 0, robotAngle);
    }

    @Override
    public void execute()
    {

    }

}
