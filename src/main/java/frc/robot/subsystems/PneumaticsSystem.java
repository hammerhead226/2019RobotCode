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
    shiftIntake();
  }

  public void compressorOn() {
    compressor.start();
  }

  public void compressorOff() {
    compressor.stop();
  }

  public void toggleCompressor() {
    if(compressor.enabled()){
      compressorOff();
    } else {
      compressorOn();
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  private DoubleSolenoid intake = new DoubleSolenoid(RobotMap.INTAKE_SHIFTER_1,
      RobotMap.INTAKE_SHIFTER_2);

  private DoubleSolenoid.Value intakeVal = DoubleSolenoid.Value.kOff;

  public void shiftIntake() {
    if (intakeVal == DoubleSolenoid.Value.kForward) {
      intakeVal = DoubleSolenoid.Value.kReverse;
    } else {
      intakeVal = DoubleSolenoid.Value.kForward;
    }
    intake.set(intakeVal);
  }

  public void shiftIntakeForward(){
    intake.set(DoubleSolenoid.Value.kForward);
  }

  public void shiftIntakeNeutral(){
    intake.set(DoubleSolenoid.Value.kOff);
  }
}
