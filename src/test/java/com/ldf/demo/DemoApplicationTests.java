package com.ldf.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void Character() throws IOException, InterruptedException{

        // 设置Python解释器路径
        String pythonInterpreterPath = "D:/Anaconda3/envs/py37/python.exe";

        // 设置Python脚本路径和参数
        String pythonScriptPath = "D:/Project/myzi2zi/infer.py";
        String experimentDir = "experiment";
        String gpuIds = "cuda:0";
        int batchSize = 32;
        int resume = 90000;
        boolean fromTxt = true;
        String srcFont = "./ttf/FZBangSKJF.TTF";
        String srcTxt = "今天是个好日子";

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
        command.add(srcTxt);
        command.add("--run_all_label");
        ProcessBuilder pb = new ProcessBuilder(command);

        // 来设置工作目录，使得 Python 脚本在正确的目录下运行
        pb.directory(new File("D:/Project/myzi2zi/"));

        // 设置Python路径，所有第三方包的位置
        pb.environment().put("PYTHONPATH", "D:/Anaconda3/envs/py37/Lib/site-packages");

        // 执行命令
        Process process = pb.start();

        // 获取命令输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // 等待命令执行完成
        int exitCode = process.waitFor();
        System.out.println("命令执行完成，退出码：" + exitCode);

        // 打印标准错误输出，来查看是否有错误或警告
        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String errorLine;
        while ((errorLine = error.readLine()) != null) {
            System.out.println(errorLine);
        }

    }
}
