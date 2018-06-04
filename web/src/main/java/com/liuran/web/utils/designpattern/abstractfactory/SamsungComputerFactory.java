package com.liuran.web.utils.designpattern.abstractfactory;

/**
 * Created by lr on 16/3/21.
 */
public class SamsungComputerFactory implements AbstractComputerFactory {
    @Override
    public CPUFactory createCPUFactory() {
        return new IntelCPUFactory();
    }

    @Override
    public DisplayFactory createDisplayFactory() {
        return new SamsungDisplayFactory();
    }

    @Override
    public MotherboardFactory createMotherboardFactory() {
        return new AsusMotherboardFactory();
    }
}
