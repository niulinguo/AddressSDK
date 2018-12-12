package com.niles.address;

/**
 * Created by Niles
 * Date 2018/12/12 10:07
 * Email niulinguo@163.com
 */
public class AreaBean extends AddressBean {
    @Override
    public AddressBean findByValue(String value) {
        return getValue().equals(value) ? this : null;
    }
}
