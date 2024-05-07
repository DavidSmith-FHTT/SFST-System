package com.ldf.demo.controller;

import com.ldf.demo.service.ToolPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ldf.demo.utils.Base64Utils.encodeImage;

@Slf4j
@CrossOrigin
@Controller
public class ToolPageController {

    @Autowired
    ToolPageService toolPageService;

    @Value("${imgLocation}")
    private String imgLocation;

    @GetMapping("/tool")
    public String tool(Model model){
        return "tool";
    }

    @GetMapping("/attachimage")
    public String attachimage(){
        return "toolimage/attachimage";
    }

    @GetMapping("/attachimagevertical")
    public String attachimagevertical(){
        return "toolimage/attachimagevertical";
    }

    @GetMapping("/imagecompression")
    public String imagecompression(){
        return "toolimage/imagecompression";
    }

    @GetMapping("/imagefilter")
    public String imagefilter(){
        return "toolimage/imagefilter";
    }

    @GetMapping("/imagetrim")
    public String imagetrim(){
        return "toolimage/imagetrim";
    }

    @GetMapping("/toolpage")
    public String toolpage(Model model){
        try {
            model.addAttribute("imgCode", encodeImage("D:/QQ.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "toolimage/toolpage";
    }

    @RequestMapping("/getstyle")
    public ModelAndView outputmessage(@RequestParam(value = "character") String character, @RequestParam(value = "style") int style, ModelAndView modelAndView){
        toolPageService.generateCharacter(character, style, imgLocation);
        log.info("character:" + character + "  style:" + style);
        modelAndView.setViewName("toolimage/toolpage");

        try {
            modelAndView.addObject("imgCode", encodeImage(imgLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @GetMapping("toolpage/download")
    public void downloadFile(String fileName, HttpServletResponse response) throws Exception {
        toolPageService.download(fileName, response);
    }
}
