package com.example.basemvplib.view.iview;

import com.example.basemvplib.view.iview.IBaseMvpView;

/**
 * @ClassName: IMvpView
 * @author create by Tang
 * @date date 16/10/26 下午4:51
 * @Description: TODO
 */

public interface IMvpView extends IBaseMvpView {

    /**
     * @Method: isSucceed
     * @author create by Tang
     * @date date 16/10/26 下午4:33
     * @Description: 通知View层获取数据成功
     * 在获取简单数据的时候用到
     */
    void showSucceed(boolean isSucceed);
}
