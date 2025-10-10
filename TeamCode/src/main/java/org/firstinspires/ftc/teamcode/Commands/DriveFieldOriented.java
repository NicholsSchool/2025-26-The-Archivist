package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

public class DriveFieldOriented extends CommandBase
{

    Drivetrain driveTrain;
    DoubleSupplier forward, strafe, turn;

    public DriveFieldOriented(Drivetrain driveTrain, DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier turn)
    {
        this.forward = forward;
        this.strafe = strafe;
        this.turn = turn;

        this.driveTrain = driveTrain;

        addRequirements(driveTrain);
    }


    @Override
    public void execute()
    {
     driveTrain.driveFieldOriented(forward, strafe, turn);
    }
}
