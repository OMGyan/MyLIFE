package yanxun.qf.com.life.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import yanxun.qf.com.life.R;

/**
 * Created by Administrator on 2017/6/3.
 */

public class Home_FocusProduct_Activity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_focusproduct);
        String title = getIntent().getStringExtra("title");
        Toast.makeText(this, getIntent().getStringExtra("itemId")+title, Toast.LENGTH_SHORT).show();

    }
}
