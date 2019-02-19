/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.A_SetpointBall;
import frc.robot.commands.A_SetpointGround;
import frc.robot.commands.A_SetpointHatch;
import frc.robot.commands.A_SetpointHigh;
import frc.robot.commands.A_ZeroEncoder;
import frc.robot.commands.PS_CompressorOff;
import frc.robot.commands.PS_CompressorOn;
import frc.robot.commands.PS_ShiftArm;
import frc.robot.commands.PS_ShiftIntake;
import frc.robot.vision.GoToTarget;
import frc.robot.vision.ToggleCameraSetting;
import util.Controller;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public Controller driver = new Controller(0);
  public Controller manip = new Controller(1);

  public OI(){
    driver.getXButton().whenPressed(new PS_CompressorOff());
    driver.getYButton().whenPressed(new PS_CompressorOn());

    driver.getSTARTButton().whenPressed(new ToggleCameraSetting());
    driver.getAButton().whenPressed(new GoToTarget());

    manip.getRBButton().whenPressed(new PS_ShiftArm());
    manip.getLBButton().whenPressed(new PS_ShiftIntake());

    manip.getSTARTButton().whenPressed(new A_ZeroEncoder());

    manip.getAButton().whenPressed(new A_SetpointGround());
    manip.getXButton().whenPressed(new A_SetpointHatch());
    manip.getBButton().whenPressed(new A_SetpointBall());
    //manip.getYButton().whenPressed(new A_SetpointHigh());
  }

}
