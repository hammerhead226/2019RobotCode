/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static int DT_FRONT_LEFT = 7;
  public static int DT_REAR_LEFT = 16;
  public static int DT_FRONT_RIGHT = 6;
  public static int DT_REAR_RIGHT = 15;

  public static int INTAKE_LEFT = 13;
  public static int INTAKE_RIGHT = 14;

  public static int INTAKE_LEFT_SHIFTER_1 = 2;
  public static int INTAKE_LEFT_SHIFTER_2 = 5;
  public static int INTAKE_RIGHT_SHIFTER_1 = 1;
  public static int INTAKE_RIGHT_SHIFTER_2 = 6;

  public static int EL_ONE = 1;
  public static int EL_TWO = 2;
  public static int EL_THREE = 11;
  public static int EL_FOUR = 12;

  public static final int EL_ROLLER = 4;

  public static int ARM_SHIFTER_1 = 0;
  public static int ARM_SHIFTER_2 = 7;

  public static int ARM_LEFT = 5;
  public static int ARM_RIGHT = 3;

}
