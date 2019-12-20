package com.example.coding_hackaton_guwahati;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPager extends FragmentStatePagerAdapter {

    private UserHomeFragment tab0 = null;

    //integer to count number of tabs
    int tabCount;
//    private String[] tabTitles = new String[]{"Tab1", "Tab2", "Tab3"};

    //Constructor to the class
    public ViewPager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                if(tab0 == null){
                    tab0 = new UserHomeFragment();
                }
                return tab0;

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }

}
