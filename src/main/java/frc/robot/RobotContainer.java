// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenixpro.configs.Slot0Configs;
import com.ctre.phoenixpro.hardware.Pigeon2;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PoseEstimatorSubsystem;
import frc.robot.subsystems.drivetrain.CTRSwerve.CTRSwerveModuleIO;
import frc.robot.subsystems.drivetrain.DrivetrainSubsystem;
import frc.robot.subsystems.drivetrain.CTRSwerve.SwerveDriveConstantsCreator;
import frc.robot.subsystems.drivetrain.CTRSwerve.SwerveDriveTrainConstants;

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
                    Constants.CTRSwerveConstants.DriveMotorRatio, // 10:1 ratio for the drive motor
                    Constants.CTRSwerveConstants.SteerMotorRatio, // 12.8:1 ratio for the steer motor
                    Constants.CTRSwerveConstants.wheelRadius, // 3 inch radius for the wheels
                    17, // Only apply 17 stator amps to prevent slip
                    steerGains, // Use the specified steer gains
                    driveGains, // Use the specified drive gains
                    true // CANcoder not reversed from the steer motor. For WCP Swerve X this should be true.
            );


    PoseEstimatorSubsystem m_poseEstimator = new PoseEstimatorSubsystem(
            new SwerveDriveKinematics(
            ),
            new Rotation2d(m_pigeon2.getAngle()),
            new SwerveModulePosition[4],
            new Pose2d(0,0,new Rotation2d(0))
    );

    DrivetrainSubsystem m_drive = new DrivetrainSubsystem(drivetrain, m_poseEstimator, m_pigeon2, new CTRSwerveModuleIO);

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
