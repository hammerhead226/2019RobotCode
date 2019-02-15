/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

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

  private TalonSRX main = new TalonSRX(RobotMap.ARM_LEFT);
  private TalonSRX follower = new TalonSRX(RobotMap.ARM_RIGHT);
  private int setpointPosition = ArmSetpoint.STRAIGHT_UP.position;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new A_DriveArm());
  }

  public Arm(){
    follower.follow(main);

    main.setInverted(Constants.ARM_INVERT);
    follower.setInverted(InvertType.FollowMaster);

    main.setSensorPhase(Constants.ARM_LEFT_SENSOR_PHASE);
    follower.setSensorPhase(Constants.ARM_RIGHT_SENSOR_PHASE);

    main.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_TIMEOUT);
    follower.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_TIMEOUT);

    main.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);
    follower.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);

    main.configContinuousCurrentLimit(Constants.ARM_CURRENT_LIMIT, Constants.ARM_TIMEOUT);
    main.enableCurrentLimit(Constants.ARM_CURRENT_LIMIT_ENABLED);
  }

  public enum ArmSetpoint{
    DISK_INTAKE(0), BALL_INTAKE(0), STRAIGHT_UP(0), HATCH_SCORING(0);
    public int position;
    private ArmSetpoint(int position){
      this.position = position;
    }
  }

  public void setArmSetpoint(ArmSetpoint sp) {
    this.setpointPosition = sp.position;
  }

  public int getArmPos() {
    return main.getSelectedSensorPosition();
  }

  public void driveArm(double speed) {
    if (speed != 0) {
      main.set(ControlMode.PercentOutput, speed);
      setpointPosition = getArmPos();
    } else {
      main.set(ControlMode.Position, setpointPosition);
    }
  }
}
