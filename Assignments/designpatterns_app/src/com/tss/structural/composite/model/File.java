package com.tss.structural.composite.model;

public class File implements IFileSystem {

	String fileName;

    public File(String name){
        this.fileName = name;
    }

	@Override
	public void ls() {
		// TODO Auto-generated method stub
		System.out.println("file name " + fileName);
	}

}
