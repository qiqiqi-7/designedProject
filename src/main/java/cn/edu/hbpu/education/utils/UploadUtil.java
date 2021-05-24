package cn.edu.hbpu.education.utils;

import java.io.File;

public class UploadUtil {
    private UploadUtil(){

    }
    public static File getUploadFile(String suffix,String oldFilename,String uuid,String uploadpath){
        String endwith = oldFilename.substring(oldFilename.lastIndexOf("."));
        String newfileName = suffix+uuid+endwith;
        System.out.println("路径是:"+ uploadpath+"/"+newfileName);
        return new File(uploadpath,newfileName);
    }






}
