package com.example.gymapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymapp.data.gymContract;
import com.example.gymapp.data.gymDbHelper;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter. CustomView >    {
    private ArrayList<DataClass>  mDataset;
    public gymDbHelper mDbHelper;
    public CustomAdapter(ArrayList<DataClass> mData, ForecastAdapterOnClickHandler mClickHandler) {
        mDataset=mData;
        this.mClickHandler = mClickHandler;
    }
    public CustomAdapter(ForecastAdapterOnClickHandler clickHandler){
        mClickHandler = clickHandler;
    }

    private final ForecastAdapterOnClickHandler mClickHandler;
    public interface ForecastAdapterOnClickHandler {
        void onClick(String weatherForDay);
    }


    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_exercise;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new CustomView (view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {
        String check="IT NOT";

       DataClass fill_in=mDataset.get(position);
        if (fill_in.get_vegan()==1){
            check="IT IS";
        }
        holder.textView.setText(fill_in.get_name());
        holder.textView3.setText("Calories :"+fill_in.get_calories());

        holder.textView2.setText("Protein :"+fill_in.get_protein());
        holder.textView5.setText("Vegan :"+check);

        if(fill_in.get_vegan()==0 && fill_in.get_protein()==0){
            holder.textView2.setVisibility(View.GONE);
            holder.textView5.setVisibility(View.GONE);
            holder.textView3.setText("Description :"+fill_in.get_calories());
        }

    }

    @Override
    public int getItemCount() {
        return mDataset.size();

    }

    public  class CustomView extends  RecyclerView.ViewHolder  implements View.OnClickListener {

        public TextView textView;
        public TextView textView2;
        public TextView textView3;
        public  TextView textView4;
        public TextView textView5;


        public CustomView(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.show_list);
            textView2=itemView.findViewById(R.id.show_detials);
            textView3=itemView.findViewById(R.id.show_details2);
            textView4=itemView.findViewById(R.id.show_details3);
            textView5=itemView.findViewById(R.id.show_clicked);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            DataClass we_it=mDataset.get(adapterPosition);
            String weatherForDay = we_it.get_name();
            mClickHandler.onClick(weatherForDay);
        }
    }
    public void setWeatherData(ArrayList<DataClass> weatherData) {
        final String TAG = "Entered";
        Log.i(TAG,"-----------------------------ENTERED THE SET_WEAThER FUNCTION-----------------------------");
        mDataset = weatherData;
        notifyDataSetChanged();
    }
}
