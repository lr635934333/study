package com.liuran.web.utils.designpattern.abstractfactory;

/**
 * Created by lr on 16/3/21.
 */
public class MacDisplayFactory implements  DisplayFactory{

    public void produceDisplay(){
        System.out.println("MAC生产显示器");
    }

}
