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
import android.widget.Toast;

import text.my.com.myscrollview.com.my.dome.PinnedHeaderAdapter;
import text.my.com.myscrollview.com.my.dome.PinnedHeaderItemDecoration;
import text.my.com.myscrollview.com.my.dome.PinnedHeaderRecyclerView;

import static android.widget.Toast.LENGTH_SHORT;

//结束？
//noway


public class MainActivity extends AppCompatActivity {
    //private RecyclerView myRecycler;
    private PinnedHeaderRecyclerView mRecyclerView;
    View myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.myRecycle);
        myView = findViewById(R.id.myshai);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new MyAdapter());
        mRecyclerView.addItemDecoration(new PinnedHeaderItemDecoration());//增加装饰器样式
        //增加装饰器点击事件
//        mRecyclerView.setOnPinnedHeaderClickListener(new PinnedHeaderRecyclerView.OnPinnedHeaderClickListener() {
//            @Override
//            public void onPinnedHeaderClick(int adapterPosition) {
//                Toast.makeText(MainActivity.this, "点击了悬浮标题 position = " + adapterPosition, LENGTH_SHORT).show();
//            }
//        });

    }

    class MyAdapter extends PinnedHeaderAdapter<RecyclerView.ViewHolder> {
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

        //增加头部装饰对方法
        @Override
        public boolean isPinnedPosition(int position) {
            return getItemViewType(position) == Notice;
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
