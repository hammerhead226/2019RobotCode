/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DT_CheesyDrive extends Command {

  private boolean isDefense = false;
  private double[] holdPositions;

  public DT_CheesyDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.driver.getAButtonPressed()) {
      isDefense = !isDefense;
      if(isDefense) {
        holdPositions = Robot.driveTrain.getPositions();
      }
    }
    if(isDefense) {
      Robot.driveTrain.tankDrive(-(Robot.driveTrain.getPositions()[0] - holdPositions[0]) / 5, -(Robot.driveTrain.getPositions()[1] - holdPositions[1]) / 5);
    } else {
      Robot.driveTrain.cheesyDrive(0.85 * -Robot.m_oi.driver.getLeftJoystick_Y(), 0.75 * Robot.m_oi.driver.getRightJoystick_X());
    }
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
