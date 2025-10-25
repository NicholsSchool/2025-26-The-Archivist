package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.GoBildaOdometrySubsystem;

import java.util.function.DoubleSupplier;

public class DriveFieldOriented extends CommandBase
{

    Drivetrain driveTrain;
    GoBildaOdometrySubsystem odometry;
    DoubleSupplier forward, strafe, turn;

    public DriveFieldOriented(Drivetrain driveTrain, GoBildaOdometrySubsystem odometry, DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier turn)
    {
        this.forward = forward;
        this.strafe = strafe;
        this.turn = turn;

        this.driveTrain = driveTrain;
        this.odometry = odometry;

        addRequirements(driveTrain, odometry);
    }


    @Override
    public void execute()
    {
     driveTrain.driveFieldOriented(forward, strafe, turn, odometry.getPos2D_Heading());
    }
}
