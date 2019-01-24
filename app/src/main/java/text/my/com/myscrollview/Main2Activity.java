package text.my.com.myscrollview;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.wanglu.lib.juejin.WJueJinLikeAnim;

import static text.my.com.myscrollview.R.mipmap.ic_launcher;

public class Main2Activity extends AppCompatActivity {
    private String content = "你好大连我来北京看海，还有上海";
    TextView myText;
    ImageView imageView;
    boolean isLike;
    boolean isClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.myText = findViewById(R.id.htmltext);
        this.imageView = findViewById(R.id.my_dianzan);
        if (content.contains("来")) {
            content = content.replace("来", "<font color=\'#FA800A\'>来</font>");
        }

        // Log.e("TAG",content+"----");
        this.myText.setText(Html.fromHtml(content));

        this.imageView.setImageResource(R.drawable.dianzan);


        this.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dianzai();
            isClick = true;


            }
        });

        if (isClick){
            this.imageView.setImageResource(R.drawable.dianzan);
        }


    }

    //点赞的方法
    private void dianzai(){
        RequestListener<GifDrawable> requestListener = new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                resource.setLoopCount(1);//设置只执行一次
                //获取总帧数
                int frameCount = resource.getFrameCount();



                return false;
            }
        };


        //处理点赞效果


        RequestOptions requestOptions = new RequestOptions();
        //Glide加载
        Glide.with(Main2Activity.this)
                .asGif()
                .load(R.drawable.dianzan)
                .listener(requestListener)
                .into(imageView);



    }
}
