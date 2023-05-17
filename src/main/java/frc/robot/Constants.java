package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.util.Units;
import frc.robot.subsystems.drivetrain.CTRSwerve.SwerveModuleConstants;

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
    public static final int FRONT_RIGHT_STEER = 1;
    public static final int FRONT_RIGHT_DRIVE = 2;
    public static final int FRONT_RIGHT_CANCODER = 3;
    public static final int FRONT_LEFT_STEER = 4;
    public static final int FRONT_LEFT_DRIVE = 5;
    public static final int FRONT_LEFT_CANCODER = 6;
    public static final int BACK_RIGHT_STEER = 7;
    public static final int BACK_RIGHT_DRIVE = 8;
    public static final int BACK_RIGHT_CANCODER = 7;
    public static final int BACK_LEFT_STEER = 9;
    public static final int BACK_LEFT_DRIVE = 7;
    public static final int BACK_LEFT_CANCODER = 7;

    public static class CTRSwerve {

        public static SwerveModuleConstants getModuleConstants(int moduleIndex) {

            return null;
        }
    }

}
