package com.xsg.health.controller;

import com.xsg.health.aliyun.AliyunService;
import com.xsg.health.controller.qo.OcrQO;
import com.xsg.health.dto.HealthMetrics;
import com.xsg.health.dto.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/ocr")
public class OcrController {

    @Autowired
    AliyunService aliyunService;

    @RequestMapping("/recognition")
    public HttpResponse<HealthMetrics> recognition(MultipartFile ocrImg) {

        HealthMetrics recognition;
        try {
            recognition = aliyunService.recognition(ocrImg.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return HttpResponse.success(recognition);
    }
}
