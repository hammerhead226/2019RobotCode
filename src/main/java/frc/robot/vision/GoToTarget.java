package frc.robot.vision;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 *
 */
public class GoToTarget extends Command {

    double initialHeading;
    double setpoint;
    double area;
    boolean isTarget;
    double divider;

    public GoToTarget() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        area = Limelight.getContourArea();
        isTarget = Limelight.contourFound();
        divider = 1.1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        setpoint = Limelight.getTargetAngle();
        SmartDashboard.putNumber("setpoint", setpoint);
        System.out.println(isTarget);
        if (isTarget) {
            Robot.driveTrain.tankDrive(-Limelight.sigmoid(setpoint)/divider, -Limelight.sigmoid(setpoint)/divider);
        } else {
            Robot.driveTrain.tankDrive(0, 0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return setpoint <= Constants.VISION_TOLERANCE && setpoint >= -Constants.VISION_TOLERANCE;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}