package com.gw.marquee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.MarqueeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<String> datas = Arrays.asList("《赋得古原草送别》", "离离原上草，一岁一枯荣。", "野火烧不尽，春风吹又生。", "远芳侵古道，晴翠接荒城。", "又送王孙去，萋萋满别情。");
        MarqueeView marqueeView1 = (MarqueeView) findViewById(R.id.marqueeView1);
        final MarqueeView marqueeView2 = (MarqueeView) findViewById(R.id.marqueeView2);
        final MarqueeView marqueeView3 = (MarqueeView) findViewById(R.id.marqueeView3);
        MarqueeView marqueeView4 = (MarqueeView) findViewById(R.id.marqueeView4);
        MarqueeView marqueeView5 = (MarqueeView) findViewById(R.id.marqueeView5);
        final MarqueeView marqueeView6 = (MarqueeView) findViewById(R.id.marqueeView6);
        MarqueeView marqueeView7 = (MarqueeView) findViewById(R.id.marqueeView7);

        MarqueeFactory<TextView, String> marqueeFactory1 = new NoticeMF(this);
        marqueeView1.setMarqueeFactory(marqueeFactory1);
        marqueeView1.startFlipping();
        marqueeFactory1.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(MainActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory1.setData(datas);

        final MarqueeFactory<TextView, String> marqueeFactory2 = new NoticeMF(this);
        marqueeFactory2.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(MainActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory2.setData(datas);
        marqueeView2.setMarqueeFactory(marqueeFactory2);
        marqueeView2.startFlipping();

        final MarqueeFactory<TextView, String> marqueeFactory6 = new NoticeMF(this);
        marqueeFactory6.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(MainActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory6.setData(datas);
        marqueeView6.setMarqueeFactory(marqueeFactory6);
        marqueeView6.startFlipping();

        MarqueeFactory<TextView, String> marqueeFactory3 = new NoticeMF(this);
        marqueeFactory3.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(MainActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory3.setData(datas);
        marqueeView3.setMarqueeFactory(marqueeFactory3);
        marqueeView3.setAnimInAndOut(R.anim.left_in, R.anim.right_out);
        marqueeView3.setAnimDuration(2000);
        marqueeView3.setInterval(2500);
        marqueeView3.setAnimateFirstView(true);
        //直接调用startFlipping，setAnimateFirstView并没有生效
        //marqueeView3.startFlipping();
        marqueeView3.post(new Runnable() {
            @Override
            public void run() {
                marqueeView3.startFlipping();
            }
        });

        MarqueeFactory<TextView, String> marqueeFactory4 = new NoticeMF(this);
        marqueeFactory4.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(MainActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory4.setData(datas);
        marqueeView4.setAnimInAndOut(R.anim.top_in, R.anim.bottom_out);
        marqueeView4.setMarqueeFactory(marqueeFactory4);
        marqueeView4.startFlipping();

        List<ComplexItemEntity> complexDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            complexDatas.add(new ComplexItemEntity("标题 " + i, "副标题 " + i, "时间 " + i));
        }
        MarqueeFactory<RelativeLayout, ComplexItemEntity> marqueeFactory5 = new ComplexViewMF(this);
        marqueeFactory5.setData(complexDatas);
        marqueeView5.setAnimInAndOut(R.anim.top_in, R.anim.bottom_out);
        marqueeView5.setMarqueeFactory(marqueeFactory5);
        marqueeView5.startFlipping();

        MarqueeFactory<RelativeLayout, ComplexItemEntity> marqueeFactory7 = new ComplexViewMF(this);
        marqueeView7.setAnimInAndOut(R.anim.top_in, R.anim.bottom_out);
        marqueeView7.setMarqueeFactory(marqueeFactory7);
        marqueeView7.startFlipping();
        marqueeFactory7.resetData(complexDatas);

        //当MarqueeView多次更新数据源时，最好使用resetData方法，防止出现叠影
        //以下是为了对比setData，reSetData区别

        //测试重置数据使用resetData方法
        marqueeView2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int delayMillis = (random.nextInt(5) + 4) * 1000;
                marqueeFactory2.resetData(datas);
                marqueeView2.postDelayed(this, delayMillis);
            }
        }, 4000);
        //测试重置数据使用setData方法
        marqueeView6.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int delayMillis = (random.nextInt(5) + 4) * 1000;
                marqueeFactory6.setData(datas);
                marqueeView6.postDelayed(this, delayMillis);
            }
        }, 4000);
    }
}
