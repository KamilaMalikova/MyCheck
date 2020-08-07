package com.example.mycheck;


public class SystemData {
//    public String getPhoneName(){
//        return System.getProperty();
//    }

    public String getKernelVersion(){
        return System.getProperty("os.version");
    }
    public String getModel(){
        return android.os.Build.MODEL;
    }
    public String getProduct(){
        return android.os.Build.PRODUCT;
    }
}
