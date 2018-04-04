package yanxun.qf.com.life.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yanxun.qf.com.life.R;

/**
 * Created by Administrator on 2017/6/3.
 */

public class Main_DiscoveryFragment extends Fragment{
   /* //声明toolbar视图
    private View toolbarview;*/
    //声明根布局
    private View rootView;
    //声明适配器
    private FragmentStatePagerAdapter adapter;
    //声明fragment集合
    private List<Fragment> fragments = new ArrayList<>();
    private RelativeLayout discovery_toolbar;
    private RadioGroup rg;
    private RadioButton rb_column;
    private RadioButton rb_single;
    private View scollTip;
    private ViewPager viewPager;
    private int widthPixels;

    @Nullable
    @Override
    //创建视图
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      /*  if(toolbarview==null){
            //创建toolbar视图
            toolbarview = inflater.inflate(R.layout.toolbar_discovery,container,false);
        }*/
        if(rootView==null){
            rootView = inflater.inflate(R.layout.fragment_main_discovery,container,false);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，
        // 要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if(parent!=null){
            parent.removeView(rootView);
        }
        return rootView;
    }
    //视图创建完全后

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(adapter==null){
            //找到控件
            initViews(view);
            //初始化数据
            initData();
            //初始化适配器
            initAdapter();
            //设置适配器
            viewPager.setAdapter(adapter);
            //radiobutton改变状态逻辑
            setRgCheckChanged();
            //viewpager改变状态逻辑
            setViewpagerChanged();
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    private void setViewpagerChanged() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 switch (position){
                     case 0:
                         rb_column.setChecked(true);
                         setOneTipMargin();
                         break;
                     case 1:
                         rb_single.setChecked(true);
                         setTwoTipMargin();
                         break;
                 }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setRgCheckChanged() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                 switch (i){
                     case R.id.rb_column:
                         viewPager.setCurrentItem(0);
                         setOneTipMargin();
                         break;
                     case R.id.rb_single:
                         viewPager.setCurrentItem(1);
                         setTwoTipMargin();
                         break;
                 }
                 /*int position = viewPager.getCurrentItem();
                setTipMargin(position);*/
            }
        });
    }
    private void setOneTipMargin(){
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) scollTip.getLayoutParams();
        lp.leftMargin=widthPixels/8;
        scollTip.setLayoutParams(lp);
    }
    private void setTwoTipMargin(){
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) scollTip.getLayoutParams();
        lp.leftMargin=widthPixels/8+widthPixels/2;
        scollTip.setLayoutParams(lp);
    }
  /*  private void setTipMargin(int position) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) scollTip.getLayoutParams();
       // lp.leftMargin=widthPixels/8+(widthPixels/8)*(position*2)+position*(widthPixels/4);
        lp.leftMargin=widthPixels/8;
        scollTip.setLayoutParams(lp);
    }*/

    private void initAdapter() {
        adapter = new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
    }

    private void initData() {
        fragments.add(new Discovery_Fragment_Item_Channel());
        fragments.add(new Discovery_Fragment_Item_Category());
    }

    private void initViews(View view) {
        discovery_toolbar = (RelativeLayout)view.findViewById(R.id.discovery_toolBar);
        rg = (RadioGroup)view.findViewById(R.id.rg);
        rb_column = (RadioButton)view.findViewById(R.id.rb_column);
        rb_single = (RadioButton)view.findViewById(R.id.rb_single);
        scollTip = view.findViewById(R.id.scrollTip);
        viewPager = ((ViewPager) view.findViewById(R.id.viewPager));
        //获取屏幕宽度
        widthPixels = getResources().getDisplayMetrics().widthPixels;
        /**
         * public LayoutParams(int width, int height)
         */
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(widthPixels / 4, 5);
        lp.leftMargin=widthPixels/8;
        scollTip.setLayoutParams(lp);
        discovery_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"搜索中。。。", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
