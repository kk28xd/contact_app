//package com.example.test;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
//
//    private ArrayList<Contact> fruitList;
//    private int layoutRes;
//
//    public MyRecyclerAdapter(ArrayList<Contact> fruitList, int layoutRes) {
//        this.fruitList = fruitList;
//        this.layoutRes = layoutRes;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutRes, null, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Contact fruit = fruitList.get(position);
//        holder.fruit_name.setText(fruit.getContact_name());
//        holder.fruit_image.setImageResource(fruit.getId());
//    }
//
//    @Override
//    public int getItemCount() {
//        return fruitList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView fruit_image;
//        TextView fruit_name;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            fruit_image = itemView.findViewById(R.id.fruit_image);
//            fruit_name = itemView.findViewById(R.id.fruit_name);
//        }
//    }
//
//}
