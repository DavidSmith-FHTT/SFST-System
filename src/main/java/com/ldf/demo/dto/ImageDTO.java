package com.ldf.demo.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ImageDTO implements Serializable{
    private static final long serialVersionUID = 2L;
    String format;
    String img;
}