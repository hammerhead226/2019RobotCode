/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.vision;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class ToggleCameraSetting extends InstantCommand {
  /**
   * Add your docs here.
   */
  public ToggleCameraSetting() {
    super();
    
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Limelight.changeCamMode();
  }

}
