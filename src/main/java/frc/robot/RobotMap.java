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
  public static int DT_FRONT_LEFT = 1;
  public static int DT_REAR_LEFT = 2;
  public static int DT_FRONT_RIGHT = 5;
  public static int DT_REAR_RIGHT = 6;

  public static int INTAKE = 7;
  public static int INTAKE_ROLLER = 13;

  public static int INTAKE_SHIFTER_1 = 1;
  public static int INTAKE_SHIFTER_2 = 6;

  public static int EL_ONE = 3;
  public static int EL_TWO = 4;
  public static int EL_THREE = 11;
  public static int EL_FOUR = 12;

  public static final int EL_ROLLER = 16;

  public static int ARM_SHIFTER_1 = 0;
  public static int ARM_SHIFTER_2 = 7;

  public static int ARM_MAIN = 5;
  public static int ARM_FOLLOWER = 15;

}
