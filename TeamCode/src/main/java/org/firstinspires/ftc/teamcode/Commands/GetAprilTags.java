package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

public class GetAprilTags extends CommandBase
{
    VisionSubsystem vision;
    Telemetry telemetry;

    public GetAprilTags(VisionSubsystem vision)
    {
        this.vision = vision;

        addRequirements(vision);
    }

    @Override
    public void execute()
    {
        vision.getAprilTags();
    }
}
