// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenixpro.configs.Slot0Configs;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PoseEstimatorSubsystem;
import frc.robot.subsystems.drivetrain.DrivetrainSubsystem;
import frc.robot.subsystems.drivetrain.SwerveDriveConstantsCreator;
import frc.robot.subsystems.drivetrain.SwerveDriveTrainConstants;
import frc.robot.subsystems.drivetrain.SwerveModuleConstants;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    SwerveDriveTrainConstants drivetrain =
            new SwerveDriveTrainConstants().withPigeon2Id(1).withCANbusName("Canivore1").withTurnKp(5);

    Slot0Configs steerGains = new Slot0Configs();
    Slot0Configs driveGains = new Slot0Configs();

    {
        steerGains.kP = 30;
        steerGains.kD = 0.2;
        driveGains.kP = 1;
    }


    SwerveDriveConstantsCreator m_constantsCreator =
            new SwerveDriveConstantsCreator(
                    10, // 10:1 ratio for the drive motor
                    12.8, // 12.8:1 ratio for the steer motor
                    3, // 3 inch radius for the wheels
                    17, // Only apply 17 stator amps to prevent slip
                    steerGains, // Use the specified steer gains
                    driveGains, // Use the specified drive gains
                    false // CANcoder not reversed from the steer motor. For WCP Swerve X this should be true.
            );
    SwerveModuleConstants frontRight =
            m_constantsCreator.createModuleConstants(
                    0, 1, 0, -0.538818, Units.inchesToMeters(22.0 / 2.0), Units.inchesToMeters(-22.0 / 2.0));

    SwerveModuleConstants frontLeft =
            m_constantsCreator.createModuleConstants(
                    2, 3, 1, -0.474609, Units.inchesToMeters(22.0 / 2.0), Units.inchesToMeters(22.0 / 2.0));
    SwerveModuleConstants backRight =
            m_constantsCreator.createModuleConstants(
                    4, 5, 2, -0.928467, Units.inchesToMeters(-22.0 / 2.0), Units.inchesToMeters(-22.0 / 2.0));
    SwerveModuleConstants backLeft =
            m_constantsCreator.createModuleConstants(
                    6, 7, 3, -0.756348, Units.inchesToMeters(-22.0 / 2.0), Units.inchesToMeters(22.0 / 2.0));


    //TODO: Link this into constants and configurations, this is a placeholder!
    PoseEstimatorSubsystem m_poseEstimator = new PoseEstimatorSubsystem(
            new SwerveDriveKinematics(
                    new Translation2d(2, 3),
                    new Translation2d(3, 4),
                    new Translation2d(5, 3),
                    new Translation2d(2, 3)
            ),
            new Rotation2d(0),
            new SwerveModulePosition[4],
            new Pose2d(0,0,new Rotation2d(0))
    );

    DrivetrainSubsystem m_drive = new DrivetrainSubsystem(drivetrain, m_poseEstimator, frontRight, frontLeft, backRight, backLeft);

    public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();
    }


    /**
     * Use this method to define your trigger->command mappings.
     */
    private void configureBindings() {

    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // TODO: Implement properly
        return null;
    }
}
