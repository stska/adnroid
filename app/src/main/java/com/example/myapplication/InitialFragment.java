package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InitialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InitialFragment extends Fragment {



  WeatherInfo weatherInfo = new WeatherInfo();

    private FragmentActivity myContext;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InitialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment initialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InitialFragment newInstance(String param1, String param2) {
        InitialFragment fragment = new InitialFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
      
            /*  Не работает зараза
                getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("bundleKey");
                // Do something with the result...
            }
        });  */


    }
    //changed 08.07.2020 //------------------------------------------------хммммммммммммммммммммммммммммммммммм
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.initial_fragment, container, false);
        final SearchView searchView = (SearchView) view.findViewById(R.id.searchViewId);



       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
               // android.R.layout.simple_dropdown_item_1line, weatherInfo.getCityNamesArray());

       // searchView.setSuggestionsAdapter(adapter);





        //обработка ввода текста и его отправка
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                boolean checkUp = false;
                int position = 0;
                //пробегаемся по списку имебщихся город и сравниваем с тем, что ввели. Я понимаю, что нужен некий фильтр еще здесь, но пока  так
                for (int i = 0; i < weatherInfo.getCityNamesArray().length; i++) {
                    if (weatherInfo.getCityNamesArray()[i].equals(query)) {
                        checkUp = true;
                        position = i;
                    }
                }


                if(checkUp){
                    goTo(position);
                    searchView.clearFocus(); //-------------------------------------------
                    return true;
                }else  Toast.makeText(getActivity(), "No Match found", Toast.LENGTH_LONG).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


       // String[] data = {"Moscow","Tula","Kiev","Tokyo","Kaliningrad"};
        initRecycleView(view,weatherInfo.getCityNamesArray());


        return view;

    }

    private void goTo(int i){
        CityWeatherDescription cwd = CityWeatherDescription.newInstance(i);
        cwd.setTargetFragment(InitialFragment.this,111);
        ///--------------------------------------------------
        FragmentTransaction ft = ((AppCompatActivity)myContext).getSupportFragmentManager().beginTransaction()
                .replace(R.id.initialFramLayoutId,cwd)
                .addToBackStack(cwd.getClass().getName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);    //--------------changed 08.07.2020

        ft.commit();



        /*
        FragmentC fragmentC = FragmentC.newInstance();
fragmentC.setTargetFragment(FragmentB.this, REQUEST_CODE);
getFragmentManager().beginTransaction().replace(R.id.container, fragmentC).commit();
         */
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



    }
    private void initRecycleView(View view,String[] data){

        RecyclerView recyclerView = view.findViewById(R.id.cityWeatherRecycleView);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayout.VERTICAL);
        itemDecoration.setDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.separator,null));
        recyclerView.addItemDecoration(itemDecoration);

        LinearLayoutManager layoutManager = new LinearLayoutManager(myContext);
        recyclerView.setLayoutManager(layoutManager);

        TestRecycleViewAdapter adapter = new TestRecycleViewAdapter(data);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemListener(new TestRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), String.format("%s - %d", ((TextView)view).getText(), position), Toast.LENGTH_SHORT).show();
                CityWeatherDescription cwd = CityWeatherDescription.newInstance(position);
                ///--------------------------------------------------
               goTo(position);
            }

        });
    }
}

