package com.mvp.framework;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mvp.framework.module.test.bean.NuoMiShopInfoBean;
import com.mvp.framework.module.test.bean.NuoMiCategoryBean;
import com.mvp.framework.module.test.bean.WeatherBean;
import com.mvp.framework.module.test.params.NuoMiShopInfoParams;
import com.mvp.framework.module.test.params.WeatherParams;
import com.mvp.framework.module.test.presenter.NuoMiShopInfoPresenter;
import com.mvp.framework.module.test.presenter.NuoMiCategoryPresenter;
import com.mvp.framework.module.test.presenter.WeatherPresenter;
import com.mvp.framework.module.test.view.activity.TestActivity;
import com.mvp.framework.module.test.view.iview.INuoMiShopInfoView;
import com.mvp.framework.module.test.view.iview.INuoMiCategoryView;
import com.mvp.framework.module.test.view.iview.IWeatherView;
import com.mvp.framework.utils.ListUtils;
import com.mvp.framework.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements IWeatherView
        ,INuoMiCategoryView,INuoMiShopInfoView {

    private static final String TAG = "MainActivity";

    private Button weatherBtn;
    private Button nuoMiCategoryBtn;
    private Button nuoMiShopInfoBtn;
    private Button testBtn;

    private WeatherPresenter presenter;
    private NuoMiCategoryPresenter nuoMiCategoryPresenter;
    private NuoMiShopInfoPresenter nuoMiShopInfoPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        presenter = new WeatherPresenter(this);
        nuoMiCategoryPresenter = new NuoMiCategoryPresenter(this);
        nuoMiShopInfoPresenter = new NuoMiShopInfoPresenter(this);


    }

    private void initView(){
        weatherBtn = (Button) findViewById(R.id.weather_btn);
        nuoMiCategoryBtn = (Button) findViewById(R.id.nuo_mi_category_btn);
        nuoMiShopInfoBtn = (Button) findViewById(R.id.nuo_mi_shop_info_btn);
        testBtn = (Button) findViewById(R.id.go_test) ;

        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherParams params = new WeatherParams();
                params.cityname = "北京";
                presenter.accessServer(params);
            }
        });

        nuoMiCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuoMiCategoryPresenter.refresh(null);
            }
        });


        nuoMiShopInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NuoMiShopInfoParams params = new NuoMiShopInfoParams();
                params.shop_id = "1745896";
                nuoMiShopInfoPresenter.accessServer(params);
            }
        });

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void showProcess(boolean show) {

    }

    @Override
    public void showVolleyError(int errorCode, String errorDesc, String apiInterface) {

    }

    @Override
    public void showServerError(int errorCode, String errorDesc) {
        LogUtil.e(MainActivity.class,"errorCode = " + errorCode + " & errorDesc = " + errorDesc);
        Toast.makeText(this,errorDesc,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showWeatherView(WeatherBean data) {
        Toast.makeText(this,data.weather,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNuoMiCategoryView(List<NuoMiCategoryBean> nuoMiCategoryList) {
        Toast.makeText(this,"成功,第一个分类为："
                + nuoMiCategoryList.get(0).cat_name,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showNuoMiShopInfoView(NuoMiShopInfoBean nuoMiShopInfo) {
        Toast.makeText(this,"商户名称： " + nuoMiShopInfo.shopName,Toast.LENGTH_SHORT).show();
    }
}
