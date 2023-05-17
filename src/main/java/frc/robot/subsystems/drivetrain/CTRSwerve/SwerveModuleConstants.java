package frc.robot.subsystems.drivetrain.CTRSwerve;

import com.ctre.phoenixpro.configs.Slot0Configs;

public interface SwerveModuleConstants {
    /** CAN ID of the drive motor */
    int DriveMotorId = 0;
    /** CAN ID of the steer motor */
    int SteerMotorId = 0;
    /** CAN ID of the CANcoder used for azimuth */
    int CANcoderId = 0;
    /** Offset of the CANcoder in degrees */
    double CANcoderOffset = 0;
    /** Gear ratio between drive motor and wheel */
    double DriveMotorGearRatio = 0;
    /** Gear ratio between steer motor and CANcoder An example ratio for the SDS Mk4: 12.8 */
    double SteerMotorGearRatio = 0;
    /** Wheel radius of the driving wheel in inches */
    double WheelRadius = 0;
    /**
     * The location of this module's wheels relative to the physical center of the robot in meters
     * along the X axis of the robot
     */
    double LocationX = 0;
    /**
     * The location of this module's wheels relative to the physical center of the robot in meters
     * along the Y axis of the robot
     */
    double LocationY = 0;

    /** The steer motor gains */
    Slot0Configs SteerMotorGains = new Slot0Configs();
    /** The drive motor gains */
    Slot0Configs DriveMotorGains = new Slot0Configs();

    /** The maximum amount of current the drive motors can apply without slippage */
    double SlipCurrent = 400;

    /** True if the steering motor is reversed from the CANcoder */
    boolean SteerMotorReversed = false;

}
