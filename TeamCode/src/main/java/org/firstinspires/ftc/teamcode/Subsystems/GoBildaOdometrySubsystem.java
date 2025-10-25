package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class GoBildaOdometrySubsystem extends SubsystemBase
{
    GoBildaPinpointDriver odometry;
    private Pose2D pos;
    double globalHeading, relativeHeading, offset;

    public GoBildaOdometrySubsystem(GoBildaPinpointDriver odometry)
    {
        this.odometry = odometry;

        configureOdometry();
        odometry.setPosition(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));
    }

    @Override
    public void periodic()
    {
        pos = odometry.getPosition();
        odometry.update();
    }

    public void recalibrateIMU()
    {
        odometry.recalibrateIMU();
    }

    public void resetPosAndIMU()
    {
        offset += getGyro_RelativeHeadingDegrees();
        odometry.resetPosAndIMU();
    }

    // Gets the Heading relative to where Gyro was last reset
    public double getGyro_RelativeHeadingDegrees()
    {
        return getGyro_AbsoluteHeadingDegrees() - offset;
    }

    // Gets the Current Heading
    public double getGyro_AbsoluteHeadingDegrees()
    {
        return odometry.getHeading(AngleUnit.DEGREES);
    }

    // Configures the Odometry
    public void configureOdometry()
    {
        odometry.setOffsets(-6, 9, DistanceUnit.INCH);
        odometry.setEncoderResolution( GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odometry.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.REVERSED,
                GoBildaPinpointDriver.EncoderDirection.REVERSED
        );
        odometry.resetPosAndIMU();
    }

    public double getPos2D_X()
    {
       return pos.getX(DistanceUnit.INCH);
    }

    public double getPos2D_Y()
    {
        return pos.getY(DistanceUnit.INCH);
    }

    public double getPos2D_Heading()
    {
        return pos.getHeading(AngleUnit.DEGREES);
    }

    public double getEncoder_X()
    {
        return odometry.getEncoderX();
    }

    public double getEncoder_Y()
    {
        return odometry.getEncoderY();
    }

    public Pose2D getPos()
    {
        return odometry.getPosition();
    }

}
