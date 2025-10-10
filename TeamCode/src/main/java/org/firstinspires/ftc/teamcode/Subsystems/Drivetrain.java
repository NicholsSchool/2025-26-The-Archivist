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
    private GyroEx gyro;

    public Drivetrain(MotorEx frontLeft, MotorEx frontRight, MotorEx backLeft, MotorEx backRight)
    {

        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        this.gyro = gyro;

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        frontLeft.setInverted(true);
        frontRight.setInverted(true);
        backLeft.setInverted(true);
        backRight.setInverted(true);

//        gyro.init();

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

    public void driveFieldOriented(DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier turn)
    {
        mecanum.driveFieldCentric(strafe.getAsDouble(), forward.getAsDouble(), turn.getAsDouble(), gyro.getHeading());
    }
    public enum motor
    {
        FRONT_LEFT_MOTOR,
        FRONT_RIGHT_MOTOR,
        BACK_LEFT_MOTOR,
        BACK_RIGHT_MOTOR
    }
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

}
