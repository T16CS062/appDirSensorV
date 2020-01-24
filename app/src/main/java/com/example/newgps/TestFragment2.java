package com.example.newgps;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;




public class TestFragment2 extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;

    private ImageView arrowBView;
    public double elevation;
    public long interval = 30 /* msec */;
    public int i = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get an instance of the SensorManager
        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);

    }


    @Override
    public void onResume() {
        super.onResume();
        // Listenerの登録
        Sensor accel = sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);

        /*
        Bundle bnd = getArguments();

        if(bnd != null){

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                // このメソッドが定期的に実行される
                @Override
                public void run() {

                    elevation = bnd.getDouble("elevation");
                    // アニメーションの状態更新（ボールの位置とか）
                    System.out.println(elevation);
                    arrowBView.setRotation(90 + (float) elevation);
                    arrowBView.postInvalidate();
                }

            }, interval, interval);

        }
*/
    }

    // 解除するコードも入れる!
    @Override
    public void onPause() {
        super.onPause();
        // Listenerを解除
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float sensorX, sensorY, sensorZ;

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            sensorZ = event.values[2];
            if(sensorZ > 3.0 || sensorZ < -3.0){
                //getFragmentManager().popBackStack();
                getFragmentManager().beginTransaction().remove(this).commit();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // 渡ってきたBundleから初期値を設定
        Bundle bundle = getArguments();

    }



    public static TestFragment newInstance(String str){
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

        return inflater.inflate(R.layout.fragment_main,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        arrowBView = view.findViewById(R.id.arrow_b);

        Bundle bnd = getArguments();

        if(bnd != null){
            elevation = bnd.getDouble("elevation");

            arrowBView.setRotation(90 + (float)elevation);
            arrowBView.postInvalidate();

        }

    }
}