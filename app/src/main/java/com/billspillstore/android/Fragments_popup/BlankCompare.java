package com.billspillstore.android.Fragments_popup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.billspillstore.android.R;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class BlankCompare extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.blank_compare,container,false);
    }
}
