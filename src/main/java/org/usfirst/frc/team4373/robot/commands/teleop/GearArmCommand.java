package org.usfirst.frc.team4373.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4373.robot.OI;
import org.usfirst.frc.team4373.robot.subsystems.GearMechanismArm;

/**
 * A command to control the gear mechanism arm.
 * @author aaplmath
 */
public class GearArmCommand extends Command {

    private GearMechanismArm gearMechArm;

    public GearArmCommand() {
        requires(gearMechArm = GearMechanismArm.getGearMechanismArm());
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        if (OI.getOI().getDriveJoystick().getRawButton(4)) {
            gearMechArm.turnToRotation(90d);
        }
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

    }
}
