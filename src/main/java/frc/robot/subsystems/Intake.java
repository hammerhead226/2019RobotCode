/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.I_DriveIntake;;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private VictorSPX intake = new VictorSPX(RobotMap.INTAKE);
  private VictorSPX roller = new VictorSPX(RobotMap.INTAKE_ROLLER);
  boolean rollers = false;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new I_DriveIntake());
  }

  public Intake() {
    intake.setInverted(Constants.INTAKE_INVERT);
    roller.setInverted(InvertType.FollowMaster);

    intake.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_TIMEOUT);
    roller.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_TIMEOUT);

    intake.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);
    roller.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);
  }

  public void driveIntake(double speed) {
    intake.set(ControlMode.PercentOutput, speed);
    roller.set(ControlMode.PercentOutput, speed);
  }
}
