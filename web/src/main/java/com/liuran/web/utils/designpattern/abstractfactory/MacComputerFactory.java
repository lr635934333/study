package com.liuran.web.utils.designpattern.abstractfactory;

/**
 * Created by lr on 16/3/21.
 */
public class MacComputerFactory implements AbstractComputerFactory {
    @Override
    public CPUFactory createCPUFactory() {
        return new IntelCPUFactory();
    }

    @Override
    public DisplayFactory createDisplayFactory() {
        return new MacDisplayFactory();
    }

    @Override
    public MotherboardFactory createMotherboardFactory() {
        return new MacMotherboardFactory();
    }
}
