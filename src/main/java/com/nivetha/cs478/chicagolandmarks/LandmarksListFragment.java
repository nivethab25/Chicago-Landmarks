package com.nivetha.cs478.chicagolandmarks;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// Fragment to display the list of Chicago Landmarks
public class LandmarksListFragment extends ListFragment {

    // String holding the Name of the Fragment for Logging
    private static final String TAG = "LandmarksListFragment";

    // Declaring the ListSelectionListener
    private ListSelectionListener mListener = null;

    // Callback interface that allows this fragment to notify the LandmarksInfoActivity when user clicks on a ListItem
    public interface ListSelectionListener {
        void onListSelection(int index);
    }

    // Called when user selects an item from the List
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        // Indicates the selected item has been checked
        l.setItemChecked(pos, true);

        // Inform the LandmarksInfoActivity that the item in position pos has been selected
        mListener.onListSelection(pos);


    }

    @Override
    public void onAttach(Context activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);

        try {

            // Set the ListSelectionListener for communicating with the LandmarksInfoActivity
            mListener = (ListSelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ListSelectionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);

        // Retain this Fragment across Activity reconfigurations
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {

        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedState);

        setRetainInstance(true);

        // Set the list choice mode to allow only selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Set the list adapter for the ListView
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.landmarkslist_item, LandmarksInfoActivity.mLandmarksArray));

        // Update the selection in the ListView
        if (WebpageFragment.mCurrIdx != -1) {
            getListView().setItemChecked(WebpageFragment.mCurrIdx, true);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(TAG, getClass().getSimpleName() + ":onConfigurationChanged()");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }


}
