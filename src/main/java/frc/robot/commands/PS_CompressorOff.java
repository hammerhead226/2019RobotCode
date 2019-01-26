package frc.robot.commands;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 *
 */
public class PS_CompressorOff extends InstantCommand {

    public PS_CompressorOff() {
        super();
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pneumaticsSystem);
    }

    // Called once when the command executes
    protected void initialize() {
        Robot.pneumaticsSystem.compressorOff();
    }

}