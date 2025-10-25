package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem.Measurement.PIXELS;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetrySubsystem extends SubsystemBase
{
    private Telemetry telemetry;
    private Drivetrain drivetrain;
    private VisionSubsystem vision;
    private GoBildaOdometrySubsystem odometry;
    public TelemetrySubsystem(Drivetrain drivetrain, VisionSubsystem vision, Telemetry telemetry, GoBildaOdometrySubsystem odometry)
    {
        this.drivetrain = drivetrain;
        this.vision = vision;
        this.telemetry = telemetry;
        this.odometry = odometry;

        telemetry.setMsTransmissionInterval(50);
    }

    @Override
    public void periodic()
    {
        // Telemetry Data for Vision
        telemetry.addLine("Vision");
        telemetry.addData("Target Distance", vision.getTargetDistance());
        telemetry.addData("Target X", vision.getTargetX(PIXELS));
        telemetry.addData("Target Y", vision.getTargetY(PIXELS));
        telemetry.addData("Target AprilTagID", vision.getAprilTagID());
        telemetry.addData("Decode ID", vision.getDecodeSequence());

        // Telemetry Data for Drivetrain
        telemetry.addLine("DriveTrain");

        // Telemetry Data for Odometry and IMU
        telemetry.addLine("Odometry");
        telemetry.addData("Pos2D X", odometry.getPos2D_X());
        telemetry.addData("Pos2D Y", odometry.getPos2D_Y());
        telemetry.addData("Pos2D Heading", odometry.getPos2D_Heading());

        telemetry.update();
    }

}
