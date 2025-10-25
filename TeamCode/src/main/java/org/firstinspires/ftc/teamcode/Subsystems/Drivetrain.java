package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.GyroEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import java.util.function.DoubleSupplier;

public class Drivetrain extends SubsystemBase {

    private MecanumDrive mecanum;
    private MotorEx frontLeft, frontRight, backLeft, backRight;

    public Drivetrain(MotorEx frontLeft, MotorEx frontRight, MotorEx backLeft, MotorEx backRight)
    {

        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        frontLeft.setInverted(true);
        frontRight.setInverted(true);
        backLeft.setInverted(true);
        backRight.setInverted(true);

       mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);

    }

    @Override
    public void periodic()
    {
        // This method will be called once per scheduler run
    }

    public void drive(DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier turn)
    {
        mecanum.driveRobotCentric(strafe.getAsDouble(), forward.getAsDouble(), turn.getAsDouble());
    }

    public void driveMotorPower(Double frontLeft, Double frontRight, Double backLeft, Double backRight)
    {
        mecanum.driveWithMotorPowers(frontLeft, frontRight, backLeft, backRight);
    }

    public void driveFieldOriented(DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier turn, Double heading)
    {
        mecanum.driveFieldCentric(strafe.getAsDouble(), forward.getAsDouble(), turn.getAsDouble(), heading);
    }

    // Valid Motors that can be called
    public enum motor
    {
        FRONT_LEFT_MOTOR,
        FRONT_RIGHT_MOTOR,
        BACK_LEFT_MOTOR,
        BACK_RIGHT_MOTOR
    }

    // Gets the Acceleration of the Input Motor
    public double getMotorAccel(motor motor)
    {
        switch(motor)
        {
            case FRONT_LEFT_MOTOR: return frontLeft.getAcceleration();
            case FRONT_RIGHT_MOTOR: return frontRight.getAcceleration();
            case BACK_LEFT_MOTOR: return backLeft.getAcceleration();
            case BACK_RIGHT_MOTOR: return backRight.getAcceleration();
            default: throw new IllegalStateException("Invalid Motor Selection");
        }

    }
    // Gets the Power of the Input Motor
    public double getMotorPower(motor motor)
    {
        switch(motor)
        {
            case FRONT_LEFT_MOTOR: return frontLeft.get();
            case FRONT_RIGHT_MOTOR: return frontRight.get();
            case BACK_LEFT_MOTOR: return backLeft.get();
            case BACK_RIGHT_MOTOR: return backRight.get();
            default: throw new IllegalStateException("Invalid Motor Selection");
        }

    }

    // Get the Velocity of the Input Motor in ticks per second
    public double getMotorVel(motor motor)
    {
        switch(motor)
        {
            case FRONT_LEFT_MOTOR: return frontLeft.getVelocity();
            case FRONT_RIGHT_MOTOR: return frontRight.getVelocity();
            case BACK_LEFT_MOTOR: return backLeft.getVelocity();
            case BACK_RIGHT_MOTOR: return backRight.getVelocity();
            default: throw new IllegalStateException("Invalid Motor Selection");
        }
    }

    // Get the Rate of the Encoder of the Input Motor
    public double getMotorRate(motor motor)
    {
        switch(motor)
        {
            case FRONT_LEFT_MOTOR: return frontLeft.getRate();
            case FRONT_RIGHT_MOTOR: return frontRight.getRate();
            case BACK_LEFT_MOTOR: return backLeft.getRate();
            case BACK_RIGHT_MOTOR: return backRight.getRate();
            default: throw new IllegalStateException("Invalid Motor Selection");
        }
    }

    // Gets the counts per revolution of the Input Motor
    public double getMotorCPR(motor motor)
    {
        switch(motor)
        {
            case FRONT_LEFT_MOTOR: return frontLeft.getCPR();
            case FRONT_RIGHT_MOTOR: return frontRight.getCPR();
            case BACK_LEFT_MOTOR: return backLeft.getCPR();
            case BACK_RIGHT_MOTOR: return backRight.getCPR();
            default: throw new IllegalStateException("Invalid Motor Selection");
        }
    }
    // Gets the distance travelled of the Encoder of the Input Motor
    public double getMotorDistance(motor motor)
    {
        switch(motor)
        {
            case FRONT_LEFT_MOTOR: return frontLeft.getDistance();
            case FRONT_RIGHT_MOTOR: return frontRight.getDistance();
            case BACK_LEFT_MOTOR: return backLeft.getDistance();
            case BACK_RIGHT_MOTOR: return backRight.getDistance();
            default: throw new IllegalStateException("Invalid Motor Selection");
        }
    }
}
