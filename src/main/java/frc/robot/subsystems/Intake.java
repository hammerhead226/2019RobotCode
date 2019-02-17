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
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.I_DriveIntake;;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private VictorSPX leftIntake = new VictorSPX(RobotMap.INTAKE_LEFT);
  private VictorSPX rightIntake = new VictorSPX(RobotMap.INTAKE_RIGHT);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new I_DriveIntake());
  }

  public void log(){
    ShuffleboardTab intake = Shuffleboard.getTab("intake");
    Shuffleboard.selectTab("intake");
    intake.add("arm position", leftIntake.getSelectedSensorPosition()).withSize(4, 4).withPosition(0, 0);
  }

  public Intake(){
    leftIntake.setInverted(Constants.INTAKE_INVERT);
    rightIntake.setInverted(InvertType.OpposeMaster);

    leftIntake.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_TIMEOUT);
    rightIntake.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT, Constants.INTAKE_TIMEOUT);

    leftIntake.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);
    rightIntake.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_LIMIT_ENABLED);

    rightIntake.follow(leftIntake);
  }
  public void driveIntake(double speed) {
    leftIntake.set(ControlMode.PercentOutput, speed);
  }
}

