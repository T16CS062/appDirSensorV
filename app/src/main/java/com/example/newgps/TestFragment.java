package com.example.newgps;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;





public class TestFragment extends Fragment{

    //private SensorManager sensorManager;

    //public ImageView arrowBView;
    public double elevation;
    public View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "onCreate");

        super.onCreate(savedInstanceState);

    }


    @Override
    public void onResume() {
        Log.d("onResume", "onResume");

        super.onResume();

        // 渡ってきたBundleから初期値を設定
        Bundle bnd = getArguments();
/*
        if(bnd != null){
            elevation = bnd.getDouble("elevation");
            arrowBView.setRotation(90 + (float)elevation);
            arrowBView.postInvalidate();
            System.out.println("elevation >> " +  elevation);
        }else{
            System.out.println("bnd is null");
        }

*/
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("onActivityCreated", "onActivityCreated");

        super.onActivityCreated(savedInstanceState);
    }



    public static TestFragment newInstance(String str){
        Log.d("newInstance", "newInstance");

        // Fragemnt01 インスタンス生成
        TestFragment fragment = new TestFragment();
        // Bundle にパラメータを設定
        Bundle barg = new Bundle();
        barg.putString("Message", str);
        fragment.setArguments(barg);

        return fragment;
    }


    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onCreateView", "onCreateView");

        //container.removeAllViews();
        rootView = inflater.inflate(R.layout.fragment_main,
                container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Log.d("onViewCreated", "onViewCreated");

        super.onViewCreated(view, savedInstanceState);

        ImageView arrowBView;
        arrowBView = view.findViewById(R.id.arrow_b);

        Bundle bnd = getArguments();

        if(bnd != null){
            elevation = bnd.getDouble("elevation");
            arrowBView.setRotation(90 + (float)elevation);
            arrowBView.postInvalidate();
            System.out.println("elevation >> " +  elevation);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("onPause", "onPause");

    }

    @Override
    public void onDestroyView() {
        Log.d("onDestroyView", "onDestroyView");

        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        Log.d("onDestroy", "onDestroy");
        super.onDestroy();
    }

    public void SetRotationView(float ele){
        ImageView arrowBView = (ImageView) rootView.findViewById(R.id.arrow_b);
        arrowBView.setRotation(90 + ele);
    }

    public TestFragment() {
    }

}