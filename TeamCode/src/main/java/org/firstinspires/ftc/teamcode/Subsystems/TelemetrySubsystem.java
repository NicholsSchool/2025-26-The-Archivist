package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem.measurement.PIXELS;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetrySubsystem extends SubsystemBase
{
    Telemetry telemetry;
    Drivetrain drivetrain;
    VisionSubsystem vision;
    PodOdometry odometry;
    public TelemetrySubsystem(Drivetrain drivetrain, VisionSubsystem vision, Telemetry telemetry, PodOdometry odometry)
    {
        this.drivetrain = drivetrain;
        this.vision = vision;
        this.telemetry = telemetry;
        this.odometry = odometry;
    }

    @Override
    public void periodic()
    {
        telemetry.addLine("Vision");
        telemetry.addData("Target Distance:", vision.getTargetDistance());
        telemetry.addData("Target X:", vision.getTargetX(PIXELS));
        telemetry.addLine("DriveTrain");
        telemetry.addData("FrontLeftMotorAccel:", drivetrain.getMotorAccel(Drivetrain.motor.FRONT_LEFT_MOTOR));
        telemetry.addData("FrontRightMotorAccel:", drivetrain.getMotorAccel(Drivetrain.motor.FRONT_RIGHT_MOTOR));
        telemetry.addData("BackLeftMotorAccel:", drivetrain.getMotorAccel(Drivetrain.motor.BACK_LEFT_MOTOR));
        telemetry.addData("BackRightMotorAccel:", drivetrain.getMotorAccel(Drivetrain.motor.BACK_RIGHT_MOTOR));
        telemetry.addLine("Odometry");
        telemetry.update();
    }
}
