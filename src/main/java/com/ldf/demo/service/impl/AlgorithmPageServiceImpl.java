package com.ldf.demo.service.impl;

import cn.hutool.json.JSONUtil;
import com.ldf.demo.dto.ImageDTO;
import com.ldf.demo.service.AlgorithmPageService;
import com.ldf.demo.service.StyleTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ldf.demo.utils.Base64Utils.decodeImage;
import static com.ldf.demo.utils.Base64Utils.encodeImage;

@Service
public class AlgorithmPageServiceImpl implements AlgorithmPageService {

    @Autowired
    StyleTransferService styleTransferService;

    @Value("${transferImagesUpload}")
    String transferImagesUpload;

    @Override
    public String upload(MultipartFile fileUpload, MultipartFile upload) {
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID()+suffixName;
        //指定本地文件夹存储图片，写到需要保存的目录下
        String filePath = "D:\\image\\";
        File uploadFile = new File(filePath + fileName);

        try {
            //将图片保存
            fileUpload.transferTo(uploadFile);

        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageDTO imageDTO = new ImageDTO();

        try {
            //将图片编码为base64字符串，保存到imageDTO对象中
            imageDTO.setImg(encodeImage(uploadFile.getAbsolutePath()));
            imageDTO.setFormat(suffixName);

            //远程调用服务，获得转换后的数据
            String jsonData = (String) styleTransferService.transfer(JSONUtil.toJsonStr(imageDTO), "style").getData();

            //将JSON数据转换为ImageDTO对象
            ImageDTO dto = JSONUtil.toBean(jsonData, ImageDTO.class);

            //从imageDTO对象中取出图片编码，解码保存到本地
            decodeImage(dto.getImg(), "D:/image/decode.jpg", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return fileName;
    }

    @Override
    public String[] transferImages(String textInput, String style) {
        List<String> result = styleTransferService.transferFromText(textInput, style);
//      TODO textinput 格式校验

        String[] imgPath = new String[result.size()];
        try {
            for (int i = 0; i < result.size(); i++) {
                String img = result.get(i);
                String path = transferImagesUpload + File.separator + i+ ".jpg";
                decodeImage(img, path, "jpg");
                imgPath[i] = i+ ".jpg";

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return imgPath;
    }

    @Override
    public void AlgorithmGererateImage(String src_txt) {
        // 设置Python解释器路径
        String pythonInterpreterPath = "D:/Anaconda3/envs/py37/python.exe";

        // 设置Python脚本路径和参数
        String pythonScriptPath = "D:/Project/myzi2zi/infer.py";
        String experimentDir = "experiment";
        String gpuIds = "cuda:0";
        int batchSize = 32;
        int resume = 90000;
        boolean fromTxt = true;
        String srcFont = "./ttf/simsun.ttc";
//        String srcTxt = "今天是个好日子";

        // 创建ProcessBuilder对象，设置命令和参数
        List<String> command = new ArrayList<>();
        command.add(pythonInterpreterPath);
        command.add(pythonScriptPath);
        command.add("--experiment_dir");
        command.add(experimentDir);
        command.add("--gpu_ids");
        command.add(gpuIds);
        command.add("--batch_size");
        command.add(Integer.toString(batchSize));
        command.add("--resume=" + Integer.toString(resume));
        command.add("--from_txt");
        if (fromTxt) {
            command.add("--from_txt");
        }
        command.add("--src_font");
        command.add(srcFont);
        command.add("--src_txt");
        command.add(src_txt);
        command.add("--run_all_label");
        ProcessBuilder pb = new ProcessBuilder(command);

        // 来设置工作目录，使得 Python 脚本在正确的目录下运行
        pb.directory(new File("D:/Project/myzi2zi/"));

        // 设置Python路径，所有第三方包的位置
        pb.environment().put("PYTHONPATH", "D:/Anaconda3/envs/py37/Lib/site-packages");

        // 执行命令
        Process process = null;
        try {
            process = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 获取命令输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(line);
        }

        // 等待命令执行完成
        int exitCode = 0;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("命令执行完成，退出码：" + exitCode);

        // 打印标准错误输出，来查看是否有错误或警告
        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String errorLine;
        while (true) {
            try {
                if (!((errorLine = error.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(errorLine);
        }
    }

}
