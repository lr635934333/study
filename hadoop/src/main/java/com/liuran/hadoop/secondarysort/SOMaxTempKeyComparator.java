package com.liuran.hadoop.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SOMaxTempKeyComparator extends WritableComparator{
    protected SOMaxTempKeyComparator(){
        super(SOMaxTempKeyWritable.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        SOMaxTempKeyWritable key1 = (SOMaxTempKeyWritable) a;
        SOMaxTempKeyWritable key2 = (SOMaxTempKeyWritable) b;
        return key1.compareTo(key2);
    }
}
