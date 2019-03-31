package com.niles.address;

import android.content.res.AssetManager;
import android.util.Log;

import com.niles.separate.application.AbsApplicationLike;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niles
 * Date 2018/12/12 09:18
 * Email niulinguo@163.com
 */
public class AddressAppLike extends AbsApplicationLike {

    private static AddressAppLike sInstance;
    private AddressManager mAddressManager;

    public static AddressAppLike getInstance() {
        return sInstance;
    }

    public AddressManager getAddressManager() {
        if (mAddressManager == null) {
            synchronized (AddressAppLike.class) {
                if (mAddressManager == null) {
                    mAddressManager = initAddressData();
                }
            }
        }
        return mAddressManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        new Thread(new Runnable() {
            @Override
            public void run() {
                AddressManager addressManager = getAddressManager();
                List<ProvinceBean> provinceBeanList = addressManager.getProvinceBeanList();
                if (BuildConfig.DEBUG) {
                    Log.e("address", "province size " + provinceBeanList.size());
                }
            }
        }).start();
    }

    private ArrayList<ProvinceBean> readProvinceBeanList(JSONArray jsonArray) {
        ArrayList<ProvinceBean> arrayList = new ArrayList<>();
        int length = jsonArray == null ? 0 : jsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            if (jsonObject != null) {

                String value = jsonObject.optString("value");
                String label = jsonObject.optString("label");
                JSONArray children = jsonObject.optJSONArray("children");

                ProvinceBean bean = new ProvinceBean();
                bean.setValue(value);
                bean.setLabel(label);
                bean.setCityBeanList(readCityBeanList(children));
                arrayList.add(bean);
            }
        }
        return arrayList;
    }

    private ArrayList<CityBean> readCityBeanList(JSONArray jsonArray) {
        ArrayList<CityBean> arrayList = new ArrayList<>();
        int length = jsonArray == null ? 0 : jsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            if (jsonObject != null) {

                String value = jsonObject.optString("value");
                String label = jsonObject.optString("label");
                JSONArray children = jsonObject.optJSONArray("children");

                CityBean bean = new CityBean();
                bean.setValue(value);
                bean.setLabel(label);
                bean.setAreaBeanList(readAreaBeanList(children));
                arrayList.add(bean);
            }
        }
        return arrayList;
    }

    private ArrayList<AreaBean> readAreaBeanList(JSONArray jsonArray) {
        ArrayList<AreaBean> arrayList = new ArrayList<>();
        int length = jsonArray == null ? 0 : jsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            if (jsonObject != null) {

                String value = jsonObject.optString("value");
                String label = jsonObject.optString("label");

                AreaBean bean = new AreaBean();
                bean.setValue(value);
                bean.setLabel(label);
                arrayList.add(bean);
            }
        }
        return arrayList;
    }

    private AddressManager initAddressData() {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            AssetManager assetManager = getApplication().getAssets();
            inputStream = assetManager.open("pac.json");
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            String jsonString = outputStream.toString();
            JSONArray jsonArray = new JSONArray(jsonString);
            ArrayList<ProvinceBean> provinceBeans = readProvinceBeanList(jsonArray);
            AddressManager addressManager = new AddressManager();
            addressManager.setProvinceBeanList(provinceBeans);
            return addressManager;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
