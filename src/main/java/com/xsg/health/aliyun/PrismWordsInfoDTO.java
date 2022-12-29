package com.xsg.health.aliyun;

import lombok.Data;

import java.util.List;

@Data
public class PrismWordsInfoDTO {
    private int angle;
    private int direction;
    private int height;
    private List<PosDTO> pos;
    private int prob;
    private int width;
    private String word;
    private int x;
    private int y;
}