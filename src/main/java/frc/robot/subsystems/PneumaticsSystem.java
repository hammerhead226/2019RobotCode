/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class PneumaticsSystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Compressor compressor = new Compressor();

  public PneumaticsSystem() {
    compressorOn();
  }

  public void compressorOn() {
    compressor.start();
  }

  public void compressorOff() {
    compressor.stop();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void log() {
    ShuffleboardTab pneumatics_system = Shuffleboard.getTab("Pneumatics System");
    pneumatics_system.add("Compressor State", compressor.enabled());
    pneumatics_system.add("leftIntake DoubleSolenoid State", leftIntake);
    pneumatics_system.add("rightIntake DoubleSolenoid State", rightIntake);
    pneumatics_system.add("arm DoubleSolenoid State", arm);
  }

  private DoubleSolenoid leftIntake = new DoubleSolenoid(RobotMap.INTAKE_LEFT_SHIFTER_1,
      RobotMap.INTAKE_LEFT_SHIFTER_2);
  private DoubleSolenoid rightIntake = new DoubleSolenoid(RobotMap.INTAKE_RIGHT_SHIFTER_1,
      RobotMap.INTAKE_RIGHT_SHIFTER_2);
  private DoubleSolenoid arm = new DoubleSolenoid(RobotMap.ARM_SHIFTER_1, RobotMap.ARM_SHIFTER_2);

  private DoubleSolenoid.Value leftIntakeVal = DoubleSolenoid.Value.kOff;

  public void shiftIntakeLeft() {
    if (leftIntakeVal == DoubleSolenoid.Value.kForward) {
      leftIntakeVal = DoubleSolenoid.Value.kReverse;
    } else {
      leftIntakeVal = DoubleSolenoid.Value.kForward;
    }
    leftIntake.set(leftIntakeVal);
  }

  private DoubleSolenoid.Value rightIntakeVal = DoubleSolenoid.Value.kOff;

  public void shiftIntakeRight() {
    if (rightIntakeVal == DoubleSolenoid.Value.kForward) {
      rightIntakeVal = DoubleSolenoid.Value.kReverse;
    } else {
      rightIntakeVal = DoubleSolenoid.Value.kForward;
    }
    rightIntake.set(rightIntakeVal);
  }

  public void shiftIntake(){
    shiftIntakeRight();
    shiftIntakeLeft();
  }

  public void shiftIntakeNeutral(){
    rightIntake.set(DoubleSolenoid.Value.kOff);
    leftIntake.set(DoubleSolenoid.Value.kOff);
  }

  private DoubleSolenoid.Value armVal = DoubleSolenoid.Value.kOff;

  public void shiftArm() {
    if (armVal == DoubleSolenoid.Value.kForward) {
      armVal = DoubleSolenoid.Value.kReverse;
    } else {
      armVal = DoubleSolenoid.Value.kForward;
    }
    arm.set(armVal);
  }

  public void shiftArmNeutral(){
    arm.set(DoubleSolenoid.Value.kOff);;
  }

}
