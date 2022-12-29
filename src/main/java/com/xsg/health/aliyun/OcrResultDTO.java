/**
  * Copyright 2022 json.cn 
  */
package com.xsg.health.aliyun;
import lombok.Data;

import java.util.List;


@Data
public class OcrResultDTO {

    private String algo_version;
    private int angle;
    private String content;
    private int height;
    private int orgHeight;
    private int orgWidth;
    private String prism_version;
    private int prism_wnum;
    private List<PrismWordsInfoDTO> prism_wordsInfo;
    private int width;
}