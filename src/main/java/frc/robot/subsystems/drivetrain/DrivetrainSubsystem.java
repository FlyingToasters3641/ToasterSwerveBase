package frc.robot.subsystems.drivetrain;

import com.ctre.phoenixpro.BaseStatusSignalValue;
import com.ctre.phoenixpro.hardware.Pigeon2;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Twist2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.PoseEstimatorSubsystem;
import frc.robot.subsystems.drivetrain.CTRSwerve.CTRSwerveModuleIO;
import frc.robot.subsystems.drivetrain.CTRSwerve.SwerveDriveTrainConstants;
import frc.robot.subsystems.drivetrain.CTRSwerve.SwerveModuleConstants;

import static frc.robot.subsystems.drivetrain.SwerveModuleIO.*;

public class DrivetrainSubsystem extends SubsystemBase {
    private final SwerveModuleIO[] m_modules;
    private final SwerveModuleIO.SwerveModuleIOInputs[] m_moduleInputs;
   // private final Pigeon2 m_pigeon2;
    private final SwerveDriveKinematics m_kinematics;
    // private SwerveDriveOdometry m_odometry;
    private SwerveModulePosition[] m_modulePositions;
    //  private OdometryThread m_odometryThread;
    private final PoseEstimatorSubsystem m_poseEstimator;
    private Field2d m_field;
    private PIDController m_turnPid;

    public DrivetrainSubsystem(
            PoseEstimatorSubsystem poseEstimator, SwerveModuleIO... modules) {
        m_poseEstimator = poseEstimator;
        //m_pigeon2 = pigeon2;
        m_modules = modules;
        m_moduleInputs = new SwerveModuleIO.SwerveModuleIOInputs[m_modules.length];
        m_modulePositions = new SwerveModulePosition[m_modules.length];

        for (int i = 0; i < m_modules.length; i++) {
            // m_moduleLocations[iteration] = new Translation2d(module.LocationX, module.LocationY);
            m_modules[i].updateInputs(m_moduleInputs[i]);
            m_modulePositions[i] = m_moduleInputs[i].swerveModuleState;
        }

        m_kinematics = Constants.CTRSwerveConstants.getSwerveKinematics();
        //m_odometry =
        //       new SwerveDriveOdometry(m_kinematics, m_pigeon2.getRotation2d(), getSwervePositions());
        m_field = new Field2d();
        //SmartDashboard.putData("Field", m_field);

        m_turnPid = new PIDController(Constants.TurnKp, 0, Constants.TurnKd);
        m_turnPid.enableContinuousInput(-Math.PI, Math.PI);
    }

    public void driveRobotCentric(ChassisSpeeds speeds) {
        var swerveStates = m_kinematics.toSwerveModuleStates(speeds);
        for (int i = 0; i < m_modules.length; ++i) {
            m_modules[i].apply(swerveStates[i]);
        }
    }

    public void driveFieldCentric(ChassisSpeeds speeds) {
        var roboCentric = ChassisSpeeds.fromFieldRelativeSpeeds(speeds, m_poseEstimator.getEstimatedPosition().getRotation());
        driveRobotCentric(roboCentric);
    }

    public void driveFullyFieldCentric(double xSpeeds, double ySpeeds, Rotation2d targetAngle) {
        var currentAngle = m_poseEstimator.getEstimatedPosition().getRotation();
        double rotationalSpeed =
                m_turnPid.calculate(currentAngle.getRadians(), targetAngle.getRadians());

        var roboCentric =
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        xSpeeds, ySpeeds, rotationalSpeed, m_poseEstimator.getEstimatedPosition().getRotation());
        driveRobotCentric(roboCentric);
    }

    public void driveStopMotion() {
        /* Point every module toward (0,0) to make it close to an X configuration */
        var moduleLocations = Constants.CTRSwerveConstants.getModulePositions();
        for (int i = 0; i < m_modules.length; ++i) {
            var angle = moduleLocations[i].getAngle();
            m_modules[i].apply(new SwerveModuleState(0, angle));
        }
    }

    //TODO: Change this to work with pose estimator
    public void seedFieldRelative() {
       // m_pigeon2.setYaw(0);
    }
}
