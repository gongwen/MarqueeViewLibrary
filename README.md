# MarqueeViewDemo
通过MarqueeFactory来提供各种样式的跑马灯View，
支持自定义跑马灯ItemView
### 效果图
<img src="/screenshot/screenshot.gif">

### 使用
#### Gradle:
compile 'com.gongwen:marqueelibrary:1.0.0'
#### 通过自定义MarqueeFactory来设置ItemView
继承自MarqueeFactory，通过泛型指定ItemView类型以及ItemData类型，之后实现generateMarqueeItemView方法，提供ItemView，并为ItemView设置数据即可。
##### 例如：
<pre>
public class NoticeMF extends MarqueeFactory<TextView, String> {
    private LayoutInflater inflater;

    public NoticeMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TextView generateMarqueeItemView(String data) {
        TextView mView = (TextView) inflater.inflate(R.layout.notice_item, null);
        mView.setText(data);
        return mView;
    }
}
</pre>

#### 设置列表数据
<pre>
MarqueeFactory<TextView, String> marqueeFactory2 = new NoticeMF(this);
marqueeFactory2.setData(datas);
</pre>
#### 设置事件监听
<pre>
marqueeFactory2.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(MainActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
});
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