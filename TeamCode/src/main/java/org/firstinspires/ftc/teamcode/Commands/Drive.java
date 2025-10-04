package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class Drive extends CommandBase
{

    Drivetrain driveTrain;
    DoubleSupplier forward, strafe, turn;

    public Drive(Drivetrain driveTrain, GamepadEx controller)
    {
        forward = controller::getLeftY;
        strafe = controller::getLeftX;
        turn = controller::getRightX;

        this.driveTrain = driveTrain;

        addRequirements(driveTrain);
    }


    @Override
    public void execute()
    {
     driveTrain.drive(forward, strafe, turn);
    }
}
