package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.EventLoop;
import com.qualcomm.robotcore.eventloop.EventLoopManager;
import com.qualcomm.robotcore.robot.Robot;

public class OpModeHandler extends Robot
{
    //TODO Code Robot for handling OpModes
    Robot robot;
    public enum OpMode
    {
        BASIC_AUTO,
        BASIC_TELEOP
    }

    public OpModeHandler(EventLoopManager eventLoopManager, OpMode opMode) {
        super(eventLoopManager);

        switch(opMode)
        {
            case BASIC_AUTO:
            case BASIC_TELEOP:
            default:
        }
    }

}
