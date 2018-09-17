package com.nivetha.cs478.chicagolandmarks;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * Created by nivetha on 10/26/17.
 */

public class WebpageFragment extends Fragment {

    // Fragment name for Logging
    private static final String TAG = "WebpageFragment";

    // Declaring WebView to display the landmark WebPage
    private WebView mwebView = null;

    // A public static field to hold the current selection of Web URL
    public static int mCurrIdx = -1;

    // Field to hold the WebPages Array length
    private int mWebpagesArrLen = 0;

    // Show the webpage of the landmark at position newIndex
    void showLandmarkAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mWebpagesArrLen)
            return;
        mCurrIdx = newIndex;
        mwebView.loadUrl(LandmarksInfoActivity.mWebpageArray[mCurrIdx]);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
    }

    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

        // Inflate the layout defined in webpage_fragment.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        return inflater.inflate(R.layout.webpage_fragment,
                container, false);
    }

    // Set up some information about the mwebView WebView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);

        // Binding the Webview from the layout
        mwebView = (WebView) getActivity().findViewById(R.id.webpageView);

        // Setting the WebPages Array length
        mWebpagesArrLen = LandmarksInfoActivity.mWebpageArray.length;

        // If a WebPage has been selected, load the URL into the WebView
        if (mCurrIdx != -1) {
            showLandmarkAtIndex(mCurrIdx);
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
