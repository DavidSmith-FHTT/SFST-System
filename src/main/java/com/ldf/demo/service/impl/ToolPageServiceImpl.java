package com.ldf.demo.service.impl;

import com.ldf.demo.service.ToolPageService;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
//reference： https://www.cnblogs.com/tomcat985/p/15131241.html
@Service
public class ToolPageServiceImpl implements ToolPageService {
    @Override
    public void download(String fileName, HttpServletResponse response) {
        String rootPath = "D:/";
        File file = new File(rootPath + fileName);
        // 2.将文件读取为文件输入流
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            // 2.1 获取响应流之前  一定要设置以附件形式下载
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            // 3.获取响应输出流
            ServletOutputStream os = response.getOutputStream();
            // 4.输入流复制给输出流
            FileCopyUtils.copy(is, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void generateCharacter(String text, int style, String imgLocation) {
        try {
            String[] my_args = new String[] {"python","./font_library/drawChinese.py",
                    String.valueOf(text),String.valueOf(style), imgLocation};
            Process proc =  Runtime.getRuntime().exec(my_args);

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
            reader.close();
            proc.waitFor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
