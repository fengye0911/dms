//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bzdgs.dms.resault;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseEntity<T> implements Serializable {
    private static final long serialVersionUID = -1L;

    @JsonView({ResponseEntity.IResponseEntityView.class})
    private String code;

    @JsonView({ResponseEntity.IResponseEntityView.class})
    private String message;
    @JsonView({ResponseEntity.IResponseEntityView.class})
    private T data;
    @JsonView({ResponseEntity.IResponseEntityView.class})
    private List<ArgumentInvalidResult> error;

    public ResponseEntity() {
        this.error = new ArrayList();
    }

    public ResponseEntity(int code, String message) {
        this.error = new ArrayList();
        this.code = String.valueOf(code);
        this.message = message;
    }

    public ResponseEntity(String code, String message) {
        this.error = new ArrayList();
        this.code = code;
        this.message = message;
    }

    public ResponseEntity(int code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public ResponseEntity(String code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public List<ArgumentInvalidResult> getError() {
        return this.error;
    }

    public void setError(List<ArgumentInvalidResult> error) {
        this.error = error;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @JsonIgnore
    public int getCode() {
        return Integer.valueOf(this.code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCode(int code) {
        this.code = String.valueOf(code);
    }

    @JsonIgnore
    public String getFullCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "ResponseEntity [code=" + this.code + ", message=" + this.message + ", data=" + this.data + ", error=" + this.error + "]";
    }

    public interface IResponseEntityView {
    }
}
