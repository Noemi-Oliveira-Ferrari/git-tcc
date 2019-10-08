package br.net.daumhelp.menu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsAdapter extends FragmentPagerAdapter {

    private Context context;

    public TabsAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if(position == 0){
            f = MainFragment.newInstance("Texto 1");
        }else if(position == 1){
            f = MainFragment.newInstance("Texto 2");
        }else if(position == 2){
            f = MainFragment.newInstance("Texto 3");
        }
        return f;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return "Texto 1";
        else if (position == 1)
            return "Texto 2";
        else
            return "Texto 3" ;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
