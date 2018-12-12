package com.niles.address;

import android.support.annotation.NonNull;

/**
 * Created by Niles
 * Date 2018/12/12 10:05
 * Email niulinguo@163.com
 */
public abstract class AddressBean {

    private String value;
    private String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public abstract AddressBean findByValue(String value);

    @NonNull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
