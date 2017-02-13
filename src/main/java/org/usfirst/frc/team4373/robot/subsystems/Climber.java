package org.usfirst.frc.team4373.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4373.robot.RobotMap;
import org.usfirst.frc.team4373.robot.commands.teleop.ClimberCommand;

/**
 * Programmatic representation of physical climber components.
 * @author aaplmath
 */
public class Climber extends Subsystem {
    private CANTalon climberTalon1;
    private CANTalon climberTalon2;

    private static Climber climber = null;

    public static Climber getClimber() {
        climber = climber == null ? new Climber() : climber;
        return climber;
    }


    private Climber() {
        climberTalon1 = new CANTalon(RobotMap.CLIMBER_MOTOR_1);
        climberTalon2 = new CANTalon(RobotMap.CLIMBER_MOTOR_2);

        this.climberTalon1.enableBrakeMode(false);
        this.climberTalon2.enableBrakeMode(false);

        this.climberTalon2.changeControlMode(CANTalon.TalonControlMode.Follower);
        this.climberTalon2.set(RobotMap.CLIMBER_MOTOR_1);
    }

    /**
     * Start the motor spinning forward.
     * @param power The power, from 0 to 1, to supply to the motor.
     */
    public void setForward(int power) {
        climberTalon1.set(power);
    }

    /**
     * Start the motor spinning backward.
     * @param power The power, from 0 to 1, to supply to the motor.
     */
    public void setBackward(int power) {
        climberTalon1.set(-power);
    }

    /**
     * Stop the motor.
     */
    public void stop() {
        climberTalon1.set(0);
    }

    @Override
    protected void initDefaultCommand() {
         setDefaultCommand(new ClimberCommand());
    }
}
