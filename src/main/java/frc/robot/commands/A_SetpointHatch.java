/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Arm.ArmSetpoint;

/**
 * Add your docs here.
 */
public class A_SetpointHatch extends InstantCommand {
  /**
   * Add your docs here.
   */
  public A_SetpointHatch() {
    super();
    requires(Robot.arm);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.arm.setArmSetpoint(ArmSetpoint.HATCH_SCORING);
  }

}