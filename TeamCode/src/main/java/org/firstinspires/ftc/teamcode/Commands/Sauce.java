package org.firstinspires.ftc.teamcode.Commands;


import static org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem.Measurement.INCHES;


import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.GoBildaOdometrySubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

import java.util.function.DoubleSupplier;

public class Sauce extends CommandBase
{
    VisionSubsystem vision;
    Drivetrain driveTrain;
    GoBildaOdometrySubsystem odometry;

    DoubleSupplier forward, strafe, turn;
    double sauce;
    double denominator;

    public Sauce(Drivetrain driveTrain, VisionSubsystem vision, GoBildaOdometrySubsystem odometry, DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier turn)
    {
        denominator = 1.0;

        this.forward = forward;
        this.strafe = strafe;
        this.turn = turn;

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
              (-rotatedForward + rotatedStrafe + turn.getAsDouble() + sauce) / denominator,
              (rotatedForward + rotatedStrafe - turn.getAsDouble() - sauce) / denominator,
              (rotatedForward + rotatedStrafe + turn.getAsDouble() + sauce) / denominator,
              (-rotatedForward + rotatedStrafe - turn.getAsDouble() - sauce) / denominator
      );
    }

}
