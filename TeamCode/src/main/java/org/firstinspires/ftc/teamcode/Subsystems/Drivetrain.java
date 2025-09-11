package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain extends SubsystemBase {

    private final MecanumDrive mecanum;
    private final MotorEx frontLeft, frontRight, backLeft, backRight;

    public Drivetrain(final HardwareMap hm)
    {
        frontLeft = hm.get(MotorEx.class, "FrontLeftMotor");
        frontRight = hm.get(MotorEx.class, "FrontRightMotor");
        backLeft = hm.get(MotorEx.class, "BackLeftMotor");
        backRight = hm.get(MotorEx.class, "BackRightMotor");

       mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    @Override
    public void periodic()
    {
        // This method will be called once per scheduler run
    }

    public void drive(double forward, double strafe, double turn)
    {
        mecanum.driveRobotCentric(forward, strafe, turn);
    }
}
