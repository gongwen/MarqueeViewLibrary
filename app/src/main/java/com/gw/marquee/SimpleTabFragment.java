package com.gw.marquee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GongWen on 17/9/6.
 */

public class SimpleTabFragment extends Fragment {
    private static final String TAG = "SimpleTabFragment";
    private final List<String> datas = Arrays.asList("《赋得古原草送别》", "离离原上草，一岁一枯荣。", "野火烧不尽，春风吹又生。", "远芳侵古道，晴翠接荒城。", "又送王孙去，萋萋满别情。");

    private SimpleMarqueeView marqueeView1, marqueeView2, marqueeView3;
    private ImageView yellowSpeaker;

    public static SimpleTabFragment newInstance() {
        Bundle bundle = new Bundle();
        SimpleTabFragment fragment = new SimpleTabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_simple_tab, null, false);
        marqueeView1 = (SimpleMarqueeView) mView.findViewById(R.id.marqueeView1);
        marqueeView2 = (SimpleMarqueeView) mView.findViewById(R.id.marqueeView2);
        marqueeView3 = (SimpleMarqueeView) mView.findViewById(R.id.marqueeView3);
        yellowSpeaker = (ImageView) mView.findViewById(R.id.yellowSpeaker);
        DrawableCompat.setTint(DrawableCompat.wrap(yellowSpeaker.getDrawable().mutate()), getResources().getColor(R.color.yellow));
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SimpleMF<String> marqueeFactory = new SimpleMF(getActivity());
        marqueeFactory.setData(datas);
        marqueeView1.setMarqueeFactory(marqueeFactory);
        marqueeView1.startFlipping();
        marqueeFactory.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(getActivity(), holder.data, Toast.LENGTH_SHORT).show();
            }
        });

        SimpleMF<String> marqueeFactory2 = new SimpleMF(getActivity());
        marqueeFactory2.setData(datas);
        marqueeView2.setMarqueeFactory(marqueeFactory2);
        marqueeView2.startFlipping();
        marqueeFactory2.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(getActivity(), holder.data, Toast.LENGTH_SHORT).show();
            }
        });

        SimpleMF<Spanned> marqueeFactory3 = new SimpleMF<>(getActivity());
        List<Spanned> datas3 = new ArrayList<>();
        datas3.add(Html.fromHtml("<font color=\"#ff0000\">《赋得古原草送别》</font>"));
        datas3.add(Html.fromHtml("<font color=\"#00ff00\">离离原上草，</font>一岁一枯荣。"));
        datas3.add(Html.fromHtml("野火烧不尽，<font color=\"#0000ff\">春风吹又生。</font>"));
        datas3.add(Html.fromHtml("<font color=\"#333333\">远芳侵古道，晴翠接荒城。</font>"));
        datas3.add(Html.fromHtml("<font color=\"#ffffff\">又送王孙去，萋萋满别情。</font>"));
        marqueeFactory3.setData(datas3);
        marqueeView3.setMarqueeFactory(marqueeFactory3);
        marqueeView3.startFlipping();
        marqueeFactory3.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, Spanned>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, Spanned> holder) {
                Toast.makeText(getActivity(), holder.data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeView1.startFlipping();
        marqueeView2.startFlipping();
        marqueeView3.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView1.stopFlipping();
        marqueeView2.stopFlipping();
        marqueeView3.stopFlipping();
    }
}
