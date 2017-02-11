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

        int absolutePosition = climberTalon.getPulseWidthPosition() & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        // Use the low level API to set the quad encoder signal
        climberTalon.setEncPosition(absolutePosition);
        
        // Set sensor and sensor direction
        climberTalon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        climberTalon.reverseSensor(false);

        // Set peak and nominal outputs—12V means full
        climberTalon.configNominalOutputVoltage(+0f, -0f);
        climberTalon.configPeakOutputVoltage(+12f, -12f);

        // Set the allowable closed-loop error—Closed-Loop output will be neutral in this range
        climberTalon.setAllowableClosedLoopErr(0); // Always servo

        // Set closed loop gains in slot 0
        climberTalon.setProfile(0);

        // PID config
        climberTalon.setF(0.0);
        climberTalon.setP(0.1);
        climberTalon.setI(0.0);
        climberTalon.setD(0.0); 
    }

    /**
     * Rotate the climber a certain number of times.
     * @param rotations The number of rotations to complete.
     */
    public void rotate(int rotations) {
        climberTalon.set(rotations);

    }

    @Override
    protected void initDefaultCommand() {
//         setDefaultCommand(new ClimberCommand());
    }
}
