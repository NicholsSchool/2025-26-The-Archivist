package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Vision;

public class GetAprilTags extends CommandBase
{
    Vision vision;
    Telemetry telemetry;

    public GetAprilTags(Vision vision)
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
