package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.Commands.GetAprilTags;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.GoBildaOdometrySubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TelemetrySubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

public class BasicAuto extends CommandOpMode
{
    private TelemetrySubsystem telemetrySubsystem;
    private VisionSubsystem visionSubsystem;
    private Limelight3A vision;
    private GoBildaOdometrySubsystem goBildaOdometrySubsystem;
    @Override
    public void initialize()
    {
        CommandScheduler.getInstance().run();

        goBildaOdometrySubsystem = new GoBildaOdometrySubsystem(hardwareMap.get(GoBildaPinpointDriver.class, "Odometry"));

        // Initialize Limelight3A for Vision
        vision = hardwareMap.get(Limelight3A.class, "Limelight3A");
        visionSubsystem = new VisionSubsystem(vision);

        // Initialize DriveTrain
        Drivetrain drivetrain = new Drivetrain(
                new MotorEx(hardwareMap, "FrontLeftMotor"),
                new MotorEx(hardwareMap, "FrontRightMotor"),
                new MotorEx(hardwareMap, "BackLeftMotor"),
                new MotorEx(hardwareMap, "BackRightMotor")
        );

        // Initialize Telemetry Subsystem
        telemetrySubsystem = new TelemetrySubsystem(drivetrain, visionSubsystem, telemetry, goBildaOdometrySubsystem);

        // Periodically Called Subsystems
        register(
                telemetrySubsystem,
                goBildaOdometrySubsystem
        );
        // Periodically Called Commands
        schedule(
                new GetAprilTags(visionSubsystem)
        );
    }
}
