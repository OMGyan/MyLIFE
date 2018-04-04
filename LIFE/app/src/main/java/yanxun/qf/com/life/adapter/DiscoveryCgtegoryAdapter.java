package yanxun.qf.com.life.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ismaeltoe.FlowLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import yanxun.qf.com.life.R;
import yanxun.qf.com.life.bean.DiscoveryCategoryBean;

/**
 * Created by Administrator on 2017/6/6.
 */

public class DiscoveryCgtegoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //声明上下文
    private Context context;
    private LayoutInflater inflater;
    //声明数据
    private List<DiscoveryCategoryBean.DataBean.CategoriesBean> list;

    public DiscoveryCgtegoryAdapter(Context context, List<DiscoveryCategoryBean.DataBean.CategoriesBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_discovery_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder mholder = (MyViewHolder) holder;
        Picasso.with(context).load(list.get(position).getIcon_url()).into(mholder.iv);
        List<DiscoveryCategoryBean.DataBean.CategoriesBean.SubcategoriesBean> subcategories = list.get(position).getSubcategories();
        Object tag = mholder.fl.getTag();
        if(tag==null|| ((int) tag)!=position){
            mholder.fl.removeAllViews();
            for (int i = 0; i < subcategories.size(); i++) {
                TextView textView = new TextView(context);
                textView.setClickable(true);
                textView.setPadding(20,20,20,20);
                textView.setTextColor(Color.BLACK);
                textView.setText(subcategories.get(i).getName());
                mholder.fl.addView(textView);
            }
            mholder.fl.setTag(position);
        }
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private FlowLayout fl;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            fl = ((FlowLayout) itemView.findViewById(R.id.flowLayout));
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
