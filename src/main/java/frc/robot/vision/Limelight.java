package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    static NetworkTable getTable() {
        return NetworkTableInstance.getDefault().getTable("limelight");
    }

    static void start(){
        getTable().getEntry("camMode").setNumber(0);
    }

    static double getTargetAngle(){
        return getTable().getEntry("tx").getDouble(0.0);
    }

    static boolean contourFound(){
        return getTable().getEntry("tv").getDouble(0.0) == 1;
    }

    static double getContourArea(){
        return getTable().getEntry("ta").getDouble(0.0);
    }
}