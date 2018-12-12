package com.niles.addresssdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.niles.address.AddressAppLike;
import com.niles.address.AddressBean;
import com.niles.address.AddressManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddressManager addressManager = AddressAppLike.getInstance().getAddressManager();
        findAddress(addressManager, "51");
        findAddress(addressManager, "5105");
        findAddress(addressManager, "469021");
        findAddress(addressManager, "460000");
        findAddress(addressManager, "4600");
        findAddress(addressManager, "41");
        findAddress(addressManager, "1000");
    }

    private void findAddress(AddressManager addressManager, String value) {
        AddressBean addressBean = addressManager.findByValue(value);
        if (addressBean == null) {
            Log.e("address", "value:" + value + " find null");
        } else {
            Log.e("address", "value:" + value + " find " + addressBean.toString());
        }
    }
}
