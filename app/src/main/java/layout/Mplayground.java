package layout;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wordpress.milindkrohit.zerocrosstictactoe.DBHelper;
import com.wordpress.milindkrohit.zerocrosstictactoe.R;
import com.wordpress.milindkrohit.zerocrosstictactoe.mfragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Mplayground.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Mplayground#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Mplayground extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public DBHelper mydb;
    mfragment comm;
    private OnFragmentInteractionListener mListener;
    private TextView first_player,first_player_score,second_player,second_player_score,game_result,player_turn;
    private ImageButton ibutton_1,ibutton_2,ibutton_3,ibutton_4,ibutton_5,ibutton_6,ibutton_7,ibutton_8,ibutton_9;
    int arr[][],turn_no = 0,flag = 0;
    boolean mturn,selectedTurn;
    int mfirst_player_score,msecond_player_score,mties;
    private Button replay;
    public Mplayground() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Mplayground.
     */
    // TODO: Rename and change types and number of parameters
    public static Mplayground newInstance(String param1, String param2) {
        Mplayground fragment = new Mplayground();
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
        return inflater.inflate(R.layout.fragment_mplayground, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mydb = new DBHelper(getActivity());
        init();
        comm = (mfragment) getActivity();
        if(selectedTurn) {
            if (mturn)
                player_turn.setText(first_player.getText().toString() + " turn");
            else player_turn.setText(second_player.getText().toString() + " turn");
        }else{
            if (!mturn)
                player_turn.setText(first_player.getText().toString() + " turn");
            else player_turn.setText(second_player.getText().toString() + " turn");
        }



    }
   public void init(){
       first_player = (TextView)getActivity().findViewById(R.id.first_player_name);
       second_player = (TextView)getActivity().findViewById(R.id.second_player_name);
       first_player_score = (TextView)getActivity().findViewById(R.id.first_player_score);
       second_player_score = (TextView)getActivity().findViewById(R.id.second_player_score);
       game_result = (TextView)getActivity().findViewById(R.id.game_result);
       player_turn = (TextView)getActivity().findViewById(R.id.player_turn);
       replay = (Button)getActivity().findViewById(R.id.replay_button);
       Cursor rs = mydb.getData(1);
       rs.moveToFirst();
       first_player.setText(rs.getString(rs.getColumnIndex(DBHelper.FIRST_PLAYER)));
       second_player.setText(rs.getString(rs.getColumnIndex(DBHelper.SECOND_PLAYER)));
       mfirst_player_score = rs.getInt(rs.getColumnIndex(DBHelper.FIRST_PLAYER_SCORE));
       msecond_player_score = rs.getInt(rs.getColumnIndex(DBHelper.SECOND_PLAYER_SCORE));

               mties =  rs.getInt(rs.getColumnIndex(DBHelper.TIES));
       turn_no = rs.getInt(rs.getColumnIndex(DBHelper.TURN));
       if( turn_no == 0) mturn = false;
       else mturn = true;
       first_player_score.setText(String.format("%d",mfirst_player_score));
       second_player_score.setText(String.format("%d", msecond_player_score));
       ibutton_1 = (ImageButton) getActivity().findViewById(R.id.row1col1);
       ibutton_2 = (ImageButton) getActivity().findViewById(R.id.row1col2);
       ibutton_3 = (ImageButton) getActivity().findViewById(R.id.row1col3);
       ibutton_4 = (ImageButton) getActivity().findViewById(R.id.row2col1);
       ibutton_5 = (ImageButton) getActivity().findViewById(R.id.row2col2);
       ibutton_6 = (ImageButton) getActivity().findViewById(R.id.row2col3);
       ibutton_7 = (ImageButton) getActivity().findViewById(R.id.row3col1);
       ibutton_8 = (ImageButton) getActivity().findViewById(R.id.row3col2);
       ibutton_9 = (ImageButton) getActivity().findViewById(R.id.row3col3);
       ibutton_1.setOnClickListener(this);
       ibutton_2.setOnClickListener(this);
       ibutton_3.setOnClickListener(this);
       ibutton_4.setOnClickListener(this);
       ibutton_5.setOnClickListener(this);
       ibutton_6.setOnClickListener(this);
       ibutton_7.setOnClickListener(this);
       ibutton_8.setOnClickListener(this);
       ibutton_9.setOnClickListener(this);

       arr = new int[3][3];
       for(int i =0;i<3;i++){
           for(int j=0;j<3;j++){
               arr[i][j] = 0;
           }
       }
   }
    public void checkResult(){

        int j = 0;

        for(int i=0;i<3;i++){
            int num = arr[i][0];
            if(num != 0) {
                for (j = 1; j < 3; j++) {
                    if (num != arr[i][j]) break;
                }
            }
            if(j == 3){
                flag = arr[i][j - 1];
                break;
            }
        }
        for(int i=0;i<3;i++){
            int num = arr[0][i];
            if(num != 0) {
                for (j = 1; j < 3; j++) {
                    if (num != arr[j][i]) break;
                }
            }
            if(j == 3){
                flag = arr[j - 1][i];
                break;
            }
        }
        if(arr[2][0] == arr[1][1]  && arr[1][1] == arr[0][2]){
            flag = arr[1][1];
        }
        if(arr[0][0] == arr[1][1]  && arr[1][1] == arr[2][2]){
            flag = arr[1][1];
        }
        if(flag != 0) {
            if (selectedTurn) {
                game_result.setText(first_player.getText().toString() + "  wins");
                mfirst_player_score++;
                first_player_score.setText(String.format("%d", mfirst_player_score));


            } else {
                game_result.setText(second_player.getText().toString() + "  wins");
                msecond_player_score++;
                second_player_score.setText(String.format("%d",msecond_player_score));


            }
            if(mfirst_player_score > msecond_player_score){
                second_player_score.setTextColor(Color.RED);
                first_player_score.setTextColor(Color.GREEN);
            }
            else{
                second_player_score.setTextColor(Color.GREEN);
                first_player_score.setTextColor(Color.RED);
            }

            for(int i =0;i<3;i++){
                for(int k=0;k<3;k++){
                    arr[i][k] = 3;
                }
            }
        }else if(turn_no == 9){
            game_result.setText("Game Ties");
            mties++;
        }
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
    public void onClick(View v) {
        turn_no++;
        switch (v.getId()){
            case R.id.row1col1:
                if(arr[0][0] == 0){
                    if(mturn){
                        ibutton_1.setBackgroundResource(R.drawable.ic_x);
                        arr[0][0] = 1;
                        mturn = false;
                    }
                    else{
                        ibutton_1.setBackgroundResource(R.drawable.ic_o);
                        arr[0][0] = 2;
                        mturn = true;
                    }


                }
                break;
            case R.id.row1col2:
                if(arr[0][1] == 0){
                    if(mturn){
                        ibutton_2.setBackgroundResource(R.drawable.ic_x);
                        arr[0][1] = 1;
                        mturn = false;
                    }
                    else{
                        ibutton_2.setBackgroundResource(R.drawable.ic_o);
                        arr[0][1] = 2;
                        mturn = true;
                    }


                }
                break;
            case R.id.row1col3:
                if(arr[0][2] == 0){
                    if(mturn){
                        ibutton_3.setBackgroundResource(R.drawable.ic_x);
                        arr[0][2] = 1;
                        mturn = false;
                    }
                    else{
                        ibutton_3.setBackgroundResource(R.drawable.ic_o);
                        arr[0][2] = 2;
                        mturn = true;
                    }


                }
                break;
            case R.id.row2col1:
                if(arr[1][0] == 0){
                    if(mturn){
                        ibutton_4.setBackgroundResource(R.drawable.ic_x);
                        arr[1][0] = 1;
                        mturn = false;
                    }
                    else{
                        ibutton_4.setBackgroundResource(R.drawable.ic_o);
                        arr[1][0] = 2;
                        mturn = true;
                    }


                }
                break;
            case R.id.row2col2:
                if(arr[1][1] == 0){
                    if(mturn){
                        ibutton_5.setBackgroundResource(R.drawable.ic_x);
                        arr[1][1] = 1;
                        mturn = false;
                    }
                    else{
                        ibutton_5.setBackgroundResource(R.drawable.ic_o);
                        arr[1][1] = 2;
                        mturn = true;
                    }


                }
                break;
            case R.id.row2col3:
                if(arr[1][2] == 0){
                    if(mturn){
                        ibutton_6.setBackgroundResource(R.drawable.ic_x);
                        arr[1][2] = 1;
                        mturn = false;
                    }
                    else{
                        ibutton_6.setBackgroundResource(R.drawable.ic_o);
                        arr[1][2] = 2;
                        mturn = true;
                    }


                }
                break;
            case R.id.row3col1:
                if(arr[2][0] == 0){
                    if(mturn){
                        ibutton_7.setBackgroundResource(R.drawable.ic_x);
                        arr[2][0] = 1;
                        mturn = false;
                    }
                    else{
                        ibutton_7.setBackgroundResource(R.drawable.ic_o);
                        arr[2][0] = 2;
                        mturn = true;
                    }


                }
                break;
            case R.id.row3col2:
                if(arr[2][1] == 0){
                    if(mturn){
                        ibutton_8.setBackgroundResource(R.drawable.ic_x);
                        arr[2][1] = 1;
                        mturn = false;
                    }
                    else{
                        ibutton_8.setBackgroundResource(R.drawable.ic_o);
                        arr[2][1] = 2;
                        mturn = true;
                    }


                }
                break;
            case R.id.row3col3:
                if(arr[2][2] == 0){
                    if(mturn){
                        ibutton_9.setBackgroundResource(R.drawable.ic_x);
                        arr[2][2] = 1;
                        mturn = false;
                    }
                    else{
                        ibutton_9.setBackgroundResource(R.drawable.ic_o);
                        arr[2][2] = 2;
                        mturn = true;
                    }


                }
                break;
            case R.id.replay_button:
                replayGame();
                break;
            default:


        }
        if(selectedTurn) {
            if (mturn)
                player_turn.setText(first_player.getText().toString() + "'s turn");
            else player_turn.setText(second_player.getText().toString() + "'s turn");
        }else{
            if (!mturn)
                player_turn.setText(first_player.getText().toString() + "'s turn");
            else player_turn.setText(second_player.getText().toString() + "'s turn");
        }
        if(turn_no > 4 && flag == 0) checkResult();
    }
    public void replayGame(){
        mturn = selectedTurn;
        turn_no = 0;
        ibutton_1.setBackgroundResource(R.drawable.ic_blank);
        ibutton_2.setBackgroundResource(R.drawable.ic_blank);
        ibutton_3.setBackgroundResource(R.drawable.ic_blank);
        ibutton_4.setBackgroundResource(R.drawable.ic_blank);
        ibutton_5.setBackgroundResource(R.drawable.ic_blank);
        ibutton_6.setBackgroundResource(R.drawable.ic_blank);
        ibutton_7.setBackgroundResource(R.drawable.ic_blank);
        ibutton_8.setBackgroundResource(R.drawable.ic_blank);
        ibutton_9.setBackgroundResource(R.drawable.ic_blank);
        game_result.setText("");
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                arr[i][j] = 0;
            }
        }
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
