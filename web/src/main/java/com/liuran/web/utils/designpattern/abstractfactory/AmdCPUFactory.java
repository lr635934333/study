package com.liuran.web.utils.designpattern.abstractfactory;

/**
 * Created by lr on 16/3/21.
 */
public class AmdCPUFactory implements  CPUFactory
{
    @Override
    public void productCPU() {
        System.out.println("AMD生产CUP硬件");
    }

    @Override
    public void productCPUDrive() {
        System.out.println("AMD开发CUP驱动");
    }
}
