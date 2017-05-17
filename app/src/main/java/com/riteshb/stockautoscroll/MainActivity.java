package com.riteshb.stockautoscroll;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final int duration = 10;
    final int pixelsToMove = 30;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    ScrollTextView tvContent;
    Button btn;
    HeaderAdapter madapter;
    private RecyclerView rv_autoScroll;
    private final Runnable SCROLLING_RUNNABLE = new Runnable() {

        @Override
        public void run() {
            rv_autoScroll.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = (ScrollTextView) findViewById(R.id.tvContent);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvContent.setSpeed(tvContent.getSpeed() - 5);
            }
        });
//        tvContent.setMovementMethod(new ScrollingMovementMethod());
//        tvContent.scroll();

        rv_autoScroll = (RecyclerView) findViewById(R.id.marqueList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        rv_autoScroll.setLayoutManager(layoutManager);
        rv_autoScroll.setHasFixedSize(true);
        madapter = new HeaderAdapter(MainActivity.this);
        rv_autoScroll.setAdapter(madapter);

        rv_autoScroll.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                if(lastItem == layoutManager.getItemCount()-1){
                    mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                    Handler postHandler = new Handler();
                    postHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rv_autoScroll.setAdapter(null);
                            rv_autoScroll.setAdapter(madapter);
                            mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
                        }
                    }, 2000);
                }
            }
        });
        mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
    }
}
