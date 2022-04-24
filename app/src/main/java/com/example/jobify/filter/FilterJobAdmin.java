package com.example.jobify.filter;

import android.widget.Filter;

import com.example.jobify.adapter.JobAdapter;
import com.example.jobify.adapter.JobAdminAdapter;
import com.example.jobify.model.JobModel;

import java.util.ArrayList;

public class FilterJobAdmin extends Filter {

    //arraylist in which we want to search
    ArrayList<JobModel> filterList;

    //adapter in which filter need to implement
    JobAdminAdapter jobAdminAdapter;

    //constructor


    public FilterJobAdmin(ArrayList<JobModel> filterList, JobAdminAdapter jobAdminAdapter) {
        this.filterList = filterList;
        this.jobAdminAdapter = jobAdminAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results =new FilterResults();
        //value should not be null and empty
        if (constraint!= null && constraint.length()>0){
            //change to uppercase or lowercase to avoid case sensitive
            constraint = constraint.toString().toUpperCase();
            ArrayList<JobModel> filteredModels = new ArrayList<>();
            for (int i=0; i<filterList.size();i++){

                //validation
                if (filterList.get(i).getJobTitle().toUpperCase().contains(constraint)){

                    //add to filtered list
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count =filteredModels.size();
            results.values=filteredModels;

        }
        else{
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {


        //apply filter change
        jobAdminAdapter.jobAdminArrayList =(ArrayList<JobModel>)results.values;

        //  notify changes
        jobAdminAdapter.notifyDataSetChanged();
    }
}
