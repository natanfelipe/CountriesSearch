package com.br.natanfelipe.teste;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by natanbrito on 25/04/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> implements Filterable {

    ArrayList<Country> countryArrayList;
    ArrayList<Country> mFilteredList;
    boolean searchEnd = false;



    public ItemAdapter(ArrayList<Country> countryArrayList) {
        this.countryArrayList = countryArrayList;
        mFilteredList = countryArrayList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planet_layout,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(mFilteredList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String text = charSequence.toString();
                if(text.isEmpty())
                    mFilteredList = countryArrayList;
                else {
                    mFilteredList = new ArrayList<>();
                    for(Country c: countryArrayList){
                        String country = c.getName().toLowerCase();


                        boolean permuted = isPermuted(country,text.toLowerCase());
                        boolean typo = isTypo(country,text.toLowerCase());
                            if (permuted || typo) {
                                mFilteredList.add(c);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = mFilteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Country>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static boolean isPermuted(String word1, String word2){

        boolean result = true;

        if(word1.isEmpty() || word2.isEmpty())
            return false;

        if(word1.length() != word2.length())
            return false;

        if(word1.charAt(0) != word2.charAt(0))
            result = false;

        int twoThirdsCount = (word1.length() * 2)/3;
        int count = 0;
        if(word2.length() > 3){
            for (int i = 0; i < word1.length(); i++){
                if(word1.charAt(i) != word2.charAt(i))
                    count++;
            }

            if(count >= twoThirdsCount)
                result = false;
        }

        return result;
    }


    private boolean isTypo(String word1, String word2) {

        boolean result = true;

        int count = 0;
        int addTypo = word1.length()+1;
        int removeTypo = word1.length()-1;
        int diffLength = word2.length() - word1.length();
        if(word1.length() == word2.length()){
            for(int i = 0; i < word1.length(); i++){
                if(word1.charAt(i) != word2.charAt(i))
                    count++;
            }
            if(count != 1)
                result = false;

        } else {
            if(diffLength == 1 && word2.length() == addTypo){
                for(int i = 0; i < word1.length(); i++){
                        if(!(word1.charAt(i) == 0) && word1.charAt(i) != word2.charAt(i))
                        count++;

                }
                if(count > 1)
                    result = false;

            }
            else if(word2.length() == removeTypo){
                for(int i = 0; i < removeTypo; i++){
                    if(word1.charAt(i) != word2.charAt(i))
                        count++;
                }
                if(count > 1)
                    result = false;
            } else
                result = false;
        }

        return result;

    }

}
