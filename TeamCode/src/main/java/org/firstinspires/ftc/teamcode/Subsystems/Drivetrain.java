package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

public class Drivetrain extends SubsystemBase {

    private MecanumDrive mecanum;
    private MotorEx frontLeft, frontRight, backLeft, backRight;
    private Telemetry telemetry;

    public Drivetrain(MotorEx frontLeft, MotorEx frontRight, MotorEx backLeft, MotorEx backRight)
    {

        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;



       mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    @Override
    public void periodic()
    {
        // This method will be called once per scheduler run
        telemetry.addData("flMotorVel", frontLeft.getVelocity());
        telemetry.addData("frMotorVel", frontRight.getVelocity());
        telemetry.addData("blMotorVel", backLeft.getVelocity());
        telemetry.addData("brMotorVel", backRight.getVelocity());
    }

    public void drive(DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier turn)
    {
        mecanum.driveRobotCentric(forward.getAsDouble(), strafe.getAsDouble(), turn.getAsDouble());
    }

    public void driveMotorPower(Double frontLeft, Double frontRight, Double backLeft, Double backRight)
    {
        mecanum.driveWithMotorPowers(frontLeft, frontRight, backLeft, backRight);
    }
}
