package org.usfirst.frc.team4373.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4373.robot.RobotMap;

/**
 * Programmatic representation of physical gear intake components.
 * @author aaplmath
 */
public class GearIntake extends Subsystem {
    private CANTalon gearIntakeTalon;

    private static GearIntake gearIntake = null;

    public static GearIntake getGearIntake() {
        gearIntake = gearIntake == null ? new GearIntake() : gearIntake;
        return gearIntake;
    }

    private GearIntake() {
        gearIntakeTalon = new CANTalon(RobotMap.GEAR_INTAKE_MOTOR);
        gearIntakeTalon.changeControlMode(CANTalon.TalonControlMode.Position);
    }

    /**
     * Turns the gear intake to a specified angle.
     * @param degrees The angle to which to turn.
     */
    public void turnToRotation(int degrees) {
        // TODO: Implementation
    }

    @Override
    protected void initDefaultCommand() {
//        setDefaultCommand(new GearIntakeCommand());
    }
}
