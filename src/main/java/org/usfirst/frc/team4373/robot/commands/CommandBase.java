package org.usfirst.frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4373.robot.OI;
/**
 * CommandBase provides a parent class for many commands
 * and holds common variables and functionality.
 * @author Henry Pitcairn
 */

public abstract class CommandBase extends Command {
    protected OI oi;

    public CommandBase() {
        super();
        this.oi = OI.getOI();
    }
}
