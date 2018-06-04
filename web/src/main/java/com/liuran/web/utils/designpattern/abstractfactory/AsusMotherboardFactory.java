package com.liuran.web.utils.designpattern.abstractfactory;

/**
 * Created by lr on 16/3/21.
 */
public class AsusMotherboardFactory implements  MotherboardFactory {
    @Override
    public void produceMotherboard() {
        System.out.println("ASUS生产主板");
    }
}
