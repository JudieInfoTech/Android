package com.example.userslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Toast;
import com.google.gson.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCall apiService =
                retrofit.create(ApiCall.class);


        compositeDisposable.add(apiService.getUserDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Users>>() {
                    @Override
                    public void accept(List<Users> posts) throws Exception {
                        jsonParse(posts);
                    }
                }));


    }
    private void jsonParse(List<Users> posts){

        try {


                ArrayList<Users> usersArrayList = new ArrayList<>();
                //JSONArray dataArray  = obj.getJSONArray("articles");

                for (int i = 0; i < posts.size(); i++) {

                    Users users = new Users();
                    users.setName(posts.get(i).getName());
                    users.setEmail(posts.get(i).getEmail());
                    users.setPhone(posts.get(i).getPhone());
                    usersArrayList.add(users);
                }
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                MyAdapter adapter = new MyAdapter(usersArrayList,MainActivity.this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

