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

  private String Compressor_State = "Off";

  public PneumaticsSystem() {
    compressorOn();
  }

  public void compressorOn() {
    compressor.start();
    Compressor_State = "On";
  }

  public void compressorOff() {
    compressor.stop();
    Compressor_State = "Off";
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void log() {
    ShuffleboardTab pneumatics_system = Shuffleboard.getTab("Pneumatics System");
    Shuffleboard.selectTab("Pneumatics System");
    pneumatics_system.add("Compressor State", Compressor_State);
    pneumatics_system.add("leftIntake DoubleSolenoid State", leftIntake_State);
    pneumatics_system.add("rightIntake DoubleSolenoid State", rightIntake_State);
    pneumatics_system.add("arm DoubleSolenoid State", arm_State);
  }

  private DoubleSolenoid leftIntake = new DoubleSolenoid(RobotMap.INTAKE_LEFT_SHIFTER_1,
      RobotMap.INTAKE_LEFT_SHIFTER_2);
  private DoubleSolenoid rightIntake = new DoubleSolenoid(RobotMap.INTAKE_RIGHT_SHIFTER_1,
      RobotMap.INTAKE_RIGHT_SHIFTER_2);
  private DoubleSolenoid arm = new DoubleSolenoid(RobotMap.ARM_SHIFTER_1, RobotMap.ARM_SHIFTER_2);

  private DoubleSolenoid.Value leftIntakeVal = DoubleSolenoid.Value.kOff;
  private String leftIntake_State = "Off";

  public void shiftIntakeLeft() {
    if (leftIntakeVal == DoubleSolenoid.Value.kForward) {
      leftIntakeVal = DoubleSolenoid.Value.kReverse;
      leftIntake_State = "reverse";
    } else {
      leftIntakeVal = DoubleSolenoid.Value.kForward;
      leftIntake_State = "forward";
    }
    leftIntake.set(leftIntakeVal);
  }

  private DoubleSolenoid.Value rightIntakeVal = DoubleSolenoid.Value.kOff;
  private String rightIntake_State = "Off";

  public void shiftIntakeRight() {
    if (rightIntakeVal == DoubleSolenoid.Value.kForward) {
      rightIntakeVal = DoubleSolenoid.Value.kReverse;
      rightIntake_State = "reverse";
    } else {
      rightIntakeVal = DoubleSolenoid.Value.kForward;
      rightIntake_State = "forward";
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
    leftIntake_State = "off";
    rightIntake_State = "off";
  }

  private DoubleSolenoid.Value armVal = DoubleSolenoid.Value.kOff;
  private String arm_State = "off";

  public void shiftArm() {
    if (armVal == DoubleSolenoid.Value.kForward) {
      armVal = DoubleSolenoid.Value.kReverse;
      arm_State = "reverse";
    } else {
      armVal = DoubleSolenoid.Value.kForward;
      arm_State = "forward";
    }
    arm.set(armVal);
  }

  public void shiftArmNeutral(){
    arm.set(DoubleSolenoid.Value.kOff);;
    arm_State = "off";
  }

}
