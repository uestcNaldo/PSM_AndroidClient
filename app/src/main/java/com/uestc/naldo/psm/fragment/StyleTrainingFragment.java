package com.uestc.naldo.psm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.adapter.StyleAdapter;
import com.uestc.naldo.psm.model.Photo;
import com.uestc.naldo.psm.model.StyleItem;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StyleTrainingFragment extends Fragment {

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/getstylephotolistall";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private StyleAdapter styleAdapter;

    private List<Photo> photoListAll = new ArrayList<>();

    public StyleTrainingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPhotoListAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_style_training, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView_StyleItemList = (RecyclerView) getActivity().findViewById(R.id.recyclerview_trainingStyle);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView_StyleItemList.setLayoutManager(layoutManager);
        styleAdapter = new StyleAdapter(photoListAll);
        recyclerView_StyleItemList.setAdapter(styleAdapter);

    }

    //获取图片列表
    private void getPhotoListAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();

                Request request = builder.get().url(URL).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "获取照片列表失败,请重试", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Gson gson = new Gson();
                        final List<Photo> photoList = gson.fromJson(responseData, new TypeToken<List<Photo>>(){}.getType());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                photoListAll.clear();
                                for (Photo photo : photoList){
                                    photoListAll.add(photo);
                                }
                                styleAdapter.notifyDataSetChanged();
                                if (photoList.size()==0){
                                    Toast.makeText(getActivity(), "没有图片展示", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        }).start();
    }

}
