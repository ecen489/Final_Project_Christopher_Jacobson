package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends ListFragment {

    private ExpenseListener expenseListener;
    public ListFrag() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this.getActivity(),R.layout.mytextview,getResources().getStringArray(R.array.expenses)));
    }

    public interface ExpenseListener{
        public void onExpenseSelected(int index);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            expenseListener = (ExpenseListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement the interface called expenseListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        expenseListener.onExpenseSelected(position);
    }
}
