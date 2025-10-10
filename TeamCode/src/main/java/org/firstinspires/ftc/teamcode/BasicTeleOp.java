package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.GyroEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

//import org.firstinspires.ftc.teamcode.Commands.Sauce;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Drive;
import org.firstinspires.ftc.teamcode.Commands.DriveFieldOriented;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.PodOdometry;
import org.firstinspires.ftc.teamcode.Subsystems.TelemetrySubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSubsystem;

//import org.firstinspires.ftc.teamcode.Subsystems.Vision;
@TeleOp (name = "BasicTeleOp")
public class BasicTeleOp extends CommandOpMode {

    //TODO Make Work
    private GamepadEx controller1, controller2;
    private GyroEx gyro;
    private MotorEx frontLeft, frontRight, backLeft, backRight;
//    private Limelight3A vision;
    private TelemetrySubsystem telemetrySubsystem;
    private VisionSubsystem visionSubsystem;
    private Limelight3A vision;
    private PodOdometry odometrySubsystem;
    @Override
    public void initialize() {

        odometrySubsystem = new PodOdometry(hardwareMap.get(GoBildaPinpointDriver.class, "Odometry"));

        // Initialize Limelight3A for Vision
        vision = hardwareMap.get(Limelight3A.class, "Limelight3A");
        visionSubsystem = new VisionSubsystem(vision);

        // Declaration of Driver Controller: controller1
        // Declaration of Operator Controller: controller2
        GamepadEx controller1 = new GamepadEx(gamepad1); GamepadEx controller2 = new GamepadEx(gamepad2);

        // Initialize DriveTrain
        Drivetrain drivetrain = new Drivetrain(
                new MotorEx(hardwareMap, "FrontLeftMotor"),
                new MotorEx(hardwareMap, "FrontRightMotor"),
                new MotorEx(hardwareMap, "BackLeftMotor"),
                new MotorEx(hardwareMap, "BackRightMotor")
                );

        // Initialize Telemetry Subsystem
        telemetrySubsystem = new TelemetrySubsystem(drivetrain, visionSubsystem, telemetry, odometrySubsystem);
        register(telemetrySubsystem);

          drivetrain.setDefaultCommand(new Drive(drivetrain, () -> controller1.getLeftY(), () -> controller1.getLeftX(), () -> controller1.getRightX()));
//        controller1.getGamepadButton(GamepadKeys.Button.Y).toggleWhenPressed(new Sauce(drivetrain, vision, controller1));

    }
}
