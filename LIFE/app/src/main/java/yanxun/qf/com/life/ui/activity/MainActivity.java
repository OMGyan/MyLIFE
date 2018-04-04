package yanxun.qf.com.life.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yanxun.qf.com.life.R;
import yanxun.qf.com.life.ui.fragment.Main_DiscoveryFragment;
import yanxun.qf.com.life.ui.fragment.Main_HomeFragment;
import yanxun.qf.com.life.ui.fragment.Main_PersonFragment;
//yanxun first time use git version control 111
public class MainActivity extends AppCompatActivity {
    //yanxun
    private int[] selector = {R.drawable.tab_item_home_selector,R.drawable.tab_item_discovery_selector,R.drawable.tab_item_personal_selector};
    private LayoutInflater inflater;
    private List<Class> fragments = new ArrayList<>();
    private FragmentTabHost tabHost;
    private boolean isExit;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(this);
        //初始化数据
        initData();
        //初始化FragmentTabHost
        initTabHost();
    }

    private void initTabHost() {
        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        tabHost.getTabWidget().setDividerDrawable(null);

        for(int i = 0;i < fragments.size();i++){
            TabHost.TabSpec spec = tabHost.newTabSpec("晏讯" + i).setIndicator(getItemView(i));
            tabHost.addTab(spec,fragments.get(i),null);
        }
    }

    private View getItemView(int a) {
        View view = inflater.inflate(R.layout.tab_item_view, null);
       ImageView iv = (ImageView) view.findViewById(R.id.iv_tabitem);
        iv.setImageResource(selector[a]);
        return  view;
    }

    private void initData() {
        fragments.add(Main_HomeFragment.class);
        fragments.add(Main_DiscoveryFragment.class);
        fragments.add(Main_PersonFragment.class);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
    public void exit(){
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出[ L I F E ]", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }

}
