package jp.ac.doshisha.mikilab.tom.kc104;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ControlAllFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ControlAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlAllFragment extends Fragment {
    View view;
    NumberPicker pic1, pic2;
    Button sendButton;
    RelativeLayout layout;
    Button btn_w100, btn_w80, btn_w60, btn_w40, btn_w20, btn_w0;
    Button btn_e100, btn_e80, btn_e60, btn_e40, btn_e20, btn_e0;

    private OnFragmentInteractionListener mListener;

    public ControlAllFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ControlAllFragment newInstance() {
        ControlAllFragment fragment = new ControlAllFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_control_all, container, false);
        // Inflate the layout for this fragment
        pic1 = (NumberPicker) view.findViewById(R.id.signalPicker1);
        pic2 = (NumberPicker) view.findViewById(R.id.signalPicker2);
        sendButton = (Button) view.findViewById(R.id.buttonSendAll);
        layout = (RelativeLayout) view.findViewById(R.id.control_layout);

        btn_w100 = (Button) view.findViewById(R.id.button_w100);
        btn_w80 = (Button) view.findViewById(R.id.button_w80);
        btn_w60 = (Button) view.findViewById(R.id.button_w60);
        btn_w40 = (Button) view.findViewById(R.id.button_w40);
        btn_w20 = (Button) view.findViewById(R.id.button_w20);
        btn_w0 = (Button) view.findViewById(R.id.button_w0);
        btn_e100 = (Button)view.findViewById(R.id.button_e100);
        btn_e80 = (Button)view.findViewById(R.id.button_e80);
        btn_e60 = (Button)view.findViewById(R.id.button_e60);
        btn_e40 = (Button)view.findViewById(R.id.button_e40);
        btn_e20 = (Button)view.findViewById(R.id.button_e20);
        btn_e0 = (Button)view.findViewById(R.id.button_e0);

        initView();

        return view;
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

    public void initView() {
        pic1.setMaxValue(Constants.MAX_SIGNAL);
        pic1.setMinValue(0);

        pic2.setMaxValue(Constants.MAX_SIGNAL);
        pic2.setMinValue(0);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runDim();
            }
        });

        btn_w100.setOnClickListener(new signalButtonOnClickListener());
        btn_w80.setOnClickListener(new signalButtonOnClickListener());
        btn_w60.setOnClickListener(new signalButtonOnClickListener());
        btn_w40.setOnClickListener(new signalButtonOnClickListener());
        btn_w20.setOnClickListener(new signalButtonOnClickListener());
        btn_w0.setOnClickListener(new signalButtonOnClickListener());
        btn_e100.setOnClickListener(new signalButtonOnClickListener());
        btn_e80.setOnClickListener(new signalButtonOnClickListener());
        btn_e60.setOnClickListener(new signalButtonOnClickListener());
        btn_e40.setOnClickListener(new signalButtonOnClickListener());
        btn_e20.setOnClickListener(new signalButtonOnClickListener());
        btn_e0.setOnClickListener(new signalButtonOnClickListener());
    }

    /**
     * ソケット通信を行うAsyncTaskを発行し，トーストで通知も表示
     */
    public void runDim() {
        new AsyncDimmer().execute("all", String.valueOf(pic1.getValue()), String.valueOf(pic2.getValue()));
        Snackbar.make(layout, getString(R.string.snackbar_send), Snackbar.LENGTH_LONG).show();
    }

    /**
     * 信号値のボタン用のリスナーですよ
     */
    class signalButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_w100:
                    pic1.setValue(Constants.MAX_SIGNAL);
                    System.out.println("OK");
                    break;
                case R.id.button_w80:
                    pic1.setValue(Constants.MAX_SIGNAL / 5 * 4);
                    break;
                case R.id.button_w60:
                    pic1.setValue(Constants.MAX_SIGNAL / 5 * 3);
                    break;
                case R.id.button_w40:
                    pic1.setValue(Constants.MAX_SIGNAL / 5 * 2);
                    break;
                case R.id.button_w20:
                    pic1.setValue(Constants.MAX_SIGNAL / 5 * 1);
                    break;
                case R.id.button_w0:
                    pic1.setValue(0);
                    break;
                case R.id.button_e100:
                    pic2.setValue(Constants.MAX_SIGNAL);
                    break;
                case R.id.button_e80:
                    pic2.setValue(Constants.MAX_SIGNAL / 5 * 4);
                    break;
                case R.id.button_e60:
                    pic2.setValue(Constants.MAX_SIGNAL / 5 * 3);
                    break;
                case R.id.button_e40:
                    pic2.setValue(Constants.MAX_SIGNAL / 5 * 2);
                    break;
                case R.id.button_e20:
                    pic2.setValue(Constants.MAX_SIGNAL / 5 * 1);
                    break;
                case R.id.button_e0:
                    pic2.setValue(0);
                    break;
                default:
                    break;
            }

            runDim();
        }
    }
}
