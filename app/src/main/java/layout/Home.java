package layout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wordpress.milindkrohit.zerocrosstictactoe.DBHelper;
import com.wordpress.milindkrohit.zerocrosstictactoe.R;
import com.wordpress.milindkrohit.zerocrosstictactoe.mfragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button startGame;
     int turn;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText firstPlayer,secondPlayer;
    mfragment comm;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public  DBHelper mydb;
    private String first_player_name,second_player_name;
    private OnFragmentInteractionListener mListener;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (mfragment) getActivity();
        mydb = new DBHelper(getActivity());
        firstPlayer = (EditText) getActivity().findViewById(R.id.first_player);
        secondPlayer = (EditText)getActivity().findViewById(R.id.second_player);
        startGame = (Button) getActivity().findViewById(R.id.home_button);
        radioGroup = (RadioGroup)getActivity().findViewById(R.id.radio_group);
        first_player_name = firstPlayer.getText().toString();
        second_player_name = secondPlayer.getText().toString();

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId == R.id.radioButton_x) {
                    turn = 1;
                }else{
                    turn = 0;
                }
                int pairNumber = mydb.numberOfRows();
                int flag = 0;


                for(int i=1;i<=pairNumber;i++){
                    Cursor rs = mydb.getData(i);
                    rs.moveToFirst();

                    if(checkPair(rs)) {
                        flag = i;
                        break;
                    }
                    if(!rs.isClosed())
                    rs.close();

                }


                if(pairNumber == 0 || flag == 0) {
                    if(pairNumber != 0) mydb.updatepair(1,pairNumber + 1);
                    if (mydb.insertPlayers(firstPlayer.getText().toString(), secondPlayer.getText().toString(), turn,pairNumber + 1))
                        comm.fragment_selector(2);
                }else {
                    if (mydb.updatepair(1,flag))
                        comm.fragment_selector(2);
                }
                }
        });
    }
    public boolean checkPair(Cursor rs){
        String firstP,secondP;
        firstP = rs.getString(rs.getColumnIndex(DBHelper.FIRST_PLAYER));
        secondP = rs.getString(rs.getColumnIndex(DBHelper.SECOND_PLAYER));
        if((firstPlayer.getText().toString().equalsIgnoreCase(firstP) && secondPlayer.getText().toString().equalsIgnoreCase(secondP)) ||
                (firstPlayer.getText().toString().equalsIgnoreCase(secondP) && secondPlayer.getText().toString().equalsIgnoreCase(firstP))){
            return true;
        }else return false;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
