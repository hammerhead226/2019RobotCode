/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.A_DriveArm;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX main = new TalonSRX(RobotMap.ARM_MAIN);
  private VictorSPX follower = new VictorSPX(RobotMap.ARM_FOLLOWER);
  private int setpointPosition = ArmSetpoint.STRAIGHT_UP.position;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new A_DriveArm());
  }

  public void log(){
    SmartDashboard.putNumber("arm setpoint", setpointPosition);
    SmartDashboard.putNumber("arm absolute position", main.getSelectedSensorPosition());
    //SmartDashboard.putNumber("arm absolute position", main.getSensorCollection().getPulseWidthPosition());
    SmartDashboard.putNumber("target", main.getClosedLoopTarget());
    SmartDashboard.putNumber("error", main.getClosedLoopError());
  }

  public Arm() {
    follower.follow(main);

    main.config_kP(Constants.ARM_PIDSLOT_IDX, Constants.ARM_P);

    main.configForwardSoftLimitEnable(Constants.ARM_SOFT_LIMIT_ENABLED);
    main.configForwardSoftLimitThreshold(Constants.ARM_SOFT_LIMIT);
    
    main.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    main.setSensorPhase(Constants.ARM_SENSOR_PHASE);

    main.setInverted(Constants.ARM_INVERT);
    follower.setInverted(InvertType.OpposeMaster);

    main.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_TIMEOUT);
    follower.configVoltageCompSaturation(Constants.ARM_VOLTAGE_LIMIT, Constants.ARM_TIMEOUT);

    main.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);
    follower.enableVoltageCompensation(Constants.ARM_VOLTAGE_LIMIT_ENABLED);

    main.configPeakOutputForward(Constants.ARM_PEAK_OUTPUT);
    main.configPeakOutputReverse(-Constants.ARM_PEAK_OUTPUT);
    follower.configPeakOutputForward(Constants.ARM_PEAK_OUTPUT);
    follower.configPeakOutputReverse(-Constants.ARM_PEAK_OUTPUT);

    main.configContinuousCurrentLimit(Constants.ARM_CURRENT_LIMIT, Constants.ARM_TIMEOUT);
    main.enableCurrentLimit(Constants.ARM_CURRENT_LIMIT_ENABLED);

    main.setSelectedSensorPosition(ArmSetpoint.STRAIGHT_UP.position);
  }

  public enum ArmSetpoint {
    GROUND(0),BALL_INTAKE(100), ROCKET_CARGO(840), HATCH_SCORING(400),  STRAIGHT_UP(1150);
    public int position;

    private ArmSetpoint(int position) {
      this.position = position;
    }
  }

  public void setArmSetpoint(ArmSetpoint sp) {
    this.setpointPosition = sp.position;
  }

  public int getArmPos() {
    return main.getSelectedSensorPosition();
  }

  public void zeroEncoder() {
    main.setSelectedSensorPosition(0);
    setpointPosition = 0;
  }

  public void encoderUp(){
    main.setSelectedSensorPosition(ArmSetpoint.STRAIGHT_UP.position);
    setpointPosition = ArmSetpoint.STRAIGHT_UP.position;
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