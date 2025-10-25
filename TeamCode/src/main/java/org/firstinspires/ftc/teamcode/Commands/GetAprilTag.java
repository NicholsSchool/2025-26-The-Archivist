package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

public class GetAprilTag extends CommandBase
{
    VisionSubsystem vision;
    int aprilTagID;

    public GetAprilTag(VisionSubsystem vision, int aprilTagID)
    {
        this.vision = vision;
        this.aprilTagID = aprilTagID;

        addRequirements(vision);
    }

    @Override
    public void execute()
    {
        if (vision.isAprilTag(aprilTagID))
        {
            vision.getAprilTags();
        }
    }
}
