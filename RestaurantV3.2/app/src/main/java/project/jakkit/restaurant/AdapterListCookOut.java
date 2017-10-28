package project.jakkit.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by AloneBOY on 13/09/2017.
 */
public class AdapterListCookOut extends BaseAdapter {
    private Context objContext;
    private String[] strTableID, strNameFood, strHotLevel, strAmount, strOpenID;

    public AdapterListCookOut(Context objContext, String[] strOpenID, String[] strTableID, String[] strNameFood, String[] strHotLevel, String[] strAmount){
        this.objContext = objContext;
        this.strOpenID = strOpenID;
        this.strTableID = strTableID;
        this.strNameFood = strNameFood;
        this.strHotLevel = strHotLevel;
        this.strAmount = strAmount;
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
        View view = objLayoutInflater.inflate(R.layout.listview_cook_low2, parent, false);

        TextView listTableID = (TextView) view.findViewById(R.id.txtShowTableIDO);
        listTableID.setText(strTableID[position]);

        TextView listFood = (TextView) view.findViewById(R.id.txtShowFoodNameO);
        listFood.setText(strNameFood[position]);

        TextView listHot = (TextView) view.findViewById(R.id.txtShowHotO);
        listHot.setText(strHotLevel[position]);

        TextView listAmount = (TextView) view.findViewById(R.id.txtShowAmountO);
        listAmount.setText(strAmount[position]);

        return view;
    }
}
