// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenixpro.configs.Slot0Configs;
import com.ctre.phoenixpro.hardware.Pigeon2;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PoseEstimatorSubsystem;
import frc.robot.subsystems.drivetrain.DrivetrainSubsystem;
import frc.robot.subsystems.drivetrain.CTRSwerve.SwerveDriveConstantsCreator;
import frc.robot.subsystems.drivetrain.CTRSwerve.SwerveDriveTrainConstants;
import frc.robot.subsystems.drivetrain.CTRSwerve.SwerveModuleConstants;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    Pigeon2 m_pigeon2 = new Pigeon2(1, Constants.CanivoreName);
    SwerveDriveTrainConstants drivetrain =
            new SwerveDriveTrainConstants().withTurnKp(5);

    Slot0Configs steerGains = new Slot0Configs();
    Slot0Configs driveGains = new Slot0Configs();

    {
        steerGains.kP = 30;
        steerGains.kD = 0.2;
        driveGains.kP = 1;
    }


    SwerveDriveConstantsCreator m_constantsCreator =
            new SwerveDriveConstantsCreator(
                    Constants.DriveMotorRatio, // 10:1 ratio for the drive motor
                    Constants.SteerMotorRatio, // 12.8:1 ratio for the steer motor
                    Constants.WheelRadius, // 3 inch radius for the wheels
                    17, // Only apply 17 stator amps to prevent slip
                    steerGains, // Use the specified steer gains
                    driveGains, // Use the specified drive gains
                    true // CANcoder not reversed from the steer motor. For WCP Swerve X this should be true.
            );
    SwerveModuleConstants frontRight =
            m_constantsCreator.createModuleConstants(
                    1, 2, 3, -0.538818, Constants.RobotWidth / 2, -Constants.RobotLength / 2);

    SwerveModuleConstants frontLeft =
            m_constantsCreator.createModuleConstants(
                    4, 5, 6, -0.474609, Constants.RobotWidth / 2, Constants.RobotLength / 2);
    SwerveModuleConstants backRight =
            m_constantsCreator.createModuleConstants(
                    7, 8, 7, -0.928467, -Constants.RobotWidth / 2, -Constants.RobotLength / 2);
    SwerveModuleConstants backLeft =
            m_constantsCreator.createModuleConstants(
                    9, 7, 7, -0.756348, -Constants.RobotWidth / 2, Constants.RobotLength / 2);



    PoseEstimatorSubsystem m_poseEstimator = new PoseEstimatorSubsystem(
            new SwerveDriveKinematics(
                    new Translation2d(frontRight.LocationX, frontRight.LocationY),
                    new Translation2d(frontLeft.LocationX, frontLeft.LocationY),
                    new Translation2d(backRight.LocationX, backRight.LocationY),
                    new Translation2d(backLeft.LocationX, backLeft.LocationY)
            ),
            new Rotation2d(m_pigeon2.getAngle()),
            new SwerveModulePosition[4],
            new Pose2d(0,0,new Rotation2d(0))
    );

    DrivetrainSubsystem m_drive = new DrivetrainSubsystem(drivetrain, m_poseEstimator, m_pigeon2, frontRight, frontLeft, backRight, backLeft);

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
