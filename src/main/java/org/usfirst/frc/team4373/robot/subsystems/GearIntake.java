package org.usfirst.frc.team4373.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4373.robot.RobotMap;

/**
 * Programmatic representation of physical gear intake components.
 * @author aaplmath
 */
public class GearIntake extends Subsystem {
    private Servo gearIntakeServo;

    private static GearIntake gearIntake = null;

    public static GearIntake getGearIntake() {
        gearIntake = gearIntake == null ? new GearIntake() : gearIntake;
        return gearIntake;
    }

    private GearIntake() {
        gearIntakeServo = new Servo(RobotMap.GEAR_INTAKE_MOTOR);
    }

    /**
     * Turns the gear intake to a specified angle.
     * @param degrees The angle to which to turn.
     */
    public void turnToRotation(int degrees) {
        gearIntakeServo.setAngle(degrees);
    }

    @Override
    protected void initDefaultCommand() {
//        setDefaultCommand(new GearIntakeCommand());
    }
}
