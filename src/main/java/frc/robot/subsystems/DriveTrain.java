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
import frc.robot.commands.DT_CheesyDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX frontLeft = new TalonSRX(RobotMap.DT_FRONT_LEFT);
  private TalonSRX frontRight = new TalonSRX(RobotMap.DT_REAR_LEFT);

  private TalonSRX rearLeft = new TalonSRX(RobotMap.DT_FRONT_RIGHT);
  private TalonSRX rearRight = new TalonSRX(RobotMap.DT_FRONT_RIGHT);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DT_CheesyDrive());
  }

  public DriveTrain(){
    frontLeft.setInverted(Constants.DT_INVERT_L);
    rearLeft.setInverted(Constants.DT_INVERT_L);

    frontRight.setInverted(Constants.DT_INVERT_R);
    rearRight.setInverted(Constants.DT_INVERT_R);

    rearLeft.follow(frontLeft);
    rearRight.follow(frontRight);

    frontLeft.setSensorPhase(Constants.DT_LEFT_SENSOR_PHASE);
    frontRight.setSensorPhase(Constants.DT_RIGHT_SENSOR_PHASE);

    frontLeft.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.DT_TIMEOUT);
    rearLeft.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.DT_TIMEOUT);
    frontRight.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.DT_TIMEOUT);
    rearRight.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.DT_TIMEOUT);

    frontLeft.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);
    rearLeft.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);
    frontRight.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);
    rearRight.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);

    frontLeft.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.DT_TIMEOUT);
    rearLeft.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.DT_TIMEOUT);
    frontRight.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.DT_TIMEOUT);
    rearRight.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.DT_TIMEOUT);

    frontLeft.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);
    rearLeft.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);
    frontRight.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);
    rearRight.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);

    frontLeft.configOpenloopRamp(Constants.DT_VOLTAGE_RAMP_RATE, Constants.DT_TIMEOUT);
    frontRight.configOpenloopRamp(Constants.DT_VOLTAGE_RAMP_RATE, Constants.DT_TIMEOUT);
    rearLeft.configOpenloopRamp(Constants.DT_VOLTAGE_RAMP_RATE, Constants.DT_TIMEOUT);
    rearRight.configOpenloopRamp(Constants.DT_VOLTAGE_RAMP_RATE, Constants.DT_TIMEOUT);
  }
  
  private double limit(double value) {
    if (value > 1.0) {
      return 1.0;
    }
    if (value < -1.0) {
      return -1.0;
    }
    return value;
  }

  public void cheesyDrive(double xSpeed, double zRotation) {

    xSpeed = limit(xSpeed);

    zRotation = limit(zRotation);

    // Square the inputs (while preserving the sign) to increase fine control
    // while permitting full power.

    xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
    zRotation = Math.copySign(zRotation * zRotation, zRotation);

    double leftMotorOutput;
    double rightMotorOutput;

    double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

    if (xSpeed >= 0.0) {
      // First quadrant, else second quadrant
      if (zRotation >= 0.0) {
        leftMotorOutput = maxInput;
        rightMotorOutput = xSpeed - zRotation;
      } else {
        leftMotorOutput = xSpeed + zRotation;
        rightMotorOutput = maxInput;
      }
    } else {
      // Third quadrant, else fourth quadrant
      if (zRotation >= 0.0) {
        leftMotorOutput = xSpeed + zRotation;
        rightMotorOutput = maxInput;
      } else {
        leftMotorOutput = maxInput;
        rightMotorOutput = xSpeed - zRotation;
      }
    }

    frontLeft.set(ControlMode.PercentOutput, limit(leftMotorOutput));
    frontRight.set(ControlMode.PercentOutput, limit(rightMotorOutput));

  }
}