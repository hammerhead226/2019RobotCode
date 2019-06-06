/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.A_SetEncoderUp;
import frc.robot.commands.A_SetpointBall;
import frc.robot.commands.A_SetpointHatch;
import frc.robot.commands.A_SetpointHigh;
import frc.robot.commands.A_SetpointRocket;
import frc.robot.commands.A_ZeroEncoder;
import frc.robot.commands.DT_Brake;
import frc.robot.commands.PS_ShiftIntake;
import frc.robot.commands.PS_ToggleCompressor;
import util.Controller;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Controller driver = new Controller(0);
  public Controller manip = new Controller(1);

  public OI(){
    driver.getYButton().whenPressed(new PS_ToggleCompressor());
    driver.getXButton().whileHeld(new DT_Brake());

    manip.getLBButton().whenPressed(new PS_ShiftIntake());

    manip.getSTARTButton().whenPressed(new A_ZeroEncoder());
    manip.getRBButton().whenPressed(new A_SetEncoderUp());

    manip.getXButton().whenPressed(new A_SetpointHatch());
    manip.getBButton().whenPressed(new A_SetpointRocket());
    manip.getAButton().whenPressed(new A_SetpointBall());
    manip.getYButton().whenPressed(new A_SetpointHigh());
  }

}
