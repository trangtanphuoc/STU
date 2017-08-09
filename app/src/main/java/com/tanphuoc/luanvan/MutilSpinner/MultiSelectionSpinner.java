package com.tanphuoc.luanvan.MutilSpinner;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;

import com.tanphuoc.luanvan.Moudle.Quan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MultiSelectionSpinner extends android.support.v7.widget.AppCompatSpinner implements
        DialogInterface.OnMultiChoiceClickListener {
    String[] _items = null;
    boolean[] mSelection = null;
    Dialog Quandialog;

    ArrayAdapter<String> simple_adapter;

    public MultiSelectionSpinner(Context context) {
        super(context);

        simple_adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(simple_adapter);
    }

    public MultiSelectionSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        simple_adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(simple_adapter);
    }

    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (mSelection != null && which < mSelection.length) {
            mSelection[which] = isChecked;

            simple_adapter.clear();
            simple_adapter.add(buildSelectedItemString());

        } else {
            throw new IllegalArgumentException(
                    "Argument 'which' is out of bounds.");
        }
    }

    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMultiChoiceItems(_items, mSelection, this);
        builder.setTitle(Html.fromHtml("<font color='#FF7F27'>Danh sách quận</font>"));


        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNeutralButton("Xóa chọn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for (int i = 0; i < mSelection.length; i++) {
                    mSelection[i] = false;
                    simple_adapter.clear();
                }
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        //Set negative button background
        nbutton.setBackgroundColor(Color.DKGRAY);
        //Set negative button text color
        nbutton.setTextColor(Color.YELLOW);
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        //Set positive button background
        pbutton.setBackgroundColor(Color.YELLOW);
        //Set positive button text color
        pbutton.setTextColor(Color.MAGENTA);
        Button nebutton = alert.getButton(DialogInterface.BUTTON_NEUTRAL);
        //Set positive button background
        nebutton.setBackgroundColor(Color.YELLOW);
        //Set positive button text color
        nebutton.setTextColor(Color.MAGENTA);
        return true;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        throw new RuntimeException(
                "setAdapter is not supported by MultiSelectSpinner.");
    }

    public void setItems(String[] items) {
        _items = items;
        mSelection = new boolean[_items.length];
        simple_adapter.clear();
        simple_adapter.add(_items[0]);
        Arrays.fill(mSelection, false);
    }

    public void setItems(ArrayList<Quan> items) {
        _items = new String[items.size()];
        for(int i=0; i<items.size();i++){
            _items[i] = items.get(i).toString();
        }
        //_items = items.toArray(new String[items.size()]);

        mSelection = new boolean[_items.length];
        simple_adapter.clear();
        try{
            simple_adapter.add(_items[0]);
            Arrays.fill(mSelection, false);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

//    public void setSelection(String[] selection) {
//        for (String cell : selection) {
//            for (int j = 0; j < _items.length; ++j) {
//                if (_items[j].equals(cell)) {
//                    mSelection[j] = true;
//                }
//            }
//        }
//    }
//
//    public void setSelection(List<String> selection) {
//        for (int i = 0; i < mSelection.length; i++) {
//            mSelection[i] = false;
//        }
//        for (String sel : selection) {
//            for (int j = 0; j < _items.length; ++j) {
//                if (_items[j].equals(sel)) {
//                    mSelection[j] = true;
//                }
//            }
//        }
//        simple_adapter.clear();
//        simple_adapter.add(buildSelectedItemString());
//    }

    public void setSelection(int index) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
        }
        if (index >= 0 && index < mSelection.length) {
            mSelection[index] = true;
        } else {
            throw new IllegalArgumentException("Index " + index
                    + " is out of bounds.");
        }
        simple_adapter.clear();
        simple_adapter.add(buildSelectedItemString());
    }

    public void setSelection(int[] selectedIndicies) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
        }
        for (int index : selectedIndicies) {
            if (index >= 0 && index < mSelection.length) {
                mSelection[index] = true;
            } else {
                throw new IllegalArgumentException("Index " + index
                        + " is out of bounds.");
            }
        }
        simple_adapter.clear();
        simple_adapter.add(buildSelectedItemString());
    }

    public List<String> getSelectedStrings() {
        List<String> selection = new LinkedList<String>();
        for (int i = 0; i < _items.length; ++i) {
            if (mSelection[i]) {
                selection.add(_items[i]);
            }
        }
        return selection;
    }

    public List<Integer> getSelectedIndicies() {
        List<Integer> selection = new LinkedList<Integer>();
        for (int i = 0; i < _items.length; ++i) {
            if (mSelection[i]) {
                selection.add(i+1);
            }
        }
        return selection;
    }

    private String buildSelectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;

        for (int i = 0; i < _items.length; ++i) {
            if (mSelection[i]) {
                if (foundOne) {
                    sb.append(", ");
                }
                foundOne = true;

                sb.append(_items[i]);
            }
        }
        return sb.toString();
    }

    public String getSelectedItemsAsString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;

        for (int i = 0; i < _items.length; ++i) {
            if (mSelection[i]) {
                if (foundOne) {
                    sb.append(", ");
                }
                foundOne = true;
                sb.append(_items[i]);
            }
        }
        return sb.toString();
    }
}