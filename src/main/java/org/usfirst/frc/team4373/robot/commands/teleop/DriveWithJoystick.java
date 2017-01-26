package org.usfirst.frc.team4373.robot.commands.teleop;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import org.usfirst.frc.team4373.robot.OI;
import org.usfirst.frc.team4373.robot.RobotMap;
import org.usfirst.frc.team4373.robot.input.filter.HalfFilter;
import org.usfirst.frc.team4373.robot.input.hid.RooJoystick;
import org.usfirst.frc.team4373.robot.subsystems.DriveTrain;

/**
 * This command handles operator control of the drive train subsystem.
 * It sets outputs based on joystick axes.
 * @author Henry Pitcairn
 */
public class DriveWithJoystick extends PIDCommand {
    private static final double kP = 1;
    private static final double kI = 0.1;
    private static final double kD = 0.01;

    private ADXRS450_Gyro gyro;
    private DriveTrain driveTrain;
    private RooJoystick joystick;

    private boolean straight;

    /**
     * Constructor for DriveWithJoystick.
     */
    public DriveWithJoystick() {
        super("DriveWithJoystick", kP, kI, kD);
        requires(DriveTrain.getDriveTrain());
        driveTrain = DriveTrain.getDriveTrain();
        joystick = OI.getOI().getDriveJoystick();
        this.gyro = OI.getOI().getGyro();
        this.setInputRange(-180, 180);
        this.setSetpoint(0);
    }

    @Override
    protected void execute() {
        double twistAxis = this.joystick.getAxis(RobotMap.JOYSTICK_TWIST_AXIS);
        double horizontalAxis = this.joystick.getAxis(RobotMap.JOYSTICK_HORIZONTAL_AXIS);
        double forwardAxis = this.joystick.getAxis(RobotMap.JOYSTICK_FORWARD_AXIS);
        if (twistAxis == 0 && horizontalAxis == 0) {
            // Drive straight
            driveTrain.setBoth(forwardAxis);
            straight = true;
        } else {
            // Drive with joystick—translation and back/forth
            double right = forwardAxis
                    + this.joystick.getAxis(RobotMap.JOYSTICK_TWIST_AXIS, new HalfFilter());
            double left = forwardAxis
                    - this.joystick.getAxis(RobotMap.JOYSTICK_TWIST_AXIS, new HalfFilter());
            driveTrain.setLeft(-left);
            driveTrain.setRight(-right);
            straight = false;
        }
        // Sliding will be captured independently of rotation/back-forth so that
        // multiple translations can occur simultaneously
        if (horizontalAxis != 0) {
            // Horizontal "sliding"—positive=right, negative=left
            driveTrain.setMiddle(horizontalAxis);
        }
    }

    @Override
    protected double returnPIDInput() {
        double angle = gyro.getAngle();
        return Math.signum(angle) * (Math.abs(angle) % 180);
    }

    @Override
    protected void usePIDOutput(double output) {
        if (straight) {
            double power = output / 180;
            power /= 2;
            driveTrain.setLeft(0.5 + power);
            driveTrain.setRight(0.5 - power);
        }
    }

    @Override
    protected void initialize() {
        gyro.reset();
        gyro.calibrate();
        gyro.setPIDSourceType(PIDSourceType.kDisplacement);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {
        this.driveTrain.setBoth(0);
    }
}
