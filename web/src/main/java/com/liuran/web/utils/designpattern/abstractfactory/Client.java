package com.liuran.web.utils.designpattern.abstractfactory;

/**
 * Created by lr on 16/3/21.
 */
public class Client {
    public static void main(String[] args){
        System.out.println("macbook");
        AbstractComputerFactory macbook = new MacComputerFactory();
        CPUFactory maccpu = macbook.createCPUFactory();
        maccpu.productCPU();
        maccpu.productCPUDrive();
        DisplayFactory macdisplay = macbook.createDisplayFactory();
        macdisplay.produceDisplay();
        MotherboardFactory macmb = macbook.createMotherboardFactory();
        macmb.produceMotherboard();

        System.out.println();
        System.out.println("DIY电脑");
        AbstractComputerFactory diycomputer = new DiyComputerFactory();
        CPUFactory diyCpu = diycomputer.createCPUFactory();
        diyCpu.productCPU();
        diyCpu.productCPUDrive();
        DisplayFactory diyDisplay = diycomputer.createDisplayFactory();
        diyDisplay.produceDisplay();
        MotherboardFactory diymb = diycomputer.createMotherboardFactory();
        diymb.produceMotherboard();

        System.out.println();
        System.out.println("三星笔记本");
        AbstractComputerFactory samsung = new SamsungComputerFactory();
        CPUFactory samsungCpu = samsung.createCPUFactory();
        samsungCpu.productCPU();
        samsungCpu.productCPUDrive();
        DisplayFactory samsungDisplay = samsung.createDisplayFactory();
        samsungDisplay.produceDisplay();
        MotherboardFactory samsungmb = samsung.createMotherboardFactory();
        samsungmb.produceMotherboard();



    }

}
