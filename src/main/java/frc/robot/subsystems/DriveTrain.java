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
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import org.hammerhead226.sharkmacro.actions.ActionListParser;
import org.hammerhead226.sharkmacro.actions.ActionRecorder;
import org.hammerhead226.sharkmacro.motionprofiles.ProfileParser;
import org.hammerhead226.sharkmacro.motionprofiles.ProfileRecorder;
import org.hammerhead226.sharkmacro.motionprofiles.ProfileRecorder.RecordingType;

import edu.wpi.first.wpilibj.Timer;
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
  private TalonSRX frontRight = new TalonSRX(RobotMap.DT_FRONT_RIGHT);

  private TalonSRX rearLeft = new TalonSRX(RobotMap.DT_REAR_LEFT);
  private VictorSPX rearRight = new VictorSPX(RobotMap.DT_REAR_RIGHT);

  private PigeonIMU pigeon = new PigeonIMU(rearLeft);

  private ProfileRecorder recorder = new ProfileRecorder(frontLeft, frontRight, RecordingType.VOLTAGE);

  private double lastSpeed = 0;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DT_CheesyDrive());
  }

  public DriveTrain() {
    rearLeft.follow(frontLeft);
    rearRight.follow(frontRight);

    frontLeft.setNeutralMode(Constants.DT_NEUTRAL_MODE);
    frontRight.setNeutralMode(Constants.DT_NEUTRAL_MODE);
    rearLeft.setNeutralMode(Constants.DT_NEUTRAL_MODE);
    rearRight.setNeutralMode(Constants.DT_NEUTRAL_MODE);

    frontLeft.setInverted(Constants.DT_INVERT_L);
    rearLeft.setInverted(InvertType.FollowMaster);

    frontRight.setInverted(Constants.DT_INVERT_R);
    rearRight.setInverted(InvertType.FollowMaster);

    frontLeft.setSensorPhase(Constants.DT_LEFT_SENSOR_PHASE);
    frontRight.setSensorPhase(Constants.DT_RIGHT_SENSOR_PHASE);

    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    frontLeft.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.DT_TIMEOUT);
    rearLeft.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.DT_TIMEOUT);
    frontRight.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.DT_TIMEOUT);
    rearRight.configVoltageCompSaturation(Constants.DT_VOLTAGE_LIMIT, Constants.DT_TIMEOUT);

    frontLeft.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);
    rearLeft.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);
    frontRight.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);
    rearRight.enableVoltageCompensation(Constants.DT_VOLTAGE_LIMIT_ENABLED);

    frontLeft.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.DT_TIMEOUT);
    frontRight.configContinuousCurrentLimit(Constants.DT_CURRENT_LIMIT, Constants.DT_TIMEOUT);

    frontLeft.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);
    frontRight.enableCurrentLimit(Constants.DT_CURRENT_LIMIT_ENABLED);

    frontLeft.configOpenloopRamp(Constants.DT_VOLTAGE_RAMP_RATE, Constants.DT_TIMEOUT);
    frontRight.configOpenloopRamp(Constants.DT_VOLTAGE_RAMP_RATE, Constants.DT_TIMEOUT);
    rearLeft.configOpenloopRamp(Constants.DT_VOLTAGE_RAMP_RATE, Constants.DT_TIMEOUT);
    rearRight.configOpenloopRamp(Constants.DT_VOLTAGE_RAMP_RATE, Constants.DT_TIMEOUT);
  }

  public void toggleProfileRecording() {
    if (recorder.isRecording()) {
      ProfileParser p = new ProfileParser(ProfileParser.getNewFilename());
      p.writeToFile(recorder.stop().toProfile());
      System.out.println("Profile saved.");
    } else {
      zeroEncoders();
      Timer.delay(0.2);
      System.out.println("Profile recording started.");
      recorder.start();
    }
  }

  public void toggleActionListRecording() {
    if (ActionRecorder.isRecording()) {
      ActionListParser al = new ActionListParser(ActionListParser.getNewFilename());
      al.writeToFile(ActionRecorder.stop());
      System.out.println("ActionList saved.");
    } else {
      Timer.delay(0.2);
      ActionRecorder.start();
      System.out.println("ActionList recording started.");
    }
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

  public void tankDrive(double left, double right){
    frontLeft.set(ControlMode.PercentOutput,left);
    frontRight.set(ControlMode.PercentOutput, right);
  }

  public void cheesyDrive(double xSpeed, double zRotation) {

    double change = xSpeed - lastSpeed;

    if(change < -Constants.DT_SPEED_CHANGE_LIMIT) {
      change = -Constants.DT_SPEED_CHANGE_LIMIT;
    }
    
    xSpeed = lastSpeed + change;

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

    frontLeft.set(ControlMode.PercentOutput, limit(leftMotorOutput));
    frontRight.set(ControlMode.PercentOutput, limit(rightMotorOutput));

    lastSpeed = xSpeed;
  }

  public void arcadeDrive(double throttle, double turn){
    frontLeft.set(ControlMode.PercentOutput, throttle-turn);
    frontRight.set(ControlMode.PercentOutput, throttle + turn);
  }

  public TalonSRX[] getMotionProfileTalons() {
    return new TalonSRX[] { frontLeft, frontRight };
  }

  public double getYaw(){
    double[] vals = new double[3];
    pigeon.getYawPitchRoll(vals);
    return vals[0];
  }

  public void zeroEncoders() {
    frontLeft.setSelectedSensorPosition(0, 0, 0);
    frontRight.setSelectedSensorPosition(0, 0, 0);
    System.out.println("Drivetrain encoders zeroed.");
  }

  public void zeroYaw(){
    pigeon.setYaw(0);
    pigeon.setFusedHeading(0);
  }
}