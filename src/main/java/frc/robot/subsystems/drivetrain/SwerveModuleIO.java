package frc.robot.subsystems.drivetrain;

import com.ctre.phoenixpro.configs.Slot0Configs;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import org.littletonrobotics.junction.AutoLog;

public interface SwerveModuleIO {
    @AutoLog
     class SwerveModuleIOInputs {
        public double drivePositionRad = 0.0;
        public double driveVelocityRadPerSec = 0.0;
        public double turnPositionRad = 0.0;
        public double turnVelocityRadPerSec = 0.0;
        public SwerveModulePosition swerveModuleState = new SwerveModulePosition();
        public double driveTempCelcius = 0.0;
        public double turnTempCelcius = 0.0;

    }

    class SwerveModuleConstants {
        /** CAN ID of the drive motor */
        public int DriveMotorId = 0;
        /** CAN ID of the steer motor */
        public int SteerMotorId = 0;
        /** CAN ID of the CANcoder used for azimuth */
        public int CANcoderId = 0;
        /** Offset of the CANcoder in degrees */
        public double CANcoderOffset = 0;
        /** Gear ratio between drive motor and wheel */
        public double DriveMotorGearRatio = 0;
        /** Gear ratio between steer motor and CANcoder An example ratio for the SDS Mk4: 12.8 */
        public double SteerMotorGearRatio = 0;
        /** Wheel radius of the driving wheel in inches */
        public double WheelRadius = 0;
        /**
         * The location of this module's wheels relative to the physical center of the robot in meters
         * along the X axis of the robot
         */
        public double LocationX = 0;
        /**
         * The location of this module's wheels relative to the physical center of the robot in meters
         * along the Y axis of the robot
         */
        public double LocationY = 0;

        /** The steer motor gains */
        public Slot0Configs SteerMotorGains = new Slot0Configs();
        /** The drive motor gains */
        public Slot0Configs DriveMotorGains = new Slot0Configs();

        /** The maximum amount of current the drive motors can apply without slippage */
        public double SlipCurrent = 400;

        /** True if the steering motor is reversed from the CANcoder */
        public boolean SteerMotorReversed = false;
    }


    public Translation2d modulePosition = new Translation2d(0,0);

    /** Updates the set of loggable inputs. */
    default void updateInputs(SwerveModuleIOInputs inputs) {
    }

    /** Run closed loop to the specified SwerveModuleState. */
    default void apply(SwerveModuleState state) {
    }

}
