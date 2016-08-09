package com.example.cass.wohand.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.cass.wohand.R;
import com.example.cass.wohand.fragment.ContactsFragment;
import com.example.cass.wohand.fragment.HomeFragment;
import com.example.cass.wohand.fragment.SNSFragment;

/**
 * Created by Cass on 2016/8/4.
 */
public class MyFragmentFactory {
    private static Fragment fragment;
    private FragmentManager manager;

    public static Fragment createFragment(int checkedId){

        switch (checkedId){

            case R.id.btn_home://主页
                fragment=new HomeFragment();
                break;

            case R.id.btn_contacts://通讯录
                fragment=new ContactsFragment();
                break;

            case R.id.btn_sns://圈子
                fragment=new SNSFragment();
                break;
        }

        return fragment;
    }
}
