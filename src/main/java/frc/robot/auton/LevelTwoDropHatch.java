/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class LevelTwoDropHatch extends TimedCommand {
  /**
   * Add your docs here.
   */
  Timer timer = new Timer();
  public LevelTwoDropHatch(double timeout) {
    super(timeout);
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.intake.driveIntake(-0.5);
    if(timer.get() <=1.0){
    Robot.driveTrain.cheesyDrive(0.85, 0);
    }else{
      Robot.driveTrain.cheesyDrive(0, 0);
    }
  }

  // Called once after timeout
  @Override
  protected void end() {
    // Robot.intake.driveIntake(0);
    // Robot.driveTrain.cheesyDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.driveTrain.cheesyDrive(0, 0);
  }
}
