package frc.robot;

import com.ctre.phoenixpro.configs.Slot0Configs;
import edu.wpi.first.math.util.Units;
import frc.robot.subsystems.drivetrain.CTRSwerve.SwerveModuleConstants;

import java.util.Map;

public final class Constants {
    public static final Mode currentMode = Mode.REAL;

    public static boolean DeterministicTimestamps = true;
    public static String CanivoreName = "Canivore1";

    public static enum Mode {
        /** Running on a real robot. */
        REAL,
        /** Running a physics simulator. */
        SIM,
        /** Replaying from a log file. */
        REPLAY
    }


    public static final double RobotWidth = Units.inchesToMeters(22.0);
    public static final double RobotLength = RobotWidth;
    public static final double SteerMotorRatio = 12.8; //12.8 : 1
    public static final double DriveMotorRatio = 10.0; //10.0 : 1
    public static final double WheelRadius = 3;

    public static class CTRSwerveConstants {
        Slot0Configs steerGains = new Slot0Configs();
        Slot0Configs driveGains = new Slot0Configs();

        static Map driveIDs = Map.of(
                "frontright", 2,
                "frontleft", 5,
                "backright", 8,
                "backleft", 7
        );

        static Map steerIDs = Map.of(
                "frontright", 1,
                "frontleft", 4,
                "backright", 7,
                "backleft", 9
        );

        static Map cancoderIDs = Map.of(
                "frontright", 3,
                "frontleft", 6,
                "backright", 7,
                "backleft", 7
        );

        {
            steerGains.kP = 30;
            steerGains.kD = 0.2;
            driveGains.kP = 1;
        }
        public static SwerveModuleConstants getModuleConstants(String moduleName) {
            moduleName = moduleName.toLowerCase();

            return null;
        }
    }

}
