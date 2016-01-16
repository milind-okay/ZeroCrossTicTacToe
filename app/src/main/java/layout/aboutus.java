package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wordpress.milindkrohit.zerocrosstictactoe.BuildConfig;
import com.wordpress.milindkrohit.zerocrosstictactoe.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link aboutus.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link aboutus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class aboutus extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageButton rate,facebook;
    private TextView ver;

    private OnFragmentInteractionListener mListener;

    public aboutus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment aboutus.
     */
    // TODO: Rename and change types and number of parameters
    public static aboutus newInstance(String param1, String param2) {
        aboutus fragment = new aboutus();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aboutus, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rate = (ImageButton) getActivity().findViewById(R.id.rateus);
        facebook = (ImageButton)getActivity().findViewById(R.id.facebook);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "https://play.google.com/store/apps/details?id=com.wordpress.milindkrohit.sudokusolveradvanced";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));

            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "https://www.facebook.com/Tic-Tac-Toe-Xs-and-Os-1511132872515136/";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));

            }
        });
        String versionName = BuildConfig.VERSION_NAME;
        ver = (TextView)getActivity().findViewById(R.id.appv);
        ver.setText(versionName);
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
}
