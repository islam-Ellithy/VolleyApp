package lamaatech.com.volleyapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Developer on 2/16/2018.
 */

public class EarthQuakeAdapter extends ArrayAdapter<Data> {

    public EarthQuakeAdapter(Activity context, ArrayList<Data> data){

        super(context, 0, data);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listQuakeView = convertView;
        if (listQuakeView == null) {
            listQuakeView = LayoutInflater.from(getContext()).inflate(R.layout.quake_list, parent, false);
        }

        Data currentData = getItem(position);

        TextView quakemag = (TextView) listQuakeView.findViewById(R.id.mag);
        quakemag.setText(currentData.getQmag() + " ");


        TextView quakeplace = (TextView) listQuakeView.findViewById(R.id.place);
        quakeplace.setText(currentData.getQplace());


        TextView quaketime = (TextView) listQuakeView.findViewById(R.id.time);
        quaketime.setText(currentData.getQtime() + " ");

        return listQuakeView;
    }
}
