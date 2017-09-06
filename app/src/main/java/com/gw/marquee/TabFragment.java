package com.gw.marquee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.MarqueeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by GongWen on 17/9/6.
 */

public class TabFragment extends Fragment {
    private static final String TAG = "TabFragment";
    private final List<String> datas = Arrays.asList("《赋得古原草送别》", "离离原上草，一岁一枯荣。", "野火烧不尽，春风吹又生。", "远芳侵古道，晴翠接荒城。", "又送王孙去，萋萋满别情。");

    private MarqueeView marqueeView1, marqueeView2, marqueeView3, marqueeView4, marqueeView5;

    private WeakHandler mHandler = new WeakHandler();

    public static TabFragment newInstance() {
        Bundle bundle = new Bundle();
        TabFragment fragment = new TabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_tab, null, false);
        marqueeView1 = (MarqueeView) mView.findViewById(R.id.marqueeView1);
        marqueeView2 = (MarqueeView) mView.findViewById(R.id.marqueeView2);
        marqueeView3 = (MarqueeView) mView.findViewById(R.id.marqueeView3);
        marqueeView4 = (MarqueeView) mView.findViewById(R.id.marqueeView4);
        marqueeView5 = (MarqueeView) mView.findViewById(R.id.marqueeView5);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initMarqueeView1();
        initMarqueeView2();
        initMarqueeView3();
        initMarqueeView4();
        initMarqueeView5();
    }

    private void initMarqueeView1() {
        List<ComplexItemEntity> complexDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            complexDatas.add(new ComplexItemEntity("标题 " + i, "副标题 " + i, "时间 " + i));
        }
        MarqueeFactory<RelativeLayout, ComplexItemEntity> marqueeFactory = new ComplexViewMF(getActivity());
        marqueeFactory.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<RelativeLayout, ComplexItemEntity>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<RelativeLayout, ComplexItemEntity> holder) {
                Toast.makeText(getActivity(), holder.data.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory.setData(complexDatas);
        marqueeView1.setInAndOutAnim(R.anim.in_top, R.anim.out_bottom);
        marqueeView1.setMarqueeFactory(marqueeFactory);
        marqueeView1.startFlipping();
    }

    private void initMarqueeView2() {
        MarqueeFactory<TextView, String> marqueeFactory = new NoticeMF(getActivity());
        marqueeFactory.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(getActivity(), holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory.setData(datas);
        marqueeView2.setInAndOutAnim(R.anim.in_top, R.anim.out_bottom);
        marqueeView2.setMarqueeFactory(marqueeFactory);
        marqueeView2.startFlipping();
    }

    private void initMarqueeView3() {
        MarqueeFactory<TextView, String> marqueeFactory = new NoticeMF(getActivity());
        marqueeView3.setMarqueeFactory(marqueeFactory);
        marqueeView3.startFlipping();
        marqueeFactory.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(getActivity(), holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory.setData(datas);
    }

    private void initMarqueeView4() {
        MarqueeFactory<TextView, String> marqueeFactory = new NoticeMF(getActivity());
        marqueeFactory.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(getActivity(), holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory.setData(datas);
        marqueeView4.setMarqueeFactory(marqueeFactory);
        marqueeView4.setInAndOutAnim(R.anim.in_left, R.anim.out_right);
        marqueeView4.setAnimDuration(2000);
        marqueeView4.setFlipInterval(2500);
        marqueeView4.setAnimateFirstView(false);
        marqueeView4.startFlipping();
    }

    private void initMarqueeView5() {
        final MarqueeFactory<TextView, String> marqueeFactory = new NoticeMF(getActivity());
        marqueeFactory.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(getActivity(), holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory.setData(datas);
        marqueeView5.setMarqueeFactory(marqueeFactory);
        marqueeView5.startFlipping();

        //测试重置数据效果
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int delayMillis = (random.nextInt(5) + 4) * 1000;
                marqueeFactory.setData(datas);
                mHandler.postDelayed(this, delayMillis);
            }
        }, 8000);
    }


    @Override
    public void onStart() {
        super.onStart();
        marqueeView1.startFlipping();
        marqueeView2.startFlipping();
        marqueeView3.startFlipping();
        marqueeView4.startFlipping();
        marqueeView5.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView1.stopFlipping();
        marqueeView2.stopFlipping();
        marqueeView3.stopFlipping();
        marqueeView4.stopFlipping();
        marqueeView5.stopFlipping();
    }
}
