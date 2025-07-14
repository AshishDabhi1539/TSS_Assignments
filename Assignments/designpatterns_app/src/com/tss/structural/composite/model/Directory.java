package com.tss.structural.composite.model;

import java.util.ArrayList;
import java.util.List;

public class Directory implements IFileSystem {
	String directoryName;
    List<IFileSystem> fileSystemList;

    public Directory(String name){
        this.directoryName = name;
        fileSystemList = new ArrayList<>();
    }

    public void add(IFileSystem fileSystemObj) {
        fileSystemList.add(fileSystemObj);
    }

    public void ls(){
        System.out.println("Directory name " + directoryName);

        for(IFileSystem fileSystemObj : fileSystemList){
            fileSystemObj.ls();
        }
    }
}
