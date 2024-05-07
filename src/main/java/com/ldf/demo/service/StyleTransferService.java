package com.ldf.demo.service;



import com.ldf.demo.dto.ImageDTO;
import com.ldf.demo.dto.Result;

import java.util.List;
public interface StyleTransferService {
    Result transfer(String imgDTOStr, String style);
    String hello(String msg);
    List<String>  transferFromText(String text, String style);
}