package org.usfirst.frc.team4373.robot.commands.teleop;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import org.usfirst.frc.team4373.robot.OI;
import org.usfirst.frc.team4373.robot.subsystems.DriveTrain;

/**
 * This Command uses PID to keep the robot driving straight
 * using a gyroscope.
 * @author (tesla)
 */
public class DriveStraightWithJoystick extends PIDCommand {
    private static final double kP = 1;
    private static final double kI = 0.1;
    private static final double kD = 0.01;

    private DriveTrain driveTrain;
    private ADXRS450_Gyro gyro;

    /**
     * Javadoc comment.
     * @author (tesla)
     */
    public DriveStraightWithJoystick() {
        super("DriveStraightWithJoystick", kP, kI, kD);
        this.driveTrain = DriveTrain.getDriveTrain();
        this.gyro = OI.getOI().getGyro();
        this.setInputRange(-180, 180);
        this.setSetpoint(0);
    }

    @Override
    protected double returnPIDInput() {
        double angle = gyro.getAngle();
        return Math.signum(angle) * (Math.abs(angle) % 180);
    }

    @Override
    protected void usePIDOutput(double output) {
        double power = output / 180;
        power /= 2;
        driveTrain.setLeft(0.5 + power);
        driveTrain.setRight(0.5 - power);
    }

    @Override
    protected void initialize() {
        gyro.reset();
        gyro.calibrate();
        gyro.setPIDSourceType(PIDSourceType.kDisplacement);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        driveTrain.setBoth(0);
    }

    @Override
    protected void interrupted() {

    }
}
