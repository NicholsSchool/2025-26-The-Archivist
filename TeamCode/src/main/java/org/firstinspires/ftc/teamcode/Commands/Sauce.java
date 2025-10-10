package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem.measurement.INCHES;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.PodOdometry;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

import java.util.function.DoubleSupplier;

public class Sauce extends CommandBase
{
    VisionSubsystem vision;
    Drivetrain driveTrain;
    PodOdometry odometry;

    DoubleSupplier forward, strafe;
    double sauce;
    double denominator = 1.4;

    public Sauce(Drivetrain driveTrain, VisionSubsystem vision, PodOdometry odometry, DoubleSupplier forward, DoubleSupplier strafe)
    {
        denominator = 1.0;

        this.forward = forward;
        this.strafe = strafe;

        this.vision = vision;
        this.driveTrain = driveTrain;
        this.odometry = odometry;

        addRequirements(driveTrain, vision, odometry);
    }

    @Override
    public void execute()
    {
        // TODO Finish Implementing Math
        vision.getAprilTags();
        sauce = Math.atan2(vision.getTargetX(INCHES), vision.getTargetDistance());
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
