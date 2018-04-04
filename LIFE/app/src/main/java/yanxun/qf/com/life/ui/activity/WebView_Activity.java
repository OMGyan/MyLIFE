package yanxun.qf.com.life.ui.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import yanxun.qf.com.life.R;

/**
 * Created by Administrator on 2017/6/3.
 */
//webview工具类activity
public class WebView_Activity extends AppCompatActivity{

    private TextView tv_title,tv_back;
    private SwipeRefreshLayout swipeRefreshLayout;
    private WebView  webView;
    private ProgressDialog dialog;
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    webView.reload();
                    break;
                case 1:
                    swipeRefreshLayout.setRefreshing(false);
                    if(dialog!=null){
                        dialog.dismiss();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 沉浸 状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_strategy);
        initViews();
        initDialog();
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        tv_title.setText(title);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            //            重定向回调
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if ( url.startsWith("https")||url.contains("taobao://")) {
                    return false;
                }
                webView.loadUrl(url);
                return true;
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                swipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mhandler.sendEmptyMessage(1);
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode==KeyEvent.KEYCODE_BACK)&&webView.canGoBack()){
            webView.goBack();
            return true;
        }
        if(dialog!=null){
            dialog.dismiss();
        }
        return super.onKeyDown(keyCode, event);
    }



    private void initDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在加载....");
        dialog.setCancelable(false);
        dialog.show();
    }

    private void initViews() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        webView = (WebView) findViewById(R.id.webView);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.myYellow));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mhandler.sendEmptyMessage(0);
            }
        });
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
