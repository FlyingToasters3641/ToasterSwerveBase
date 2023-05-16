package frc.robot.subsystems.drivetrain;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import org.littletonrobotics.junction.AutoLog;

public interface SwerveModuleIO {
    @AutoLog
    static class DriveIOInputs {
        public double drivePositionRad = 0.0;
        public double driveVelocityRadPerSec = 0.0;
        public double turnPositionRad = 0.0;
        public double turnVelocityRadPerSec = 0.0;
        public SwerveModulePosition swerveModuleState = new SwerveModulePosition();
        public double driveTempCelcius = 0.0;
        public double turnTempCelcius = 0.0;

    }

    public Translation2d modulePosition = new Translation2d(0,0);

    /** Updates the set of loggable inputs. */
    default void updateInputs(DriveIOInputs inputs) {
    }

    /** Run closed loop to the specified SwerveModuleState. */
    default void apply(SwerveModuleState state) {
    }

}
