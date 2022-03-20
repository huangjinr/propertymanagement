package cn.property.utils;

import lombok.Data;

@Data
public class AjaxResponseBody {

    private Object code;
    private String msg;
    private Object data;
    private String result;
}
