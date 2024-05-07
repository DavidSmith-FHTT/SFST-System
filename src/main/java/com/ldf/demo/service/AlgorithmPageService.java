package com.ldf.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface AlgorithmPageService {
    String upload(MultipartFile fileUpload, MultipartFile upload);

    String[] transferImages(String textInput, String style);

    void AlgorithmGererateImage(String src_txt);
}
