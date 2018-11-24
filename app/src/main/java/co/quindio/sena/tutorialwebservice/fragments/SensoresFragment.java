package co.quindio.sena.tutorialwebservice.fragments;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import co.quindio.sena.tutorialwebservice.R;
import co.quindio.sena.tutorialwebservice.TemperaturaActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SensoresFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SensoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensoresFragment extends Fragment implements SensorEventListener {
    /*
    TODO: Rename parameter arguments, choose names that match
    the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    */
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private boolean isSensorPresent;
    private TextView mTemperatureValue;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SensoresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensoresFragment newInstance(String param1, String param2) {
        SensoresFragment fragment = new SensoresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mTemperatureValue  = (TextView) getView().findViewById(R.id.temperaturatxt);
                if(mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!= null)
                {
                          mSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
                          isSensorPresent=true;


                }else
                {

                    mTemperatureValue.setText("EL SENSOR DE TEMPERATURA NO ESTA DISPONILBE!");
                    isSensorPresent=false;
                }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        if (isSensorPresent) {
            /*

            hjjj*/
            /*dadadasdasd*/
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(isSensorPresent) {
            mSensorManager.unregisterListener(this);
        }
    }

    /* public void controlador(View view, @Nullable Bundle savedInstanceState) {
        Button entry = (Button) getView().findViewById(R.id.btnCambioTemperatura);
        //hago clic y se abre el 2
        entry.setOnClickListener(new View.OnClickListener()
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SensoresFragment.this.getContext(),
                        TemperaturaActivity.class);
                startActivity(intent);
            }
        });
    }
    public void controlado() {
        Button entry = (Button) getView().findViewById(R.id.btnCambioTemperatura);
        //hago clic y se abre el 2
        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SensoresFragment.this.getContext(),
                        TemperaturaActivity.class);
                startActivity(intent);
            }
        });
    }*/



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensores, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        mTemperatureValue.setText("La temperatura en grados C° " + event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onDestroy() {
        mSensorManager = null;
        mSensor = null;
        super.onDestroy();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
