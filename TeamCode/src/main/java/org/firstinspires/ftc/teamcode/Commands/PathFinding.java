package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.PurePursuitCommand;
import com.arcrobotics.ftclib.purepursuit.Path;
import com.arcrobotics.ftclib.purepursuit.PathMotionProfile;
import com.arcrobotics.ftclib.purepursuit.Waypoint;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.GoBildaOdometrySubsystem;

public class PathFinding extends CommandBase
{
    private PathMotionProfile motionProfile;
    private Drivetrain driveTrain;
    private GoBildaOdometrySubsystem odometry;
    public PathFinding(Drivetrain driveTrain, GoBildaOdometrySubsystem odometry, Waypoint... waypoints)
    {
        this.driveTrain = driveTrain;
        this.odometry = odometry;


    }

    @Override
    public void execute()
    {


        //TODO Configure Movement and Turn Speed
        motionProfile.processAccelerate(new double[] {
                driveTrain.getMotorPower(Drivetrain.motor.FRONT_LEFT_MOTOR),
                driveTrain.getMotorPower(Drivetrain.motor.FRONT_RIGHT_MOTOR),
                driveTrain.getMotorPower(Drivetrain.motor.BACK_LEFT_MOTOR),
                driveTrain.getMotorPower(Drivetrain.motor.BACK_RIGHT_MOTOR)},
                -1,
                -1,
                -1
        );
        motionProfile.processDecelerate(new double[] {
                driveTrain.getMotorPower(Drivetrain.motor.FRONT_LEFT_MOTOR),
                driveTrain.getMotorPower(Drivetrain.motor.FRONT_RIGHT_MOTOR),
                driveTrain.getMotorPower(Drivetrain.motor.BACK_LEFT_MOTOR),
                driveTrain.getMotorPower(Drivetrain.motor.BACK_RIGHT_MOTOR)},
                -1,
                -1,
                -1
        );

    }
}
