package com.example.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 */
public class Page<T> implements Serializable {
    private Integer current;
    private Integer size;
    private Integer total;
    private Integer pages;
    private List<T> records = new ArrayList<T>();

    public Page() {
    }
    public Page(Integer current,Integer size) {
        this.current = (current-1)*size;
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
        if(total != 0){
            this.pages = this.total / this.size;
            if(this.total % this.size != 0){
                this.pages++;
            }
        }
    }

    public Integer getPages() {
        return pages;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Page{" +
                "current=" + current +
                ", size=" + size +
                ", total=" + total +
                ", pages=" + pages +
                ", records=" + records +
                '}';
    }
}
