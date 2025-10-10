package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.SerialNumber;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Vision;
@TeleOp (name = "DefaultTeleOp")
public class OpMode extends CommandOpMode {

    //TODO Make Work
    private GamepadEx controller1, controller2;
    private MotorEx frontLeft, frontRight, backLeft, backRight;
    private Limelight3A vision;
    private Telemetry telemetry;
    @Override
    public void initialize() {
        // Declaration of Driver Controller: controller1
        // Declaration of Operator Controller: controller2
        GamepadEx controller1 = new GamepadEx(gamepad1); GamepadEx controller2 = new GamepadEx(gamepad2);

        Drivetrain drivetrain = new Drivetrain(
                new MotorEx(hardwareMap, "FrontLeftMotor"),
                new MotorEx(hardwareMap, "FrontRightMotor"),
                new MotorEx(hardwareMap, "BackLeftMotor"),
                new MotorEx(hardwareMap, "BackRightMotor")
                );

        // Initialize Vision
        Vision vision = new Vision();
        vision.init();

        drivetrain.setDefaultCommand(new Drive(drivetrain, controller1));
    }
}
