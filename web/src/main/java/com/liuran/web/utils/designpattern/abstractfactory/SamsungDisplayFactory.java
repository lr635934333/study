package com.liuran.web.utils.designpattern.abstractfactory;

/**
 * Created by lr on 16/3/21.
 */
public class SamsungDisplayFactory implements  DisplayFactory {
    @Override
    public void produceDisplay() {
        System.out.println("samsung生产显示器");
    }
}
