/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.*;
import frc.robot.*;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  private TalonSRX one = new TalonSRX(RobotMap.EL_ONE);
  private TalonSRX two = new TalonSRX(RobotMap.EL_TWO);
  private TalonSRX three = new TalonSRX(RobotMap.EL_THREE);
  private TalonSRX four = new TalonSRX(RobotMap.EL_FOUR);

  private int position = 0;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new EL_Drive());
  }

  public Elevator(){
    one.setInverted(Constants.EL_INVERT_ONE);
    three.setInverted(Constants.EL_INVERT_THREE);
    two.setInverted(Constants.EL_INVERT_TWO);
    four.setInverted(Constants.EL_INVERT_FOUR);

    four.follow(one);
    three.follow(one);
    two.follow(one);

    one.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.EL_PIDSLOT_IDX, Constants.EL_TIMEOUT);
    one.setSensorPhase(Constants.EL_SENSOR_PHASE);
    one.setSelectedSensorPosition(0, Constants.EL_PIDSLOT_IDX, Constants.EL_TIMEOUT);

    one.configVoltageCompSaturation(Constants.EL_VOLTAGE_LIMIT, Constants.EL_TIMEOUT);
    three.configVoltageCompSaturation(Constants.EL_VOLTAGE_LIMIT, Constants.EL_TIMEOUT);
    two.configVoltageCompSaturation(Constants.EL_VOLTAGE_LIMIT, Constants.EL_TIMEOUT);
    four.configVoltageCompSaturation(Constants.EL_VOLTAGE_LIMIT, Constants.EL_TIMEOUT);

    one.enableVoltageCompensation(Constants.EL_VOLTAGE_LIMIT_ENABLED);
    three.enableVoltageCompensation(Constants.EL_VOLTAGE_LIMIT_ENABLED);
    two.enableVoltageCompensation(Constants.EL_VOLTAGE_LIMIT_ENABLED);
    four.enableVoltageCompensation(Constants.EL_VOLTAGE_LIMIT_ENABLED);

    one.configContinuousCurrentLimit(Constants.EL_CURRENT_LIMIT, Constants.EL_TIMEOUT);
    three.configContinuousCurrentLimit(Constants.EL_CURRENT_LIMIT, Constants.EL_TIMEOUT);
    two.configContinuousCurrentLimit(Constants.EL_CURRENT_LIMIT, Constants.EL_TIMEOUT);
    four.configContinuousCurrentLimit(Constants.EL_CURRENT_LIMIT, Constants.EL_TIMEOUT);

    one.enableCurrentLimit(Constants.EL_CURRENT_LIMIT_ENABLED);
    three.enableCurrentLimit(Constants.EL_CURRENT_LIMIT_ENABLED);
    two.enableCurrentLimit(Constants.EL_CURRENT_LIMIT_ENABLED);
    four.enableCurrentLimit(Constants.EL_CURRENT_LIMIT_ENABLED);

    one.configOpenloopRamp(Constants.EL_VOLTAGE_RAMP_RATE, Constants.EL_TIMEOUT);
    two.configOpenloopRamp(Constants.EL_VOLTAGE_RAMP_RATE, Constants.EL_TIMEOUT);
    three.configOpenloopRamp(Constants.EL_VOLTAGE_RAMP_RATE, Constants.EL_TIMEOUT);
    four.configOpenloopRamp(Constants.EL_VOLTAGE_RAMP_RATE, Constants.EL_TIMEOUT);
  }
  
  public void drive(double drive){
    if(drive == 0){
      one.set(ControlMode.Position, position);
    } else {
      one.set(ControlMode.PercentOutput, drive);
      updatePosition();
    }
  }

  private void updatePosition(){
    position = one.getSelectedSensorPosition();
  }
}
