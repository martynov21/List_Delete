package com.example.zver.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterItem extends BaseAdapter {

    ArrayList<Item> arrayItems = new ArrayList<Item>();
    Context context;
    private ListView listView;


    AdapterItem(Context context, ArrayList<Item> arrayItems,ListView listView) {
        this.context = context;
        this.listView = listView;
        if (arrayItems != null)
            this.arrayItems = arrayItems;
    }

    @Override
    public int getCount() {
        return arrayItems.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Получение объекта inflater из контекста
        LayoutInflater inflater = LayoutInflater.from(context);
        //Если someView (View из ListView) вдруг оказался равен
        //null тогда мы загружаем его с помошью inflater
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item, parent, false);
        }

        //ImageButton deleteOne = (ImageButton) convertView.findViewById(R.id.add);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        name.setText(arrayItems.get(position).getName());

//        deleteOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                arrayItems.remove(position).getName();
//                notifyDataSetChanged();
//            }
//        });

        convertView.setLongClickable(true);

        if (arrayItems.get(position).isCheckStatus()){
            checkBox.setChecked(true);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    arrayItems.get(position).setCheckStatus(true);
                }
                else if (checkBox.isEnabled()){
                    arrayItems.get(position).setCheckStatus(false);
                }
            }
        });

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        return convertView;
    }
}
