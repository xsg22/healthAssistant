package com.xsg.health.dto;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HttpResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <Z> HttpResponse<Z> success(Z data) {
        HttpResponse<Z> response = new HttpResponse();
        response.setCode(HttpStatus.OK.value());
        response.setMsg(HttpStatus.OK.name());
        response.setData(data);

        return response;
    }
}
