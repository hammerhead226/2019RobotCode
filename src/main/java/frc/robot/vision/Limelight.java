package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    private static NetworkTable getTable() {
        return NetworkTableInstance.getDefault().getTable("limelight");
    }

    public static void start() {
        getTable().getEntry("camMode").setNumber(0);
    }

    private static boolean camMode = true;

    public static void changeCamMode() {
        if (camMode) {
            getTable().getEntry("camMode").setNumber(1);
            getTable().getEntry("ledMode").setNumber(1);
            camMode = !camMode;
        } else {
            getTable().getEntry("camMode").setNumber(0);
            getTable().getEntry("ledMode").setNumber(3);
            camMode = !camMode;
        }
    }

    public static void driverSetting(){
        getTable().getEntry("camMode").setNumber(1);
        getTable().getEntry("ledMode").setNumber(1);
    }

    public static double getTargetAngle() {
        return getTable().getEntry("tx").getDouble(0.0);
    }

    public static boolean contourFound() {
        return getTable().getEntry("tv").getDouble(0.0) == 1;
    }

    public static double getContourArea() {
        return getTable().getEntry("ta").getDouble(0.0);
    }

    public static double sigmoid(double x){
        return  (1/(1+Math.pow(Math.E, -x))) - 0.5;
    }
}