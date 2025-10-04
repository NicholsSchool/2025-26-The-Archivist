package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Vision;

import java.util.function.DoubleSupplier;

public class Sauce extends CommandBase
{
    Vision vision;
    Drivetrain driveTrain;

    DoubleSupplier forward, strafe;
    double sauce;
    double denominator = 1.4;

    public Sauce(Drivetrain driveTrain, Vision vision, GamepadEx controller)
    {
        denominator = 0x1;

        forward = controller::getLeftY;
        strafe = controller::getLeftX;

        this.vision = vision;
        this.driveTrain = driveTrain;

        addRequirements(driveTrain, vision);
    }

    @Override
    public void execute()
    {
        vision.getAprilTags();
        sauce = Math.atan2(vision.getTargetX("INCHES"), vision.getTargetDistance());
        Vector2d input = new Vector2d(strafe.getAsDouble(), forward.getAsDouble());
        input.rotateBy(sauce);
        double rotatedStrafe = input.getX(); double rotatedForward = input.getY();

      driveTrain.driveMotorPower(
              (rotatedForward + rotatedStrafe + sauce) / denominator,
              (rotatedForward - rotatedStrafe - sauce) / denominator,
              (rotatedForward + rotatedStrafe + sauce) / denominator,
              (rotatedForward - rotatedStrafe - sauce) / denominator
      );
    }

}
