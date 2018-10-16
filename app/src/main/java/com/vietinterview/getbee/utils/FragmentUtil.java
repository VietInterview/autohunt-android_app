package com.vietinterview.getbee.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.constant.AppConstant;


/**
 * Created by LinhNguyen on 5/18/2015.
 */
public class FragmentUtil {

    public static void pushFragment(FragmentActivity activity, Fragment fragmentSource, @NonNull Fragment fragmentDestination, @Nullable Bundle data) {
        showFragment(activity, fragmentSource, fragmentDestination, true, data, null);
    }

    public static void replaceFragment(FragmentActivity activity, @NonNull Fragment fragmentDestination, @Nullable Bundle data) {
        showFragment(activity, null, fragmentDestination, false, data, null);
    }

    public static void showFragment(FragmentActivity activity, Fragment fragmentSource, @NonNull Fragment fragmentDestination, boolean isPushInsteadOfReplace, @Nullable Bundle data, @Nullable String tag) {
        if (activity == null) {
            return;
        }

        if (data != null) {
            fragmentDestination.setArguments(data);
        }
        if (fragmentSource != null)
            fragmentDestination.setTargetFragment(fragmentSource, AppConstant.FRAGMENT_CODE);
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if (isPushInsteadOfReplace) {
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        }
        fragmentTransaction.replace(R.id.container, fragmentDestination, tag);
        if (isPushInsteadOfReplace) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    public static void popBackStack(Fragment fragment) {
        final FragmentManager fm = fragment.getFragmentManager();
        final int backStackCount = fm.getBackStackEntryCount();
        if (backStackCount > 0) {
            fragment.getFragmentManager().popBackStack();
        } else {
            fragment.getActivity().onBackPressed();
        }
    }

    public static void popEntireFragmentBackStack(Fragment fragment) {
        final int backStackCount = fragment.getFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            fragment.getFragmentManager().popBackStackImmediate();
        }
    }
}
