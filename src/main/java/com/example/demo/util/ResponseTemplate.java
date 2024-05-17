package com.example.demo.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Service
public class ResponseTemplate<T> {
    private Integer status;
    private String message;
    private T data;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseTemplate<List<?>> build (Integer responseCode, String message, List<?> data) {
        ResponseTemplate<List<?>> response = new ResponseTemplate<>();
        response.setStatus(responseCode);
        response.setMessage(message);
        response.setData(data);

        return response;
    }

    public ResponseTemplate<String> build (Integer responseCode, String message) {
        ResponseTemplate<String> response = new ResponseTemplate<String>();
        response.setStatus(responseCode);
        response.setMessage(message);
        response.setData(null);

        return response;
    }

    public ResponseTemplate<Object> build (Integer responseCode, String message, Object data) {
        ResponseTemplate<Object> response = new ResponseTemplate<Object>();
        response.setStatus(responseCode);
        response.setMessage(message);
        response.setData(data);

        return response;
    }
}
