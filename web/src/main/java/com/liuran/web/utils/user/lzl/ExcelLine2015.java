package com.liuran.web.utils.user.lzl;

import jxl.Cell;

public class ExcelLine2015 extends ExcelLine2016{
    public ExcelLine2015(String cardId, String name) {
        super(cardId, name);
    }

    public ExcelLine2015(Cell[] row){
        super(row);
    }
}
