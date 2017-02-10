package org.usfirst.frc.team4373.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4373.robot.RobotMap;
import org.usfirst.frc.team4373.robot.commands.teleop.GearArmCommand;

/**
 * Programmatic representation of physical gear intake components.
 * @author aaplmath
 */
public class GearMechanismArm extends Subsystem {
    private Servo gearIntakeServo;

    private static GearMechanismArm gearMechanismArm = null;

    public static GearMechanismArm getGearMechanismArm() {
        gearMechanismArm = gearMechanismArm == null ? new GearMechanismArm() : gearMechanismArm;
        return gearMechanismArm;
    }

    private GearMechanismArm() {
        gearIntakeServo = new Servo(RobotMap.GEAR_INTAKE_MOTOR);
    }

    /**
     * Turns the gear intake to a specified angle.
     * @param degrees The angle to which to turn.
     */
    public void turnToRotation(double degrees) {
        gearIntakeServo.setAngle(degrees);
    }

    /**
     * Gets the arm's current rotation in degrees.
     * @return The current rotation of the servo.
     */
    public double getRotation() {
        return gearIntakeServo.getAngle();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new GearArmCommand());
    }
}
