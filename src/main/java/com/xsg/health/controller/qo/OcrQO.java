package com.xsg.health.controller.qo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class OcrQO {

    MultipartFile ocrImg;
}
