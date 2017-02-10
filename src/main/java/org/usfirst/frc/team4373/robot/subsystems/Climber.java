package org.usfirst.frc.team4373.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4373.robot.RobotMap;

/**
 * Programmatic representation of physical climber components.
 * @author aaplmath
 */
public class Climber extends Subsystem {
    private CANTalon climberTalon;

    private static Climber climber = null;

    public static Climber getClimber() {
        climber = climber == null ? new Climber() : climber;
        return climber;
    }


    private Climber() {
        climberTalon = new CANTalon(RobotMap.CLIMBER_MOTOR);
        // TODO: Configure climber motor
    }

    /**
     * Rotate the climber a certain number of times.
     * @param rotations The number of rotations to complete.
     */
    public void rotate(int rotations) {

    }

    @Override
    protected void initDefaultCommand() {
//         setDefaultCommand(new ClimberCommand());
    }
}
