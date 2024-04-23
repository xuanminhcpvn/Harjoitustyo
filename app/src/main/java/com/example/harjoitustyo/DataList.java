package com.example.harjoitustyo;

import java.util.ArrayList;


// TODO DataList for sending fetched data to recyclerView
public class DataList {
    private ArrayList<DataObject> datas = new ArrayList<>();
    private static DataList dataList = null;


    public static DataList getInstance(){
        if (dataList == null) {
            dataList = new DataList();
        }
        return dataList;
    }

    public void addDataObject(DataObject data){
        datas.add(data);
    }

    public ArrayList<DataObject> getDatas() {
        return datas;
    }
}
