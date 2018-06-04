package com.liuran.web.utils.designpattern.abstractfactory;

/**
 * Created by lr on 16/3/21.
 */
public class MacMotherboardFactory implements MotherboardFactory{
    @Override
    public void produceMotherboard() {
        System.out.println("MAC生产主板");
    }
}
