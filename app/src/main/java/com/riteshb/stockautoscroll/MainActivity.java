package com.riteshb.stockautoscroll;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    final int duration = 10;
    final int pixelsToMove = 30;
    private final Runnable SCROLLING_RUNNABLE = new Runnable() {

        @Override
        public void run() {
            rv_autoScroll.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private RecyclerView rv_autoScroll;
    HeaderAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    rv_autoScroll.setAdapter(null);
                    rv_autoScroll.setAdapter(madapter);
                }

            }
        });
        mHandler.post(SCROLLING_RUNNABLE);
    }
}
