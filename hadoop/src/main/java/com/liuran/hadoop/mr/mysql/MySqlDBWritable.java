package com.liuran.hadoop.mr.mysql;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlDBWritable implements Writable ,DBWritable{
    private String id;
    private String txt;
    private String word;
    private int count;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(txt);
        out.writeInt(count);
        out.writeUTF(word);
    }

    public void readFields(DataInput in) throws IOException {
        id = in.readUTF();
        txt = in.readUTF();
        count = in.readInt();
        word = in.readUTF();
    }

    public void write(PreparedStatement statement) throws SQLException {
        statement.setString(1, id);
        statement.setString(2, word);
        statement.setInt(3, count);
    }

    public void readFields(ResultSet resultSet) throws SQLException {
        id = resultSet.getString(1);
        txt = resultSet.getString(2);
    }
}
