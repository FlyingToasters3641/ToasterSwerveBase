package frc.robot;

import com.ctre.phoenixpro.configs.Slot0Configs;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

import java.util.Map;

public final class Constants {
    public static final Mode currentMode = Mode.REAL;

    public static boolean DeterministicTimestamps = true;
    public static String CANBusName = "Canivore1";
    public static final double TurnKd = 0;
    public static final double TurnKp = 5;

    public static enum Mode {
        /**
         * Running on a real robot.
         */
        REAL,
        /**
         * Running a physics simulator.
         */
        SIM,
        /**
         * Replaying from a log file.
         */
        REPLAY
    }

    /**
     * Constants for the SDS MK4i L3 with Falcons with Phoenix Pro
     */
    public static class CTRSwerveConstants {
        public static final double wheelRadius = 3;
        public static final int slipCurrent = 17;
        public static final boolean CANCoderReversed = true;
        public static final Slot0Configs steerGains = new Slot0Configs();
        public static final Slot0Configs driveGains = new Slot0Configs();

        {
            steerGains.kP = 30;
            steerGains.kD = 0.2;
            driveGains.kP = 1;
        }

        private static final double robotWidthMeters = Units.inchesToMeters(22.0);
        private static final double robotLengthMeters = robotWidthMeters;
        public static final double SteerMotorRatio = 12.8; //12.8 : 1
        public static final double DriveMotorRatio = 10.0; //10.0 : 1

        public static final String[] moduleNames = new String[]{
                "frontright",
                "frontleft",
                "backright",
                "backleft"
        };
        private static final Map<String, Integer> driveIDs = Map.of(
                moduleNames[0], 2,
                moduleNames[1], 5,
                moduleNames[2], 8,
                moduleNames[3], 7
        );

        private static final Map<String, Double> defaultCancoderOffsets = Map.of(
                moduleNames[0], 0.0,
                moduleNames[1], 0.0,
                moduleNames[2], 0.0,
                moduleNames[3], 0.0
        );

        public static int getDriveID(String moduleName) {
            return driveIDs.get(moduleName.toLowerCase());
        }

        private static final Map<String, Integer> steerIDs = Map.of(
                moduleNames[0], 1,
                moduleNames[1], 4,
                moduleNames[2], 7,
                moduleNames[3], 9
        );

        public static int getSteerID(String moduleName) {
            return steerIDs.get(moduleName.toLowerCase());
        }

        private static final Map<String, Integer> cancoderIDs = Map.of(
                moduleNames[0], 3,
                moduleNames[1], 6,
                moduleNames[2], 7,
                moduleNames[3], 7
        );

        public static int getCANCoderID(String moduleName) {
            return cancoderIDs.get(moduleName.toLowerCase());
        }

        private static final Map<String, Translation2d> modulePositions = Map.of(
                moduleNames[0], new Translation2d(robotWidthMeters / 2, -robotLengthMeters / 2),
                moduleNames[1], new Translation2d(robotWidthMeters / 2, robotLengthMeters / 2),
                moduleNames[2], new Translation2d(-robotWidthMeters / 2, -robotLengthMeters / 2),
                moduleNames[3], new Translation2d(-robotWidthMeters / 2, robotLengthMeters / 2)
        );

        public static SwerveDriveKinematics getSwerveKinematics() {
            var positions = modulePositions.values()
                    .toArray(new Translation2d[0]);
            return new SwerveDriveKinematics(positions);
        }

        public static Translation2d[] getModulePositions() {
            return modulePositions.values().toArray(new Translation2d[0]);
        }

        public static String[] getModuleNames() {
            return moduleNames;
        }

        public static double getDefaultCancoderOffset(String moduleName) {
            return defaultCancoderOffsets.get(moduleName);
        }
    }

}
