package com.numus.budgee;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment{


    ConstraintLayout constraintLayout;
    Context mContext;
    View view;
    ConstraintSet set;
    int pressed = 0;

    public ExpensesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_expenses, container, false);
        mContext = view.getContext();
        constraintLayout = view.findViewById(R.id.myLayout);
        Button button = view.findViewById(R.id.btn);
        button.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awesomeButtonClicked();
            }
        });

        return view;
    }

    private void awesomeButtonClicked() {
        Log.i("Fucking","pressed");
        create_cv();
    }


    private void create_cv(){
        Log.i("Button","pressed");

        CardView card = new CardView(mContext);

        // Set the CardView layoutParams
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        card.setLayoutParams(params);

        // Set CardView corner radius
        card.setRadius(25);

        // Set cardView content padding
        card.setContentPadding(10, 10, 10, 10);

        // Set a background color for CardView
        card.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        // Set CardView elevation
        card.setCardElevation(5);


        // Initialize a new TextView to put in CardView
        TextView tv = new TextView(mContext);
        tv.setLayoutParams(params);
        tv.setText("CardView\nProgrammatically ");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
        tv.setTextColor(getResources().getColor(R.color.colorPrimary));

        // Put the TextView in CardView
        card.addView(tv);

        //set.connect(view.findViewById(R.id.notifications_cv).getId(), ConstraintSet.BOTTOM, view.findViewById(R.id.notifications_cv).getId(), ConstraintSet.RIGHT, 70);

        // Finally, add the CardView in root layout
        constraintLayout.addView(card);


    }
}


