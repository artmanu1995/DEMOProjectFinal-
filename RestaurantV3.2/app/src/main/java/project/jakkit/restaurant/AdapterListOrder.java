package project.jakkit.restaurant;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * Created by KHAMMA on 11/09/2017.
 */

public class AdapterListOrder extends BaseAdapter{

    private Context objContext;
    private String[] strFoodID, strNameFood, strHotLevel, strAmount, strPriceFood, strOpenID;

    public AdapterListOrder(Context objContext, String[] strOpenID, String[] strFoodID, String[] strNameFood, String[] strHotLevel, String[] strAmount, String[] strPriceFood){
        this.objContext = objContext;
        this.strOpenID = strOpenID;
        this.strFoodID = strFoodID;
        this.strNameFood = strNameFood;
        this.strHotLevel = strHotLevel;
        this.strAmount = strAmount;
        this.strPriceFood = strPriceFood;
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
        View view = objLayoutInflater.inflate(R.layout.listview_order_row, parent, false);

        //FoodID
        TextView listFoodID = (TextView) view.findViewById(R.id.txtShowIdFood);
        listFoodID.setText(strFoodID[position]);

        //Food
        TextView listFood = (TextView) view.findViewById(R.id.txtShowFood);
        listFood.setText(strNameFood[position]);

        //HotLevel
        TextView listHot = (TextView) view.findViewById(R.id.txtShowHot);
        listHot.setText(strHotLevel[position]);

        //Amount
        TextView listAmount = (TextView) view.findViewById(R.id.txtShowAmount);
        listAmount.setText(strAmount[position]);

        //Price
        TextView listPrice = (TextView) view.findViewById(R.id.txtShowPrice);
        listPrice.setText(strPriceFood[position]);

        return view;
    }
}
