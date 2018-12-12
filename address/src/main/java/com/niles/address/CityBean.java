package com.niles.address;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by Niles
 * Date 2018/12/12 10:07
 * Email niulinguo@163.com
 */
public class CityBean extends AddressBean {

    private List<AreaBean> mAreaBeanList;

    public List<AreaBean> getAreaBeanList() {
        return mAreaBeanList;
    }

    public void setAreaBeanList(List<AreaBean> areaBeanList) {
        mAreaBeanList = areaBeanList;
    }

    @Override
    public AddressBean findByValue(String value) {
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        for (AreaBean areaBean : mAreaBeanList) {
            if (value.startsWith(areaBean.getValue())) {
                AddressBean addressBean = areaBean.findByValue(value);
                if (addressBean != null) {
                    return addressBean;
                } else {
                    return areaBean;
                }
            }
        }
        return null;
    }
}
