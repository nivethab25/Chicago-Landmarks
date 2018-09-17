package com.nivetha.cs478.chicagolandmarks;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class LandmarksInfoActivity extends AppCompatActivity implements LandmarksListFragment.ListSelectionListener {

    // String arrays to hold landmarks and webpage URLs
    public static String[] mLandmarksArray;
    public static String[] mWebpageArray;

    // Creating an instance of WebpageFragment to be used in the layout
    private final WebpageFragment mWebpageFragment = new WebpageFragment();

    // Framelayouts for the LandmarksListFragment and WebpageFragment
    private FrameLayout mLandmarkFrameLayout, mWebpagesFrameLayout;

    // Declaring FragmentManager to handle fragment transactions
    private FragmentManager mFragmentManager;

    // Layout Paramters to be used in the layout
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

    // Activity Name for logging
    private static final String TAG = "LandmarksInfoActivity";

    // Name of the Broadcast Receiver action that will receive the broadcast intent sent by this activity
    private static final String PIC_GALLERY_INTENT =
            "com.nivetha.cs478.PictureGallery.START_GALLERY";

    // Dangerous permission defined by the PictureGallery (A2) App
    private static final String ACCESS_PERMISSION =
            "com.nivetha.cs478.ACCESS_GALLERY";

    //  Dangerous permission request code
    private final int ACCESS_PERMISSIONS_REQUEST = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");

        super.onCreate(savedInstanceState);

        // Get the string arrays with landmarks and web URLs
        mLandmarksArray = getResources().getStringArray(R.array.Landmarks);
        mWebpageArray = getResources().getStringArray(R.array.Webpages);

        // Set the layout for the LandmarksInfo App
        setContentView(R.layout.activity_landmarks_info);

        // Get references to the LandmarksListFragment and WebpageFragment
        mLandmarkFrameLayout = (FrameLayout) findViewById(R.id.landmark_fragment_container);
        mWebpagesFrameLayout = (FrameLayout) findViewById(R.id.webpage_fragment_container);

        // Get a reference to the FragmentManager
        mFragmentManager = getFragmentManager();

        // Add the ListFragment to the landmark_fragment_container in the layout
        mFragmentManager.beginTransaction().replace(R.id.landmark_fragment_container, new LandmarksListFragment()).addToBackStack(null).commit();

        // Add a OnBackStackChangedListener to reset the layout when the BackStack changes
        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                setLayout();
            }
        });

        // Call the onListSelection() to display the webpage in the WebpageFragment
        if (mWebpageFragment.mCurrIdx > -1) {
            onListSelection(mWebpageFragment.mCurrIdx);
        }

        // Setting the Action Bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void setLayout() {

        // Determine whether the WebpageFragment has been added
        if (!mWebpageFragment.isAdded()) {

            // Make the LandmarksListFragment occupy the entire layout
            mLandmarkFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            mWebpagesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
        } else {

            int orient = getResources().getConfiguration().orientation;

            // For Landscape orientation
            if (orient == 2) {

                // Make the LandmarksList Layout take 1/3 of the layout's width
                mLandmarkFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));

                // Make the Webpages Layout take 2/3 of the layout's width
                mWebpagesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 2f));
            }

            // For Portrait orientation
            else if (orient == 1) {

                // Make the Webpages Layout occupy the entire width
                mLandmarkFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
                mWebpagesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));

            }

        }
    }


    // Called when the user selects an item in the LandmarksListFragment
    @Override
    public void onListSelection(int index) {


        // If the WebpageFragment has not been added, add it now
        if (!mWebpageFragment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the WebpageFragment to the layout
            fragmentTransaction.replace(R.id.webpage_fragment_container,
                    mWebpageFragment, "webPage");

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }

        // Tell the WebpageFragment to show the landmark url at position index
        mWebpageFragment.showLandmarkAtIndex(index);

    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar
        getMenuInflater().inflate(R.menu.menu_landmarks_info, menu);
        return true;
    }

    // Handle action bar item clicks here
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            // Exit the ChicagoLandmarks App
            case R.id.exit:
                finish();
                System.exit(0);
                return true;

            // Launch Picture Gallery
            case R.id.pic_gallery:

                // Check if the ChicagoLandmarks App has the ACCESS_PERMISSION
                int nivisPermission = ContextCompat.checkSelfPermission(this, ACCESS_PERMISSION);

                // If ACCESS_PERMISSION has been already granted by the user
                if (PackageManager.PERMISSION_GRANTED == nivisPermission) {

                    // Send broadcast intent to start the PictureGallery App
                    Intent intent = new Intent(PIC_GALLERY_INTENT);
                    sendBroadcast(intent);
                }

                // Request ACCESS_PERMISSION from the user at run-time
                else {
                    ActivityCompat.requestPermissions(this, new String[]{ACCESS_PERMISSION}, ACCESS_PERMISSIONS_REQUEST);
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ACCESS_PERMISSIONS_REQUEST: {
                // If ACCESS_PERMISSION has been granted by the user
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Send broadcast intent to start the PictureGallery App
                    Intent intent = new Intent(PIC_GALLERY_INTENT);
                    sendBroadcast(intent);
                }
            }
        }
    }

    // To handle soft back-button press in the ChicagoLandmarks App
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
