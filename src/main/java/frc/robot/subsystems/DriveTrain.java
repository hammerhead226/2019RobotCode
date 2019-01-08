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

  private TalonSRX frontLeft = new TalonSRX(RobotMap.FRONT_LEFT_MOTOR);
	private TalonSRX frontRight = new TalonSRX(RobotMap.FRONT_RIGHT_MOTOR);
	
	private TalonSRX rearLeft = new TalonSRX(RobotMap.REAR_LEFT_MOTOR);
	private TalonSRX rearRight = new TalonSRX(RobotMap.REAR_RIGHT_MOTOR);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new CheesyDrive());
  }

  public void cheesyDrive(double throttle, double turn) {
    frontLeft.set(ControlMode.PercentOutput, -throttle + turn);
		rearLeft.set(ControlMode.PercentOutput, -throttle + turn);

		frontRight.set(ControlMode.PercentOutput, throttle + turn);
		rearRight.set(ControlMode.PercentOutput, throttle  + turn);
    }
}