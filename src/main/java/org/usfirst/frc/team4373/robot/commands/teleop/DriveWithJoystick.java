package org.usfirst.frc.team4373.robot.commands.teleop;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4373.robot.OI;
import org.usfirst.frc.team4373.robot.RobotMap;
import org.usfirst.frc.team4373.robot.commands.CommandBase;
import org.usfirst.frc.team4373.robot.input.filter.HalfFilter;
import org.usfirst.frc.team4373.robot.input.hid.RooJoystick;
import org.usfirst.frc.team4373.robot.subsystems.DriveTrain;

/**
 * This command handles operator control of the drive train subsystem.
 * It sets outputs based on joystick axes.
 * @author Henry Pitcairn
 */
public class DriveWithJoystick extends CommandBase {

    private DriveTrain driveTrain;
    private RooJoystick joystick;

    /**
     * Constructor for DriveWithJoystick.
     */
    public DriveWithJoystick() {
        requires(DriveTrain.getDriveTrain());
        driveTrain = DriveTrain.getDriveTrain();
        joystick = OI.getOI().getDriveJoystick();
    }

    @Override
    protected void execute() {
        double twistAxis = this.joystick.getAxis(RobotMap.JOYSTICK_TWIST_AXIS);
        double horizontalAxis = this.joystick.getAxis(RobotMap.JOYSTICK_HORIZONTAL_AXIS);
        double forwardAxis = -this.joystick.getAxis(RobotMap.JOYSTICK_FORWARD_AXIS);
        if (twistAxis == 0 && forwardAxis != 0) { // Just forward
            driveTrain.setBoth(forwardAxis);
        } else if (twistAxis != 0 && forwardAxis != 0) { // Twist and forward
            // special logic
            double right = forwardAxis;
            double left = forwardAxis;
            if (twistAxis > 0) {
                left -= twistAxis;
            } else if (twistAxis < 0) {
                right -= Math.abs(twistAxis);
            }
            driveTrain.setRight(right);
            driveTrain.setLeft(left);
        } else { // Just twist
            driveTrain.setRight(twistAxis / 2);
            driveTrain.setLeft(-twistAxis / 2);
        }
        driveTrain.setMiddle(horizontalAxis);
    }

    @Override
    protected void initialize() {
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
