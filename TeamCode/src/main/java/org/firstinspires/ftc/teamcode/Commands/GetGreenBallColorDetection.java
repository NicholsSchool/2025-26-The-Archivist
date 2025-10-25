package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

public class GetGreenBallColorDetection extends CommandBase
{
    VisionSubsystem vision;
    public GetGreenBallColorDetection(VisionSubsystem vision)
    {
        this.vision = vision;

        addRequirements(vision);
    }

    @Override
    public void execute()
    {
        vision.getBallColorDetector('G');
    }
}
