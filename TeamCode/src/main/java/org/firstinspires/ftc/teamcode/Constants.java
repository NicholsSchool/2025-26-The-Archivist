package org.firstinspires.ftc.teamcode;

public final class Constants
{
    //Reminder field is 144 Inch by 144 Inch
    private Constants() {}
    public static class vision
    {
        //TODO Add vision constants
        //Measurements are recorded in Inches
        public static final double kTargetHeight = 29.5;
        //TODO remeasure LimeLightHeight
        public static final double kLimeLightHeightInches = 5;
        public static final double kLimeLightMountAngleDegree = 15.3779;

    }

    public static class robotDimension
    {
        //TODO Add the Constants for robot measurements
        //Measurements are recorded in Inches
        public static final double kWheelRadius = 1.41;
        public static final double kOdometryTrackWidth = -1;
        public static final double kWheelTPI = -1;
    }

    public static class kinematics
    {
        public static final double kMaxVelocity = -1;
        public static final double kMaxAcceleration = -1;
        public static final double kTimeToAccelerate = kMaxVelocity / kMaxAcceleration;
    }
    public static class aprilTagID
    {
        // Inner Square 6.5 In
        // Outer Square 8.125 In
        public static final double kAprilTagDimension = 8.125;
        public static final int kAprilTagBlueAlliance = 20;
        public static final int kAprilTagRedAlliance = 24;
        public static final int kAprilTagDecode1 = 21; // G P P
        public static final int kAprilTagDecode2 = 22; // P G P
        public static final int kAprilTagDecode3 = 23; // P P G

    }
}
