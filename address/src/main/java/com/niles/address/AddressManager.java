package com.niles.address;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by Niles
 * Date 2018/12/12 10:15
 * Email niulinguo@163.com
 */
public class AddressManager {

    private List<ProvinceBean> mProvinceBeanList;

    public List<ProvinceBean> getProvinceBeanList() {
        return mProvinceBeanList;
    }

    void setProvinceBeanList(List<ProvinceBean> provinceBeanList) {
        mProvinceBeanList = provinceBeanList;
    }

    public AddressBean findByValue(String value) {
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        for (ProvinceBean provinceBean : mProvinceBeanList) {
            if (value.startsWith(provinceBean.getValue())) {
                AddressBean addressBean = provinceBean.findByValue(value);
                if (addressBean != null) {
                    return addressBean;
                } else {
                    return provinceBean;
                }
            }
        }
        return null;
    }
}
