/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Constants {
    public static final double DT_VOLTAGE_LIMIT = 11;
    public static final boolean DT_VOLTAGE_LIMIT_ENABLED = true;

    public static final int DT_CURRENT_LIMIT = 30;
    public static final boolean DT_CURRENT_LIMIT_ENABLED = false;

    public static final boolean DT_INVERT_L = false;
    public static final boolean DT_INVERT_R = false;

    public static final int DT_LEFT_PIDSLOT_IDX = 0;
    public static final int DT_RIGHT_PIDSLOT_IDX = 0;

    public static final int DT_TIMEOUT = 10;

    public static final boolean DT_LEFT_SENSOR_PHASE = true;
    public static final boolean DT_RIGHT_SENSOR_PHASE = true;

    public static final double DT_VOLTAGE_RAMP_RATE = 0.1;
}
