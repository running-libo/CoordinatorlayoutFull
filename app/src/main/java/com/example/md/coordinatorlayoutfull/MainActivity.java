package com.example.md.coordinatorlayoutfull;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.iv_useravator)
    ImageView ivUseravator;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    private String[] titles = new String[]{"热门", "最新"};
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
        setAvatorChange();
    }

    private void init() {
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new PartClassifyFragment());
            tablayout.addTab(tablayout.newTab());
        }

        viewpager.setAdapter(new FmPagerAdapter(fragments, getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewpager);

        for (int j = 0; j < titles.length; j++) {
            tablayout.getTabAt(j).setText(titles[j]);
        }

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.internet_star);
        ivBg.setImageBitmap(BlurImageView.BlurImages(bitmap));
    }

    /**
     * 渐变toolbar背景
     */
    private void setAvatorChange() {
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //verticalOffset始终为0以下的负数
                float percent = (Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());

                toolbar.setBackgroundColor(changeAlpha(Color.WHITE,percent));
            }
        });
    }

    /** 根据百分比改变颜色透明度 */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }
}
