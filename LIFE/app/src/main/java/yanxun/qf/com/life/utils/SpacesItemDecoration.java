package yanxun.qf.com.life.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/6/3.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration{

    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
       outRect.left = 0;
        outRect.bottom = 0;
        outRect.right = 0;
        outRect.top = space;

        if(parent.getChildPosition(view)==0){
            outRect.top = 0;
        }
    }
}
