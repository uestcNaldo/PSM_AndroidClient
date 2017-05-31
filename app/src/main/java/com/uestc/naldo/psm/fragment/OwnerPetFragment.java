package com.uestc.naldo.psm.fragment;

import android.app.ActivityManager;
import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.uestc.naldo.psm.MyApplication;
import com.uestc.naldo.psm.R;
import com.uestc.naldo.psm.activity.MainActivity.OwnerMainActivity;
import com.uestc.naldo.psm.adapter.PetItemAdapter;
import com.uestc.naldo.psm.json.PetListResult;
import com.uestc.naldo.psm.model.Owner;
import com.uestc.naldo.psm.model.Pet;
import com.uestc.naldo.psm.util.Static;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OwnerPetFragment extends Fragment {

    private Owner owner;

    private String URL_PROTOCOL = "http://";
    private String URL_IP = Static.URL_IP;
    private String URL_SUFFIX = "/app/ownergetpetlist";
    private String URL = URL_PROTOCOL+URL_IP+URL_SUFFIX;

    private PetItemAdapter petItemAdapter;

    private static final String TAG = "OwnerPetFragment";
    public static List<Pet> petItemList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        owner = OwnerMainActivity.owner;
        setHasOptionsMenu(true);
        getPetList();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_owner_pet, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toolbar toolbar_owner_pet = (Toolbar) getActivity().findViewById(R.id.toolbar_owner_pet);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_owner_pet);

        RecyclerView recyclerView_PetItemList = (RecyclerView) getActivity().findViewById(R.id.recycler_view_pet);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView_PetItemList.setLayoutManager(layoutManager);
        petItemAdapter = new PetItemAdapter(petItemList);
        recyclerView_PetItemList.setAdapter(petItemAdapter);

    }

    public void getPetList() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();

                RequestBody requestBody = new FormBody.Builder()
                        .add("ownerId", String.valueOf(owner.getId()))
                        .build();
                Request request = builder.post(requestBody).url(URL).build();

                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "获取宠物列表失败,请重试", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.d(TAG, "onResponse: "+responseData);
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                        final PetListResult petListResult = gson.fromJson(responseData, PetListResult.class);



                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                petItemAdapter.notifyDataSetChanged();
                                if (petListResult.getCode()==1){
                                    Toast.makeText(getActivity(), "获取宠物列表成功", Toast.LENGTH_LONG).show();
                                    petItemList.clear();
                                    for (Pet pet : petListResult.getPetList()){
                                        petItemList.add(pet);
                                    }
                                }
                                if (petListResult.getCode()==0){
                                    Toast.makeText(getActivity(), "宠物列表为空", Toast.LENGTH_LONG).show();
                                }
                            }
                        });


                    }
                });

            }
        }).start();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu_owner_pet, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_button_refresh:{
                getPetList();

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    public void showSelectPetDialog(){
//        DialogFragment dialogFragment = new SelectPetDialogFragment();
//        dialogFragment.show(getFragmentManager(), "select_pet");
//    }


}
