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
import frc.robot.commands.I_DriveIntake;;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX leftIntake = new TalonSRX(RobotMap.INTAKE_LEFT);
  private TalonSRX rightIntake = new TalonSRX(RobotMap.INTAKE_RIGHT);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new I_DriveIntake());
  }
  public Intake(){
    leftIntake.setInverted(Constants.INTAKE_INVERT_L);
    rightIntake.setInverted(Constants.INTAKE_INVERT_R);

    leftIntake.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_TIMEOUT);
    rightIntake.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_TIMEOUT);

    leftIntake.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);
    rightIntake.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);

    leftIntake.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_TIMEOUT);
    rightIntake.configContinuousCurrentLimit(Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_TIMEOUT);

    leftIntake.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);
    rightIntake.enableCurrentLimit(Constants.INTAKE_CURRENT_LIMIT_ENABLED);
  }
  public void driveIntake(double speed) {
    leftIntake.set(ControlMode.PercentOutput, speed);
    rightIntake.set(ControlMode.PercentOutput, -speed);
  }
}

