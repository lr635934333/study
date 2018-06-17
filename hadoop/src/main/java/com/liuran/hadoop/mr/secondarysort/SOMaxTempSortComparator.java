package com.liuran.hadoop.mr.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SOMaxTempSortComparator extends WritableComparator{
    protected SOMaxTempSortComparator(){
        super(SOMaxTempKeyWritable.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        SOMaxTempKeyWritable key1 = (SOMaxTempKeyWritable) a;
        SOMaxTempKeyWritable key2 = (SOMaxTempKeyWritable) b;
        return key1.compareTo(key2);
    }
}
