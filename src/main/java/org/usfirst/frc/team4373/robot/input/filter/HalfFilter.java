package org.usfirst.frc.team4373.robot.input.filter;

/**
 * HalfFilter simply divides input by 2.
 * @author Henry Pitcairn
 */
public class HalfFilter implements IFilter {
    @Override
    public Object applyFilter(Object input) {
        return (Double)input / 2;
    }
}
