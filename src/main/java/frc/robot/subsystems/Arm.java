/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.A_DriveArm;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX left = new TalonSRX(RobotMap.ARM_LEFT);
  private TalonSRX right = new TalonSRX(RobotMap.ARM_RIGHT);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new A_DriveArm());
  }

  public Arm(){
    left.setInverted(Constants.ARM_INVERT_L);
    right.setInverted(Constants.ARM_INVERT_R);

    left.setSensorPhase(Constants.ARM_LEFT_SENSOR_PHASE);
    right.setSensorPhase(Constants.ARM_RIGHT_SENSOR_PHASE);

    left.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_TIMEOUT);
    right.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_TIMEOUT);

    left.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);
    right.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);

    left.configContinuousCurrentLimit(Constants.ARM_CURRENT_LIMIT, Constants.ARM_TIMEOUT);
    right.configContinuousCurrentLimit(Constants.ARM_CURRENT_LIMIT, Constants.ARM_TIMEOUT);

    left.enableCurrentLimit(Constants.ARM_CURRENT_LIMIT_ENABLED);
    right.enableCurrentLimit(Constants.ARM_CURRENT_LIMIT_ENABLED);
  }

  public void driveArm(double speed) {
    left.set(ControlMode.PercentOutput, speed);
    right.set(ControlMode.PercentOutput, -speed);
  }
}