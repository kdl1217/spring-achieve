package com.kon.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * API data 返回数据
 *
 * @author kon, created on 2021/12/10T14:34.
 * @version 1.0.0-SNAPSHOT
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApiData<T> {

    private int code;

    private String message;

    private T data;

    public ApiData(int code) {
        this.code = code;
    }

    public ApiData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiData<String> getSuccess(int code, String message) {
        return new ApiData<>(code, message);
    }

}
