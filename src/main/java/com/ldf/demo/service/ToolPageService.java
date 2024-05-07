package com.ldf.demo.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public interface ToolPageService {
    void download(String fileName, HttpServletResponse response);

    void generateCharacter(String text, int style, String imgLocation);
}
