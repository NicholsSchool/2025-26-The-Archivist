package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

public class GetPurpleBallColorDetection extends CommandBase
{
    VisionSubsystem vision;
    public GetPurpleBallColorDetection(VisionSubsystem vision)
    {
        this.vision = vision;

        addRequirements(vision);
    }

    @Override
    public void execute()
    {
        vision.getBallColorDetector('P');
    }
}
