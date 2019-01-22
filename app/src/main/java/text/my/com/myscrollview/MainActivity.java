package text.my.com.myscrollview;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView myRecycler;
    View myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecycler = findViewById(R.id.myRecycle);
        myView = findViewById(R.id.myshai);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecycler.setLayoutManager(linearLayoutManager);
        myRecycler.setAdapter(new MyAdapter());

        //监听RecycleView滑动的距离
        myRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //去判断当前可见的是否是1如果是的话
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                    int lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();

                    int firstCom = linearLayoutManager.findFirstCompletelyVisibleItemPosition();;

                    Log.e("TAG", "firstItemPosition" + firstItemPosition + "======" +firstCom);
                    if (firstCom > 1) {
                        myView.setVisibility(View.VISIBLE);
                    } else {
                        myView.setVisibility(View.GONE);
                    }


                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();//获取LayoutManager


                //经过测试LinearLayoutManager和GridLayoutManager有以下的方法,这里只针对LinearLayoutManager
                if (manager instanceof LinearLayoutManager) {
                    //经测试第一个完整的可见的item位置，若为0则是最上方那个;在item超过屏幕高度的时候只有第一个item出现的时候为0 ，其他时候会是一个负的值
                    int findFirstCompletelyVisibleItemPosition = ((LinearLayoutManager) manager).findFirstCompletelyVisibleItemPosition();
                    //最后一个完整的可见的item位置
                    int findLastCompletelyVisibleItemPosition = ((LinearLayoutManager) manager).findLastCompletelyVisibleItemPosition();
                    //第一个可见的位置
                    int findFirstVisibleItemPosition = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
                    //最后一个可见的位置
                    int findLastVisibleItemPosition = ((LinearLayoutManager) manager).findLastVisibleItemPosition();

                    int topPosition = (recyclerView == null || recyclerView.getChildCount() == 1) ? 0 : recyclerView.getChildAt(1).getTop();

                    int top = recyclerView.getChildAt(1).getTop();
                    int childCount = recyclerView.getChildCount();

//                    if (topPosition<=10){
//                        myView.setVisibility(View.GONE);
//                    }else {
//                        myView.setVisibility(View.GONE);
//                    }
                    Log.e("TAG", "findFirstVisibleItemPosition"+findFirstVisibleItemPosition +"++++++++++"+top+"---"+childCount);

//                    if (findFirstCompletelyVisibleItemPosition >=1) {
//                        myView.setVisibility(View.VISIBLE);
//                    } else {
//                        myView.setVisibility(View.GONE);
//                    }
//



                }
            }
        });


    }

    class MyAdapter extends RecyclerView.Adapter {
        int Banner = 1;
        int Notice = 2;
        int Content = 3;
        //创建不同的ViewHolder返回布局类型
        View view;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {//此处拿到类判断
            if (i == Banner) {
                view = View.inflate(viewGroup.getContext(), R.layout.banner_item, null);
                return new myViewHolder(view);
            } else if (i == Notice) {
                view = View.inflate(viewGroup.getContext(), R.layout.shailayout, null);
                return new twoViewHolder(view);
            } else {
                view = View.inflate(viewGroup.getContext(), R.layout.content_item, null);
                return new threeViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }

        //多条目处理
        @Override
        public int getItemViewType(int position) {
            if (position == 0) return Banner;
            else if (position == 1) return Notice;
            else return Content;

        }

        @Override
        public int getItemCount() {
            return 50;
        }
    }

    class myViewHolder extends RecyclerView.ViewHolder {//暂时不去处理数据

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    class twoViewHolder extends RecyclerView.ViewHolder {//暂时不去处理数据

        public twoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class threeViewHolder extends RecyclerView.ViewHolder {//暂时不去处理数据

        public threeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
