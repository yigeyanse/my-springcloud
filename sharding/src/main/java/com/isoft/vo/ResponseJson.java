package com.isoft.vo;

import lombok.Data;

@Data
public class ResponseJson {

    private boolean success;
    private int code;
    private String msg;
    private Object data;
}
