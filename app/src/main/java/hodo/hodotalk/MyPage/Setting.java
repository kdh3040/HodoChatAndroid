package hodo.hodotalk.MyPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hodo.hodotalk.R;

/**
 * Created by boram on 2017-07-12.
 */

public class Setting extends Fragment {

    public static Setting newInstance() {
        Setting fragment = new Setting();
        return fragment;
    }

    public Setting() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.mypage_setting, container, false);
    }

}