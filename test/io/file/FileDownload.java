package io.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class FileDownload {

    public static void main(String[] args) {
        String fileUrl = "http://192.168.115.68:8040/license-api-release/m/v/material/fileDownloadByFileId?access_token=Alt3mc9x6rjBMmQzILr061retE0&accountId=Dzyzpt@2024&useFor=Test&fileId=65f2a95473c23b2f5aae0741";
        File file = getFileByHttpURL(fileUrl);
        System.out.println(file.getName());
    }

    private static File getFileByHttpURL(String path) {
        String newUrl = path.split("[?]")[0];
        String[] suffix = newUrl.split("/");
        //得到最后一个分隔符后的名字
        String fileName = suffix[suffix.length - 1];
        File file = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            //创建临时文件
            file = File.createTempFile("form", fileName);
            URL urlFile = new URL(newUrl);
            inputStream = urlFile.openStream();
            outputStream = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
