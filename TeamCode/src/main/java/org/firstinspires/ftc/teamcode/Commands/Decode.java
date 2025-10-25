package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

public class Decode extends CommandBase
{
    VisionSubsystem vision;
    public Decode(VisionSubsystem vision)
    {
        this.vision = vision;

        addRequirements(vision);
    }

    @Override
    public void execute()
    {
        boolean isDecodeID = vision.getAprilTagID() != Constants.aprilTagID.kAprilTagBlueAlliance || vision.getAprilTagID() != Constants.aprilTagID.kAprilTagRedAlliance;
        if (isDecodeID)
        {
            vision.setDecodeID(vision.getAprilTagID());
            isFinished();
        }
    }
}
