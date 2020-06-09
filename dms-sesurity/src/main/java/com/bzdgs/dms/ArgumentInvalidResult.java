//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bzdgs.dms;

import java.io.Serializable;

public class ArgumentInvalidResult implements Serializable {
    private static final long serialVersionUID = -6932677619994486228L;

    private String field;

    private Object rejectedValue;

    private String defaultMessage;

    public ArgumentInvalidResult() {
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedValue() {
        return this.rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getDefaultMessage() {
        return this.defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
