package org.usfirst.frc.team4373.robot.input.filter;

/**
 * ThirdFilter simply divides input by 3.
 * @author Henry Pitcairn
 */
public class ThirdFilter implements IFilter {
    @Override
    public Object applyFilter(Object input) {
        return (Double)input / 3;
    }
}
