package frc.robot.utils;


import org.littletonrobotics.junction.Logger;

import java.io.File;

public class ValueStore {
    /* This is a class designed to store values beyond
    * the lifecycle of the robot code (E.g. stores after reboots)
    */
    File filePath;
    Logger logger;
    public ValueStore() {

        filePath = new File(System.getProperty("user.home") + File.separator + "ToasterValueStore" + File.separator + "ValueStore.json");
        if (!filePath.exists()) {
            boolean created = filePath.mkdirs();
            if (created) {
                System.out.println("Value Store created at: " + filePath.getAbsolutePath());
                logger.recordOutput("Messages", "Value Store successfully created at: " + filePath.getAbsolutePath());
            } else {
                System.out.println("Failed to create Value Store.");
                logger.recordOutput("Messages", "Failed to create Value Store!");
            }
        }

    }

    public void storeValue(String key, Double value) {

    }

    public void storeValue(String key, Integer value) {

    }

    public void storeValue(String key, Boolean value) {

    }

    public void storeValue(String key, String value) {

    }

    public void getValue(String key, Double defaultValue) {

    }

    public void getValue(String key, Integer defaultValue) {

    }

    public void getValue(String key, Boolean defaultValue) {

    }

    public void getValue(String key, String defaultValue) {

    }
}
