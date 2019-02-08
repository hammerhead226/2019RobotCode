package frc.robot.vision;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class GoToTarget extends Command {

    double initialHeading;
    double setpoint;
    double area;
    boolean isTarget;

    public GoToTarget() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        initialHeading = Robot.driveTrain.getYaw();
        setpoint = initialHeading + Limelight.getTargetAngle();
        area = Limelight.getContourArea();
        isTarget = Limelight.contourFound();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println(isTarget);
        if (isTarget) {
            Robot.driveTrain.tankDrive(setpoint/100, setpoint/100);
        } else {
            Robot.driveTrain.tankDrive(0, 0);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}