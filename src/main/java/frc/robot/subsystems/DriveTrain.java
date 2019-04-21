/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DT_CheesyDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private CANSparkMax frontLeft = new CANSparkMax(RobotMap.DT_FRONT_LEFT, MotorType.kBrushless);
  private CANSparkMax frontRight = new CANSparkMax(RobotMap.DT_FRONT_RIGHT, MotorType.kBrushless);
  private CANSparkMax rearLeft = new CANSparkMax(RobotMap.DT_REAR_LEFT, MotorType.kBrushless);
  private CANSparkMax rearRight = new CANSparkMax(RobotMap.DT_REAR_RIGHT, MotorType.kBrushless);

  public void log(){
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DT_CheesyDrive());
  }

  public DriveTrain() {
    rearLeft.follow(frontLeft);
    rearRight.follow(frontRight);

    frontLeft.setIdleMode(IdleMode.kBrake);
    frontLeft.setIdleMode(IdleMode.kBrake);
    frontLeft.setIdleMode(IdleMode.kBrake);
    frontLeft.setIdleMode(IdleMode.kBrake);

    frontLeft.setInverted(Constants.DT_INVERT_L);
    rearLeft.setInverted(Constants.DT_INVERT_L);
    frontRight.setInverted(Constants.DT_INVERT_R);
    rearRight.setInverted(Constants.DT_INVERT_R);

    frontLeft.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT);
    rearLeft.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT);
    frontRight.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT);
    rearRight.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT);

    frontLeft.setSmartCurrentLimit(Constants.DT_CURRENT_LIMIT);
    frontRight.setSmartCurrentLimit(Constants.DT_CURRENT_LIMIT);
    rearLeft.setSmartCurrentLimit(Constants.DT_CURRENT_LIMIT);
    rearRight.setSmartCurrentLimit(Constants.DT_CURRENT_LIMIT);

    frontLeft.setOpenLoopRampRate(Constants.DT_VOLTAGE_RAMP_RATE);
    frontRight.setOpenLoopRampRate(Constants.DT_VOLTAGE_RAMP_RATE);
    rearLeft.setOpenLoopRampRate(Constants.DT_VOLTAGE_RAMP_RATE);
    rearRight.setOpenLoopRampRate(Constants.DT_VOLTAGE_RAMP_RATE);

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

  public void tankDrive(double left, double right) {
    frontLeft.set(left);
    frontRight.set(right);
  }

  private boolean slow = false;

  
  public void cheesyDrive(double xSpeed, double zRotation) {

    if(Robot.m_oi.driver.getAButtonPressed(0.25)){
      slow = !slow;
    }

    if(slow){
      xSpeed *= 0.5;
      zRotation *= 0.8;
    }

    xSpeed = limit(xSpeed);
    zRotation = limit(zRotation);

    xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
    zRotation = Math.copySign(zRotation * zRotation, zRotation);

    double leftMotorOutput;
    double rightMotorOutput;

    double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), zRotation);

    if (xSpeed >= 0.0) {
      // First quadrant, else second quadrant
      if (zRotation >= 0.0) {
        leftMotorOutput = maxInput;
        rightMotorOutput = zRotation - xSpeed;
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
        rightMotorOutput = zRotation - xSpeed;
      }
    }

    frontLeft.set(limit(leftMotorOutput));
    frontRight.set(limit(rightMotorOutput));

  }

  public void arcadeDrive(double throttle, double turn) {
    frontLeft.set(throttle - turn);
    frontRight.set(throttle + turn);
  }

  public void neutralOutput() {
    frontLeft.stopMotor();
    frontRight.stopMotor();
    rearLeft.stopMotor();
    rearRight.stopMotor();
  }

  public void zeroEncoders() {
    frontLeft.setEncPosition(0);
    frontRight.setEncPosition(0);
    System.out.println("Drivetrain encoders zeroed.");
  }

}