package com.example.lesson7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.lesson7.adapter.CustomAdapter;
import com.example.lesson7.model.User;

import java.util.ArrayList;
import java.util.List;

public class Apelsin extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apelsin);
        initViews();
        
        List<User> users = prepareUserList();
        refreshAdapter(users);
    }
    public void refreshAdapter(List<User> users){
        CustomAdapter adapter = new CustomAdapter(context,users);
        recyclerView.setAdapter(adapter);
    }

    private List<User> prepareUserList() {
        List<User> users = new ArrayList<>();
        for (int i=1; i<4; i++){
            users.add(new User(" A "+i));
        }
        return users;
    }

    private void initViews() {
        layout1 = findViewById(R.id.first);
        layout2 = findViewById(R.id.second);
        layout3 = findViewById(R.id.third);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int activePosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (activePosition==RecyclerView.NO_POSITION) return;
                Log.d("@@@","activitePosition"+activePosition);
                switch (activePosition){
                    case 0: layout1.setBackgroundResource(R.drawable.linear_layout_rounded_avtive);
                    layout2.setBackgroundResource(R.drawable.linear_layout_rounded);
                    layout3.setBackgroundResource(R.drawable.linear_layout_rounded);
                    break;
                    case 1:layout1.setBackgroundResource(R.drawable.linear_layout_rounded);
                        layout2.setBackgroundResource(R.drawable.linear_layout_rounded_avtive);
                        layout3.setBackgroundResource(R.drawable.linear_layout_rounded);
                        break;
                    default:layout1.setBackgroundResource(R.drawable.linear_layout_rounded);
                        layout2.setBackgroundResource(R.drawable.linear_layout_rounded);
                        layout3.setBackgroundResource(R.drawable.linear_layout_rounded_avtive);
                        break;
                }


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}