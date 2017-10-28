package project.jakkit.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by KHAMMA on 03/07/2017.
 */

public class MyAdapter extends BaseAdapter{

    private Context objContext;
    private String[] strNumFood, strNameFood, strPriceFood;

    public MyAdapter(Context objContext, String[] strNameFood, String[] strPriceFood, String[] strNumFood){
        this.objContext = objContext;
        this.strNameFood = strNameFood;
        this.strPriceFood = strPriceFood;
        this.strNumFood = strNumFood;
    }   // Constructor


    @Override
    public int getCount() {
        return strNameFood.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.list_view_row, parent, false);

        //Food
        TextView listFood = (TextView) view.findViewById(R.id.txtShowFood);
        listFood.setText(strNameFood[position]);

        //Price
        TextView listPrice = (TextView) view.findViewById(R.id.txtShowPrice);
        listPrice.setText(strPriceFood[position]);

        //NumFood
        TextView listNumFood = (TextView) view.findViewById(R.id.txtShowNumFood);
        listNumFood.setText(strNumFood[position]);

        return view;
    }
}   // Main Class
