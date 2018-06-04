package com.liuran.web.utils.designpattern.abstractfactory;

/**
 * Created by lr on 16/3/21.
 */
public class IntelCPUFactory implements CPUFactory {
    @Override
    public void productCPU() {
        System.out.println("intel生产CPU硬件");
    }

    @Override
    public void productCPUDrive() {
         System.out.println("intel开发CPU驱动");
    }
}
