package yanxun.qf.com.life.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import yanxun.qf.com.life.R;
import yanxun.qf.com.life.bean.HomeItemBean;
import yanxun.qf.com.life.ui.activity.Home_Banner_Item_Activity;
import yanxun.qf.com.life.ui.activity.Home_FocusProduct_Activity;
import yanxun.qf.com.life.ui.activity.WebView_Activity;

/**
 * Created by Administrator on 2017/6/3.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<HomeItemBean.DataBean.HomeListBean> list;

    public HomeAdapter(Context context, List<HomeItemBean.DataBean.HomeListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("yanxun",viewType+"");
       switch (viewType){
           case 0:{
               View view = inflater.inflate(R.layout.item_banners, parent, false);
               return new BannersViewHolder(view);
           }

           case 1:{
               View view = inflater.inflate(R.layout.item_fav_list, parent, false);
               return new FavListViewHolder(view);
           }

           case 2:{
               View view = inflater.inflate(R.layout.item_focus_product, parent, false);
               return new FocusProductViewHolder(view);
           }

           case 3:{
               View view = inflater.inflate(R.layout.item_post_have_item, parent, false);
               return new PostHaveItemViewHolder(view);
           }

           case 4:{
               View view = inflater.inflate(R.layout.item_post_have_item, parent, false);
               return new PostNoItemViewHolder(view);
           }

       }
       return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        Handler handler;
       switch (list.get(position).getCell_type()){
           case "banners":
               final BannersViewHolder bannersViewHolder = (BannersViewHolder) holder;
               final List<HomeItemBean.DataBean.HomeListBean.BannersBean> banners = list.get(position).getBanners();
               handler = new Handler(){
                   @Override
                   public void handleMessage(Message msg) {
                       if (bannersViewHolder.viewPager.getChildCount() > 0) {

                           Object tag = bannersViewHolder.viewPager.getTag();
                           if (null != tag && (int) tag == position) {
                               int currentItem = bannersViewHolder.viewPager.getCurrentItem();
                               bannersViewHolder.viewPager.setCurrentItem((++currentItem) % banners.size());
                           }
                       }
                       sendEmptyMessageDelayed(0, 4000);
                   }
               };
               LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
               params.setMargins(10,10,10,10);
               Object tag = bannersViewHolder.ll.getTag();
               if(null==tag||(int)tag!=position){
                   for (int i = 0; i < banners.size(); i++) {
                       View view = new View(context);
                       if(i==0){
                           view.setBackgroundResource(R.drawable.shape_tip_selected);
                       }
                       view.setBackgroundResource(R.drawable.shape_tip_normal);
                       bannersViewHolder.ll.addView(view,params);
                   }
                   bannersViewHolder.ll.setTag(position);
               }
                   bannersViewHolder.viewPager.setAdapter(new PagerAdapter() {
                       @Override
                       public int getCount() {
                           return banners.size();
                       }
                       @Override
                       public boolean isViewFromObject(View view, Object object) {
                           return view == object;
                       }

                       @Override
                       public Object instantiateItem(ViewGroup container,final int position) {
                           ImageView iv = new ImageView(context);
                           iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                           Picasso.with(context).load(banners.get(position).getImage_url()).into(iv);
                           iv.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   Intent intent=new Intent(context,Home_Banner_Item_Activity.class);
                                   intent.putExtra("urlId",banners.get(position).getTarget().getId()+"");
                                   context.startActivity(intent);
                               }
                           });
                           container.addView(iv);
                           return iv;
                       }

                       @Override
                       public void destroyItem(ViewGroup container, int position, Object object) {
                          container.removeView((View) object);
                       }
                   });
                   if(null == bannersViewHolder.viewPager.getTag()){
                       bannersViewHolder.viewPager.setTag(position);
                       handler.sendEmptyMessage(0);
                   }
                   bannersViewHolder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                       @Override
                       public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                           for (int i = 0; i < bannersViewHolder.ll.getChildCount(); i++) {
                               View view = bannersViewHolder.ll.getChildAt(i);
                               if(i == position){
                                   view.setBackgroundResource(R.drawable.shape_tip_selected);
                               } else {
                                   view.setBackgroundResource(R.drawable.shape_tip_normal);
                               }
                           }
                       }

                       @Override
                       public void onPageSelected(int position) {

                       }

                       @Override
                       public void onPageScrollStateChanged(int state) {

                       }
                   });
               break;
           case "fav_list":
               FavListViewHolder favListViewHolder = (FavListViewHolder) holder;
               HomeItemBean.DataBean.HomeListBean.FavListBean fav_list = list.get(position).getFav_list();
               favListViewHolder.tv_title.setText(fav_list.getName());
               favListViewHolder.tv_count.setText(fav_list.getItems_count() + "单品");
               HomeItemBean.DataBean.HomeListBean.FavListBean.UserInfoBean user_info = fav_list.getUser_info();
               Picasso.with(context).load(user_info.getAvatar_url()).into(favListViewHolder.iv);

               favListViewHolder.tv_tip.setText(user_info.getNickname());

               LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
               favListViewHolder.rv.setLayoutManager(layoutManager);
               favListViewHolder.rv.setAdapter(new FavListItemAdapter(context,fav_list.getItems_info()));
               break;
           case "focus_product":
               FocusProductViewHolder focusProductViewHolder = (FocusProductViewHolder) holder;
               final HomeItemBean.DataBean.HomeListBean.FocusProductBean focus_product = list.get(position).getFocus_product();
               Picasso.with(context).load(focus_product.getImage_url()).into(focusProductViewHolder.iv);
               focusProductViewHolder.tv_title.setText(focus_product.getTitle());
               focusProductViewHolder.tv_tip.setText(focus_product.getIntroduction());
               focusProductViewHolder.tv_money.setText(focus_product.getSub_title());
               focusProductViewHolder.iv.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if(""!=focus_product.getRaw_url()){
                           try{
                               Intent intent = new Intent();
                               intent.setAction("Android.intent.action.VIEW");
                               Uri parse = Uri.parse(focus_product.getRaw_url());
                               intent.setData(parse);
                               intent.setClassName("com.taobao.taoba", "com.taobao.tao.detail.activity.DetailActivity");
                               context.startActivity(intent);
                           }catch (Exception e){
                               Intent intent=new Intent(context, WebView_Activity.class);
                               intent.putExtra("url",focus_product.getRaw_url());
                               intent.putExtra("title","商品详情");
                               context.startActivity(intent);
                           }
                       }else {
                           Intent intent=new Intent(context,Home_FocusProduct_Activity.class);
                           intent.putExtra("itemId",focus_product.getTarget_url());
                           intent.putExtra("title","商品详情");
                           context.startActivity(intent);
                       }
                   }
               });
               break;
           case "post_have_item":
               PostHaveItemViewHolder postHaveItemViewHolder = (PostHaveItemViewHolder) holder;
               HomeItemBean.DataBean.HomeListBean.PostBean post = list.get(position).getPost();
               Picasso.with(context).load(post.getNew_cover_image_url()).into(postHaveItemViewHolder.iv_max);
               Picasso.with(context).load(post.getChannel_icon()).into(postHaveItemViewHolder.iv_logo);

               postHaveItemViewHolder.tv_title.setText(post.getTitle());
               postHaveItemViewHolder.tv_type.setText("[ " + post.getChannel_title() + " ]");
               postHaveItemViewHolder.tv_count.setText(post.getLikes_count() + "");
               List<HomeItemBean.DataBean.HomeListBean.PostBean.PostItemsBean> post_items = post.getPost_items();
               LinearLayoutManager  Manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
               postHaveItemViewHolder.sub_rv.setLayoutManager(Manager);
               postHaveItemViewHolder.sub_rv.setAdapter(new PostItemAdapter(context,post_items));
           break;
           case "post_no_item":
               PostNoItemViewHolder postNoItemViewHolder = (PostNoItemViewHolder) holder;
               HomeItemBean.DataBean.HomeListBean.PostBean post1 = list.get(position).getPost();
               Picasso.with(context).load(post1.getNew_cover_image_url()).into(postNoItemViewHolder.iv_max);
               Picasso.with(context).load(post1.getChannel_icon()).into(postNoItemViewHolder.iv_logo);

               postNoItemViewHolder.tv_title.setText(post1.getTitle());
               postNoItemViewHolder.tv_type.setText("[ " + post1.getChannel_title() + " ]");
               postNoItemViewHolder.tv_count.setText(post1.getLikes_count() + "");
               break;
       }


    }
    class BannersViewHolder extends RecyclerView.ViewHolder {
        private ViewPager viewPager;
        private LinearLayout ll;

        public BannersViewHolder(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewPager);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
        }
    }

    class FavListViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_count, tv_tip;
        private ImageView iv;
        private RecyclerView rv;

        public FavListViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            tv_tip = (TextView) itemView.findViewById(R.id.tv_tip);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            rv = (RecyclerView) itemView.findViewById(R.id.sub_rv);
        }
    }

    class FocusProductViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_tip, tv_money;
        private ImageView iv;
        public FocusProductViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_tip = (TextView) itemView.findViewById(R.id.tv_tip);
            tv_money = (TextView) itemView.findViewById(R.id.tv_money);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

    class PostHaveItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_max, iv_logo;
        private TextView tv_title, tv_type, tv_count;
        private RecyclerView sub_rv;

        public PostHaveItemViewHolder(View itemView) {
            super(itemView);
            iv_max = (ImageView) itemView.findViewById(R.id.iv_max);
            iv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            sub_rv = (RecyclerView) itemView.findViewById(R.id.sub_rv);
            sub_rv.setVisibility(View.VISIBLE);
        }
    }

    class PostNoItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_max, iv_logo, iv_bg;
        private TextView tv_title, tv_type, tv_count;

        public PostNoItemViewHolder(View itemView) {
            super(itemView);
            iv_max = (ImageView) itemView.findViewById(R.id.iv_max);
            iv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            iv_bg = (ImageView) itemView.findViewById(R.id.iv_bg);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            iv_bg.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("yanxun",list.get(position).getCell_type());
        switch (list.get(position).getCell_type()){
            case "banners": {
                return 0;
            }
            case "fav_list": {
                return 1;
            }
            case "focus_product": {
                return 2;
            }
            case "post_have_item": {
                return 3;
            }
            case "post_no_item": {
                return 4;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
