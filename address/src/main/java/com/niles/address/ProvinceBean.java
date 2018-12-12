package com.niles.address;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by Niles
 * Date 2018/12/12 10:07
 * Email niulinguo@163.com
 */
public class ProvinceBean extends AddressBean {

    private List<CityBean> mCityBeanList;

    public List<CityBean> getCityBeanList() {
        return mCityBeanList;
    }

    public void setCityBeanList(List<CityBean> cityBeanList) {
        mCityBeanList = cityBeanList;
    }

    @Override
    public AddressBean findByValue(String value) {
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        for (CityBean cityBean : mCityBeanList) {
            if (value.startsWith(cityBean.getValue())) {
                AddressBean addressBean = cityBean.findByValue(value);
                if (addressBean != null) {
                    return addressBean;
                } else {
                    return cityBean;
                }
            }
        }
        return null;
    }
}
