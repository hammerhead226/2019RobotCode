package frc.robot.auton;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 *
 */
public class ToggleProfileRecording extends InstantCommand {

    public ToggleProfileRecording() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
    }

    // Called once when the command executes
    protected void initialize() {
        Robot.driveTrain.toggleProfileRecording();
    }

}