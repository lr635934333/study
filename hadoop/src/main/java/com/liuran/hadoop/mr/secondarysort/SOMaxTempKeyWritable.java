package com.liuran.hadoop.mr.secondarysort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class SOMaxTempKeyWritable implements WritableComparable<SOMaxTempKeyWritable>{
    private int year;
    private float temp;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int compareTo(SOMaxTempKeyWritable o) {
        if (o.year == year){
            if (o.temp == temp){
                return 0;
            } else if (temp > o.temp){
                return -1;
            } else {
                return 1;
            }
        }

        return year - o.year;
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(year);
        out.writeFloat(temp);
    }

    public void readFields(DataInput in) throws IOException {
        year = in.readInt();
        temp = in.readFloat();
    }
}
