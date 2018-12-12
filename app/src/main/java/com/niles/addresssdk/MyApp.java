package com.niles.addresssdk;

import com.niles.address.AddressAppLike;
import com.niles.separate.application.ApplicationLikeManager;
import com.niles.separate.application.SeparateApplication;

/**
 * Created by Niles
 * Date 2018/12/12 09:17
 * Email niulinguo@163.com
 */
public class MyApp extends SeparateApplication {

    @Override
    protected void onRegisterApplicationLikeManager(ApplicationLikeManager likeManager) {
        super.onRegisterApplicationLikeManager(likeManager);
        likeManager.register(new AddressAppLike());
    }
}
