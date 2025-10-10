package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.OdometrySubsystem;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.kinematics.DifferentialOdometry;
import com.arcrobotics.ftclib.kinematics.Odometry;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;

public class PodOdometry extends SubsystemBase
{
    GoBildaPinpointDriver odometry;

    // Distance between Odometry wheels
    double trackwidth = Constants.robotDimension.kOdometryTrackWidth;
    // Wheel Ticks per Inch
    double tpi = Constants.robotDimension.kWheelTPI;
    Pose2D pos;


    public PodOdometry(GoBildaPinpointDriver odometry)
    {

        this.odometry = odometry;

        pos = new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0);
        odometry.setPosition(pos);
    }

    public void configureOdometry()
    {
        odometry.setOffsets(0, 0 , DistanceUnit.INCH);
        odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odometry.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD
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

}
