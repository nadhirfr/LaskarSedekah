package com.laskarsedekah.laskarsedekah.NavMenu;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laskarsedekah.laskarsedekah.R;

/**
 * Created by FACHRUL on 5/12/2016.
 */
public class CaraSedekah extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    //ini kebawah merupakan fragment dari tab
    public static class PlaceholderFragment extends Fragment {
        private int mPage;
        private int[] layoutTab = {R.layout.fragment_online_trans, R.layout.fragment_offline_trans};

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            mPage = getArguments().getInt(ARG_SECTION_NUMBER);

//            switch (mPage){
//                case 1:
//
//            }
            View rootView = inflater.inflate(layoutTab[mPage - 1], container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SEDEKAH ONLINE";
                case 1:
                    return "SEDEKAH LANGSUNG";

            }
            return null;
        }
    }

    //ini function untuk mengeset custom content list view
    public static class Level {
        public int icon;
        public String title;
        public String title2;
        public String title3;

        public Level() {
            super();
        }

        public Level(String title, String title2, String title3, int icon) {
            super();
            this.icon = icon;
            this.title = title;
            this.title2 = title2;
            this.title3 = title3;
        }

    }
}


