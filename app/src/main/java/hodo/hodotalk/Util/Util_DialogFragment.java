package hodo.hodotalk.Util;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hodo.hodotalk.R;

/**
 * Created by boram on 2017-07-18.
 */

public class Util_DialogFragment extends DialogFragment{

    private Fragment fragment;

    public Util_DialogFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        Bundle args = getArguments();
        String value = args.getString("key");

        /*
         * DialogFragment를 종료시키려면? 물론 다이얼로그 바깥쪽을 터치하면 되지만
         * 종료하기 버튼으로도 종료시킬 수 있어야겠죠?
         */
        // 먼저 부모 프래그먼트를 받아옵니다.
        //findFragmentByTag안의 문자열 값은 Fragment1.java에서 있던 문자열과 같아야합니다.
        //dialog.show(getActivity().getSupportFragmentManager(),"tag");
        //fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        // 아래 코드는 버튼 이벤트 안에 넣어야겠죠?
      /*  if (fragment != null) {
            DialogFragment dialogFragment = (DialogFragment) fragment;
            dialogFragment.dismiss();
        }*/
        return view;
    }
}


