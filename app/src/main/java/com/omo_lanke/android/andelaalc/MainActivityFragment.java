package com.omo_lanke.android.andelaalc;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.omo_lanke.android.andelaalc.Adapters.MyListAdapter;
import com.omo_lanke.android.andelaalc.models.GitHubList;
import com.omo_lanke.android.andelaalc.models.GitHubResponse;
import com.omo_lanke.android.andelaalc.services.Api;
import com.rey.material.widget.ProgressView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.list)
    ListView users;

//    @BindView(R.id.tit)
//    TextView title;

    @BindView(R.id.view_loading)
    ProgressView progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

//        Call<GitHubResponse> resp = Api.Git().listUsers("Lagos");
//        GitHubResponse users
//        title.setText("Page loaded");
        Call<GitHubResponse> responseCall = new Api().gitHubService().listUsers();
        responseCall.enqueue(new Callback<GitHubResponse>() {
            @Override
            public void onResponse(Call<GitHubResponse> call, Response<GitHubResponse> response) {
//                Log.i("LagosItemCount",response.body().getTotal_count());
                try{
                    final List<String[]> userList = new ArrayList<String[]>();
                    for(GitHubList listing : response.body().getItems()){
                        Log.i("Result", listing.getLogin());
                        String[] item = new String[4];
                        item[0] = listing.getLogin();
                        item[1] = listing.getAvatar_url();
                        item[2] = listing.getHtml_url();
                        userList.add(item);
                    }
                    Log.e("Users Count", userList.size() +"");
//                    title.setText("Page loaded");
                    MyListAdapter myListAdapter = new MyListAdapter(getContext(),userList);
                    users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                            Intent intent = new Intent(getContext(), DetailsActivity.class);
                            intent.putExtra("user", userList.get(pos));
                            startActivity(intent);
                        }
                    });
                    users.setAdapter(myListAdapter);
                    progressBar.setVisibility(View.GONE);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.i("LagosItemCount",e.toString());
                }

            }

            @Override
            public void onFailure(Call<GitHubResponse> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });
        return view;
    }


//    class ProgrammeListAdapter extends ArrayAdapter {
//        private Context mContext;
//        private String[][] ar;
//
//        public ProgrammeListAdapter(Context context, int textViewResourceId, String[][] objects) {
//            super(context, textViewResourceId, objects);
//            mContext = context;
//            this.ar = objects;
//        }
//
//        public View getCustomView(int position, View convertView, ViewGroup parent) {
//
//            View layout = (convertView == null
//                    ? LayoutInflater.from(mContext).inflate(R.layout.programme_list, parent, false)
//                    : convertView);
//
//            TextView time = (TextView) layout.findViewById(R.id.programme_time);
//            TextView desc = (TextView) layout.findViewById(R.id.programme_desc);
//
//            time.setText(ar[position][0]);
//            desc.setText(ar[position][2]);
//
//            return layout;
//        }
//
//        // It gets a View that displays the data at the specified position
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            return getCustomView(position, convertView, parent);
//        }
//    }
}
