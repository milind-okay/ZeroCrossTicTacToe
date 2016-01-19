package layout;

import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.milindkrohit.zerocrosstictactoe.DBHelper;
import com.wordpress.milindkrohit.zerocrosstictactoe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milind on 18/1/16.
 */
public class ListScore extends ListFragment {
    private List<ListViewItem> mItems;        // ListView items list
    String players_name,players_score;
    DBHelper mydb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb = new DBHelper(getActivity());
        // initialize the items list
        mItems = new ArrayList<ListViewItem>();
        int pairNumber = mydb.numberOfRows();

        for(int i=1;i<=pairNumber;i++){
            Cursor rs = mydb.getData(i);
            rs.moveToFirst();
            players_name = rs.getString(rs.getColumnIndex(DBHelper.FIRST_PLAYER)) + "  vs  " + rs.getString(rs.getColumnIndex(DBHelper.SECOND_PLAYER));
            players_score = rs.getString(rs.getColumnIndex(DBHelper.FIRST_PLAYER_SCORE)) + "   :   " + rs.getString(rs.getColumnIndex(DBHelper.SECOND_PLAYER_SCORE));
            mItems.add(new ListViewItem(players_name,players_score));
            if(!rs.isClosed())
                rs.close();

        }


        // initialize and set the list adapter
        setListAdapter(new ListViewAdapter(getActivity(), mItems));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        ListViewItem item = mItems.get(position);

        // do something
        Toast.makeText(getActivity(), item.description, Toast.LENGTH_SHORT).show();
    }
}

