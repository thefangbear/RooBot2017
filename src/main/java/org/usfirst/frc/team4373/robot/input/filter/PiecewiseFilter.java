package org.usfirst.frc.team4373.robot.input.filter;

/**
 * Piecewise linear function for more granular joystick control.
 * @author (tesla)
 */
public class PiecewiseFilter implements IFilter {
    @Override
    public Object applyFilter(Object input) {
        Double x = (Double)input;
        if (Math.abs(x) <= 0.4) {
            return 0.5 * x;
        } else if (Math.abs(x) > 0.4 && Math.abs(x) <= 0.8) {
            return (0.75 * x) - (Math.signum(x) * 0.1);
        } else {
            return (2.5 * x) - (Math.signum(x) * 1.5);
        }
    }
}
