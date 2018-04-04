package yanxun.qf.com.life.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import yanxun.qf.com.life.R;
import yanxun.qf.com.life.bean.DiscoveryChannelBean;

/**
 * Created by Administrator on 2017/6/6.
 */

public class DiscoveryChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<DiscoveryChannelBean.DataBean.ChannelBean> list;

    public DiscoveryChannelAdapter(Context context, List<DiscoveryChannelBean.DataBean.ChannelBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                ImageView imageView = new ImageView(context);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 400);
                params.topMargin = 40;
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(params);
                return new FirstViewHolder(imageView);
            case 1:
                View view = inflater.inflate(R.layout.item_tobe_talent, parent, false);
                 return new ToBeTalentViewHolder(view);
            case 2:
            case 3:
                View inflate = inflater.inflate(R.layout.item_discover_channel, parent, false);
               return new ChannelViewHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getCell_type()){
            case "life_sir_fav_list":
                FirstViewHolder mv = (FirstViewHolder) holder;
                Picasso.with(context).load(list.get(position).getLife_sir_fav_list().getCover_image_url()).into(mv.imageView);
                break;
            case "to_be_talent":
                ToBeTalentViewHolder tv = (ToBeTalentViewHolder) holder;
                Picasso.with(context).load(list.get(position).getTo_be_talent().getCover_image_url()).into(tv.iv);
                break;
            case "talent_channel":
                ChannelViewHolder cv = (ChannelViewHolder) holder;
                List<DiscoveryChannelBean.DataBean.ChannelBean.TalentChannelBean> talent_channel = list.get(position).getTalent_channel();
                cv.tv_title.setText("达人专栏");
                cv.tv_all.setText("查看全部");
                cv.tv_all.setTextColor(Color.GRAY);
                cv.tv_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "查看全部", Toast.LENGTH_SHORT).show();
                    }
                });
                cv.rv.setAdapter(new DiscoveryChannelItemAdapter1(context,talent_channel));
                break;
            case "life_channel":
                ChannelViewHolder clv = (ChannelViewHolder) holder;
                List<DiscoveryChannelBean.DataBean.ChannelBean.LifeChannelBean> life_channel = list.get(position).getLife_channel();
                clv.tv_title.setText("LIFE专栏");
                clv.tv_all.setVisibility(View.GONE);
                clv.rv.setAdapter(new DiscoveryChannelItemAdapter2(context,life_channel));
                break;
        }

    }
    class ChannelViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title,tv_all;
        private RecyclerView rv;
        public ChannelViewHolder(View itemView) {
            super(itemView);
            tv_title= (TextView) itemView.findViewById(R.id.tv_title);
            tv_all= (TextView) itemView.findViewById(R.id.tv_all);
            rv= (RecyclerView) itemView.findViewById(R.id.rv);
            GridLayoutManager layoutManager=new GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false);
            rv.setLayoutManager(layoutManager);
            rv.addItemDecoration(new SpacesItemDecoration(20));

        }
    }
    class ToBeTalentViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public ToBeTalentViewHolder(View itemView) {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.iv);
        }
    }
   class FirstViewHolder extends RecyclerView.ViewHolder{
       private ImageView imageView;
       public FirstViewHolder(View itemView) {
           super(itemView);
           imageView = (ImageView) itemView;
       }
   }
    @Override
    public int getItemViewType(int position) {
       switch (list.get(position).getCell_type()){
           case "life_sir_fav_list":
               return  0;
           case "to_be_talent":
               return 1;

           case "talent_channel":
               return 2;

           case "life_channel":
               return 3;
       }
        return super.getItemViewType(position);
    }
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration{
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            outRect.top = space;
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
