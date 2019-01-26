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

  private TalonSRX leftArmMotor = new TalonSRX(RobotMap.ARM_LEFT);
  private TalonSRX rightArmMotor = new TalonSRX(RobotMap.ARM_RIGHT);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new A_DriveArm());
  }

  public Arm(){
    leftArmMotor.setInverted(Constants.ARM_INVERT_L);
    rightArmMotor.setInverted(Constants.ARM_INVERT_R);

    leftArmMotor.setSensorPhase(Constants.ARM_LEFT_SENSOR_PHASE);
    rightArmMotor.setSensorPhase(Constants.ARM_RIGHT_SENSOR_PHASE);

    leftArmMotor.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_TIMEOUT);
    rightArmMotor.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_TIMEOUT);

    leftArmMotor.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);
    rightArmMotor.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);

    leftArmMotor.configContinuousCurrentLimit(Constants.ARM_CURRENT_LIMIT, Constants.ARM_TIMEOUT);
    rightArmMotor.configContinuousCurrentLimit(Constants.ARM_CURRENT_LIMIT, Constants.ARM_TIMEOUT);

    leftArmMotor.enableCurrentLimit(Constants.ARM_CURRENT_LIMIT_ENABLED);
    rightArmMotor.enableCurrentLimit(Constants.ARM_CURRENT_LIMIT_ENABLED);
  }

  public void driveArm(double speed) {
    leftArmMotor.set(ControlMode.PercentOutput, speed);
    rightArmMotor.set(ControlMode.PercentOutput, -speed);
  }
}
