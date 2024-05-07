package com.ldf.demo.controller;

import com.ldf.demo.service.AlgorithmPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlgorithmPageController {

    @Autowired
    AlgorithmPageService algorithmPageService;

    @GetMapping("/algorithm")
    public String algorithm(Model model){
        model.addAttribute("filename", "images/22.png");
        model.addAttribute("transferImg", "images/22.png");
        return "algorithmpage";
    }

    @PostMapping("/upload")

    public ModelAndView upload(@RequestParam("file") MultipartFile fileUpload,  ModelAndView modelAndView){
        modelAndView.setViewName("algorithmpage");
        String imgPath = algorithmPageService.upload(fileUpload, fileUpload);
//        try {
//            modelAndView.addObject("imgCode", encodeImage(imgPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        modelAndView.addObject("filename","/image/" + imgPath);
        modelAndView.addObject("transferImg","/image/decode.jpg");
        return modelAndView;
    }

    @GetMapping("/font2img")
    public ModelAndView font2img(ModelAndView modelAndView){
        modelAndView.setViewName("success");
//        modelAndView.addObject("test", "0.jpg");
//        modelAndView.addObject("images", new String[]{"0.jpg", "1.jpg"});
        return modelAndView;
    }

    @GetMapping("/transferImages")
    public ModelAndView transferimges(@RequestParam("textInput") String textInput, @RequestParam("style") String style, ModelAndView modelAndView){
        System.out.println("文本:" + textInput);
        String[] imgs = algorithmPageService.transferImages(textInput, style);

        modelAndView.addObject("images", imgs);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/getAlgorithmPage")
    public String transferImages(@RequestParam(value = "src_txt") String src_txt) {
        System.out.println(src_txt);
        algorithmPageService.AlgorithmGererateImage(src_txt);

        return "success";
    }
}

