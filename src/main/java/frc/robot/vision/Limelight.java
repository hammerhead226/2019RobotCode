package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    static void start(){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
    }
}