// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenixpro.hardware.Pigeon2;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PoseEstimatorSubsystem;
import frc.robot.subsystems.drivetrain.CTRSwerve.CTRSwerveModuleIO;
import frc.robot.subsystems.drivetrain.DrivetrainSubsystem;
import frc.robot.subsystems.drivetrain.SwerveModuleIO;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    Pigeon2 m_pigeon2 = new Pigeon2(1, Constants.CANBusName);

    private SwerveModuleIO[] m_modules;


    PoseEstimatorSubsystem m_poseEstimator = new PoseEstimatorSubsystem(
            Constants.CTRSwerveConstants.getSwerveKinematics(),
            new Rotation2d(m_pigeon2.getAngle()),
            new SwerveModulePosition[4],
            new Pose2d(),
            m_pigeon2,
            m_modules
    );

    DrivetrainSubsystem m_drive = new DrivetrainSubsystem(m_poseEstimator);

    public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();

        int i = 0;
        for (String moduleName : Constants.CTRSwerveConstants.moduleNames) {
            m_modules[i] = new CTRSwerveModuleIO(moduleName, Constants.CANBusName, Constants.CTRSwerveConstants.getDefaultCancoderOffset(moduleName));
            i++;
        }
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
