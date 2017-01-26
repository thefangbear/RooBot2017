package org.usfirst.frc.team4373.robot.input.filter;

/**
 * Piecewise linear function for more granular joystick control.
 * @author (tesla)
 */
public class PiecewiseFilter2 implements IFilter {
    @Override
    public Object applyFilter(Object input) {
        Double x = (Double)input;
        double y;
        if (Math.abs(x) <= 0.8) {
            y = 0.5 * x;
        } else {
            y = (3 * x) - (Math.signum(x) * 2);
        }
        return y;
    }
}
