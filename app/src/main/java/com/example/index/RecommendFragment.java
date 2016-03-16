package com.example.index;

/**
 * 首页 --- 推荐Fragment
 */
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.mengyin.R;
import com.example.utils.MySpaceItemWidth;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment {

    private View view;
    private RecyclerView index_recommend_mm_rv,index_recommend_zt_rv,index_recommend_yc_rv,index_recommend_rq_rv,index_recommend_nr_rv,index_recommend_hd_rv;
    private ViewPager index_recommend_viewPager;
    private ScheduledExecutorService scheduledExecutorService;
    private int imageIds[];               // 图片资源数组
    private ArrayList<ImageView> images;  // 图片集合
    private ArrayList<View> dots;         // 视图集合
    private ViewPagerAdapter adapter;
    private int oldPosition = 0;          // 记录上一次点的位置
    private int currentItem;              // 当前页面

    //播放图片的Handler
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // 设置当前页面
            index_recommend_viewPager.setCurrentItem(currentItem);
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommend, container, false);

        initView();

        viewControl();

        return view;
    }

    //view控制逻辑
    private void viewControl() {

        playPicture(view);//图片轮播

        //设置RecycleView水平显示
        setRecycleViewShow(index_recommend_mm_rv);
        setRecycleViewShow(index_recommend_zt_rv);
        setRecycleViewShow(index_recommend_yc_rv);
        setRecycleViewShow(index_recommend_rq_rv);
        setRecycleViewShow(index_recommend_nr_rv);
        setRecycleViewShow(index_recommend_hd_rv);

        index_recommend_mm_rv.setAdapter(new MyIndexRecycleViewAdapter(getActivity(),1));
        index_recommend_zt_rv.setAdapter(new MyIndexRecycleViewAdapter(getActivity(),2));
        index_recommend_yc_rv.setAdapter(new MyIndexRecycleViewAdapter(getActivity(),1));
        index_recommend_rq_rv.setAdapter(new MyIndexRecycleViewAdapter(getActivity(),1));
        index_recommend_nr_rv.setAdapter(new MyIndexRecycleViewAdapter(getActivity(),1));
        index_recommend_hd_rv.setAdapter(new MyIndexRecycleViewAdapter(getActivity(),1));
    }

    private void setRecycleViewShow(RecyclerView recycleView) {
        //设置RecycleView水平显示
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleView.setLayoutManager(layoutManager);
        //设置item的间距
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        recycleView.addItemDecoration(new MySpaceItemWidth(spacingInPixels));
    }

    //初始化控件
    private void initView() {
        index_recommend_viewPager = (ViewPager) view.findViewById(R.id.index_recommend_viewPager);
        index_recommend_mm_rv = (RecyclerView) view.findViewById(R.id.index_recommend_mm_rv);
        index_recommend_zt_rv = (RecyclerView) view.findViewById(R.id.index_recommend_zt_rv);
        index_recommend_yc_rv = (RecyclerView) view.findViewById(R.id.index_recommend_yc_rv);
        index_recommend_rq_rv = (RecyclerView) view.findViewById(R.id.index_recommend_rq_rv);
        index_recommend_nr_rv = (RecyclerView) view.findViewById(R.id.index_recommend_nr_rv);
        index_recommend_hd_rv = (RecyclerView) view.findViewById(R.id.index_recommend_hd_rv);
    }

    /**
     * 播放图片
     */
    private void playPicture(View view) {// 播放图片
        // 图片ID
        imageIds = new int[] { R.mipmap.ic_launcher,R.mipmap.tab_musicman_pressed,R.mipmap.tab_homepage_normal};

        // 显示的图片
        images = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);

            images.add(imageView);
        }

        // 显示的点
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.dot_0));
        dots.add(view.findViewById(R.id.dot_1));
        dots.add(view.findViewById(R.id.dot_2));

        adapter = new ViewPagerAdapter();
        index_recommend_viewPager.setAdapter(adapter);

        index_recommend_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // 页面被选中调用该方法
            @Override
            public void onPageSelected(int position) {
                dots.get(oldPosition).setBackgroundResource(
                        R.drawable.do_normal);
                dots.get(position).setBackgroundResource(R.drawable.do_focused);

                oldPosition = position;
                currentItem = position;
            }

            // 页面滚动调用该方法
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            // 页面滚动状态改变调用该方法
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    //图片轮播ViewPager适配器
    private class ViewPagerAdapter extends PagerAdapter {

        // 加载到适配器中的pager的个数
        @Override
        public int getCount() {
            return images.size();
        }

        // 是否是同一张图片,判断当前的view与instantiateItem的返回值是否一致
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        //销毁当前ViewPager项
        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(images.get(position));

        }

        // 创建新的pager页面
        // view:容器，指ViewPager
        // position:当前页的索引
        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            view.addView(images.get(position));

            //实现页面跳转
//            final Intent intent = new Intent(getActivity(),TitleInfoActivity.class);
//            switch (position){
//                case 0:
//                    images.get(0).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            intent.putExtra("url","http://mp.weixin.qq.com/s?__biz=MzAxNjYzOTIwNg==&mid=213937622&idx=1&sn=9fcd7fde450cb81d4f1e1d7f126549a2");
//                            getActivity().startActivity(intent);
//                        }
//                    });
//                    break;
//                case 1:
//                    images.get(1).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                        }
//                    });
//                    break;
//            }

            return images.get(position);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // 每隔5秒钟切换一张图片
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2,
                5, TimeUnit.SECONDS);
    }

    // 切换图片
    private class ViewPagerTask implements Runnable {

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            // 更新界面
            handler.obtainMessage().sendToTarget();
        }

    }
}
