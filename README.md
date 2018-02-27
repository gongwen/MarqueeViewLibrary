# MarqueeViewLibrary
一个很方便使用和扩展的跑马灯Library，通过提供不同的MarqueeFactory来定制不同的跑马灯View，
并且提供了常用类型的跑马灯效果：SimpleMarqueeView

效果图
---

<img src="/screenshot/screen_shot.gif"/>

示例Apk下载
---
[示例Apk下载](https://github.com/gongwen/MarqueeViewLibrary/raw/master/sample-apk/app-debug-1.1.3.apk)

## 使用

### Gradle:
implementation 'com.gongwen:marqueelibrary:1.1.3'

### 属性
MarqueeView属性

| Attribute 属性          | Description 描述 |
|:---				     |:---|
| flipInterval         |    翻页时间间隔       |
| marqueeAnimDuration         | 动画执行时间            |
| inAnimation         |  marquee in动画          |
| outAnimation         | marquee out动画          |
| autoStart         | 动画是否自动开始         |
| animateFirstView         | 当前ChildView第一次是否动画展示         |

SimpleMarqueeView属性(支持MarqueeView所有属性及以下属性)

| Attribute 属性          | Description 描述 |
|:---				     |:---|
| smvTextSize         |    文字大小       |
| smvTextColor         | 文字颜色            |
| smvTextGravity         |  文字位置          |
| smvTextSingleLine | 文字是否单行显示 |
| smvTextEllipsize | 文字显示不下时，系统的处理方式(可选：none，start，middle，end) |

### 常见用法：使用SimpleMarqueeView和SimpleMF

#### XML
```
<com.gongwen.marqueen.SimpleMarqueeView
    android:id="@+id/simpleMarqueeView"
    android:layout_width="match_parent"
    android:layout_height="40dp"
    android:flipInterval="2500"
    android:inAnimation="@anim/in_right"
    android:outAnimation="@anim/out_left"
    app:marqueeAnimDuration="2000"
    app:smvTextColor="@color/white"
    app:smvTextEllipsize="end"
    app:smvTextGravity="center_vertical"
    app:smvTextSingleLine="true"
    app:smvTextSize="15sp" />
```

#### 设置数据
```
final List<String> datas = Arrays.asList("《赋得古原草送别》", "离离原上草，一岁一枯荣。", "野火烧不尽，春风吹又生。", "远芳侵古道，晴翠接荒城。", "又送王孙去，萋萋满别情。");
//SimpleMarqueeView<T>，SimpleMF<T>：泛型T指定其填充的数据类型，比如String，Spanned等
SimpleMarqueeView<String> marqueeView = (SimpleMarqueeView) findViewById(R.id.marqueeView);
SimpleMF<String> marqueeFactory = new SimpleMF(this);
marqueeFactory.setData(datas);
marqueeView.setMarqueeFactory(marqueeFactory);
marqueeView.startFlipping();
```

#### 设置监听事件
```
marqueeView.setOnItemClickListener(new OnItemClickListener<TextView, String>() {
    @Override
    public void onItemClickListener(TextView mView, String mData, int mPosition) {
        /**
        * 注意：
        * 当MarqueeView有子View时，mView：当前显示的子View，mData：mView所填充的数据，mPosition：mView的索引
        * 当MarqueeView无子View时，mView：null，mData：null，mPosition：－1
        */
        Toast.makeText(MainActivity.this, String.format("mPosition:%s,mData:%s,mView:%s,.", mPosition, mData, mView), Toast.LENGTH_SHORT).show();
    }
});
```

### 扩展用法：自定义MarqueeFactory来定制任意类型ItemView

#### XML
```
<com.gongwen.marqueen.MarqueeView
    android:id="@+id/marqueeView"
    android:layout_width="match_parent"
    android:layout_height="40dp"
    android:flipInterval="2500"
    android:inAnimation="@anim/in_right"
    android:outAnimation="@anim/out_left"
    app:marqueeAnimDuration="2000"></com.gongwen.marqueen.MarqueeView>

```

#### 自定义MarqueeFactory
继承自MarqueeFactory，通过泛型指定ItemView类型以及ItemData类型，之后实现generateMarqueeItemView方法，提供ItemView，并为ItemView设置数据即可。
##### 例如：
```
//MarqueeFactory<T extends View, E>
//泛型T:指定ItemView的类型
//泛型E:指定ItemView填充的数据类型
public class ComplexViewMF extends MarqueeFactory<RelativeLayout, ComplexItemEntity> {
    private LayoutInflater inflater;

    public ComplexViewMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RelativeLayout generateMarqueeItemView(ComplexItemEntity data) {
        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.complex_view, null);
        ((TextView) mView.findViewById(R.id.title)).setText(data.getTitle());
        ((TextView) mView.findViewById(R.id.secondTitle)).setText(data.getSecondTitle());
        ((TextView) mView.findViewById(R.id.time)).setText(data.getTime());
        return mView;
    }
}
```
#### 设置数据和监听事件用法同上

#### 重影问题可参考以下解决方案(参考自[这里](https://github.com/sfsheng0322/MarqueeView))

<pre>
@Override
public void onStart() {
    super.onStart();
    marqueeView.startFlipping();
}

@Override
public void onStop() {
    super.onStop();
    marqueeView.stopFlipping();
}
</pre>
License
--
    Copyright (C) 2016 1798550470@qq.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
