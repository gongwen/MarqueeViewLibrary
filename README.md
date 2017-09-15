# MarqueeViewLibrary
跑马灯View，支持自定义跑马灯ItemView。

## 效果图
<img src="/screenshot/screen_shot.gif"/>

## 使用

### Gradle:
compile 'com.gongwen:marqueelibrary:1.1.1'

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

SimpleMarqueeView属性(MarqueeView所有属性及以下属性)

| Attribute 属性          | Description 描述 |
|:---				     |:---|
| smvTextSize         |    文字大小       |
| smvTextColor         | 文字颜色            |
| smvTextGravity         |  文字位置          |

### 用法一：使用SimpleMarqueeView和SimpleMF

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
    app:smvTextGravity="center_vertical"
    app:smvTextSize="15sp"></com.gongwen.marqueen.SimpleMarqueeView>
```

#### 设置数据
```
SimpleMarqueeView marqueeView = (SimpleMarqueeView) findViewById(R.id.marqueeView);
final List<String> datas = Arrays.asList("《赋得古原草送别》", "离离原上草，一岁一枯荣。", "野火烧不尽，春风吹又生。", "远芳侵古道，晴翠接荒城。", "又送王孙去，萋萋满别情。");
SimpleMF<String> marqueeFactory = new SimpleMF(this);
marqueeFactory.setData(datas);
marqueeView.setMarqueeFactory(marqueeFactory);
marqueeView.startFlipping();
```

#### 设置事件监听
```
marqueeFactory.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
    @Override
    public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
        Toast.makeText(MainActivity.this, holder.data, Toast.LENGTH_SHORT).show();
    }
});
```

### 用法二：自定义MarqueeFactory来设置不同类型ItemView

#### XML
```
<com.gongwen.marqueen.MarqueeView
    android:id="@+id/marqueeView4"
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
#### 设置数据和事件监听用法同上

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