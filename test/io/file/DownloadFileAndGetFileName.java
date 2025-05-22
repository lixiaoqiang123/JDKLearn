package io.file;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.text.UnicodeUtil;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class DownloadFileAndGetFileName {

    public static void main(String[] args) {
        String fileUrl = "http://192.168.115.68:8040/license-api-release/m/v/material/fileDownloadByFileId?access_token=vX_JbsDIbnhCyUqRITQ3-7JwUaM&accountId=Dzyzpt@2024&useFor=Test&fileId=65f16a7d73c23b2f5aae072e";
        //根据url获取content-disposition头信息中的filename
        try{
            URL url = new URL(fileUrl);
            URLConnection connection = url.openConnection();
            // 获取所有响应头字段
            Map<String, List<String>> headers = connection.getHeaderFields();
            // 查找 Content-Disposition 头
            List<String> contentDispositions = headers.get("Content-Disposition");
            String fileName = "material.ofd";
            if (contentDispositions != null) {
                String contentDisposition = contentDispositions.get(0);
                System.out.println("Content-Disposition: " + URLDecoder.decode(contentDisposition.split("=")[1], Charset.forName("UTF-8")));
                fileName = contentDisposition.split("=")[1];
            }
            // 下载文件

        }catch (Exception e){

        }
    }


}
