package com.spring.web.app.dto.errors;

public class ErrorResponse {
    private Integer code;
    private String error;
    private String message;

    public ErrorResponse(
            Integer code,
            String error,
            String message
    ) {
        this.error = error;
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
