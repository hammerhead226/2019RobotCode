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
import frc.robot.RobotMap;
import frc.robot.commands.CheesyDrive;

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
    setDefaultCommand(new CheesyDrive());
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
      }
    }

    frontLeft.set(ControlMode.PercentOutput, limit(leftMotorOutput));
    frontRight.set(ControlMode.PercentOutput, limit(rightMotorOutput));

    rearLeft.follow(frontLeft);
    rearRight.follow(frontRight);
  }
}