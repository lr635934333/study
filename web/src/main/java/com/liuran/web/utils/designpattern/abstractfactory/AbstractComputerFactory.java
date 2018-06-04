package com.liuran.web.utils.designpattern.abstractfactory;

/**
 * Created by lr on 16/3/21.
 */
public interface AbstractComputerFactory {

    public CPUFactory createCPUFactory();
    public DisplayFactory createDisplayFactory();
    public MotherboardFactory createMotherboardFactory();

}
