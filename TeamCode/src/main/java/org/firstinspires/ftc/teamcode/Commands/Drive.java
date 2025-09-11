package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class Drive extends CommandBase
{
    MotorEx frontLeft, frontRight, backLeft, backRight;
    Drivetrain driveTrain;

    public Drive(MotorEx frontLeft, MotorEx frontRight, MotorEx backLeft, MotorEx backRight, Drivetrain driveTrain)
    {

        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        this.driveTrain = driveTrain;

        addRequirements(driveTrain);

    }

    @Override
    public void execute()
    {
        driveTrain.drive();
    }
}
