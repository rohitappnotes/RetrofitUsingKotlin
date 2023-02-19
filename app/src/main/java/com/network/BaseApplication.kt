package com.network

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.multidex.MultiDex

class BaseApplication : Application() {

    companion object {
        val TAG: String = BaseApplication::class.java.simpleName
        lateinit var application: BaseApplication
        var isInBackground: Boolean = true
    }

    /**
     * Called when the application is starting, before any other application objects have been created.
     */
    override fun onCreate() {
        super.onCreate()
        application = this
        registerActivityLifecycleCallbacks(MyActivityLifecycleCallbacks())
    }

    /**
     * Called by the system when the device configuration changes while your component is running.
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e(TAG, "LANDSCAPE")
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.e(TAG, "PORTRAIT")
        }
    }

    /**
     * This is called when the overall system is running low on memory, and would like actively
     * running processes to tighten their belts.
     */
    override fun onLowMemory() {
        super.onLowMemory()
        Log.e(TAG, "onLowMemory()");
    }

    /*
     * Only for testing, not called in production. This method is for use in emulated process
     * environments. It will never be called on a production Android device.
     */
    override fun onTerminate() {
        super.onTerminate()
        Log.e(TAG, "onTerminate()");
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.e(TAG, "attachBaseContext(Context base)");
        try {
            MultiDex.install(base)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private class MyActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

        companion object {
            private val TAG: String = MyActivityLifecycleCallbacks::class.java.simpleName
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onActivityCreated at ${activity.localClassName}")
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onActivityStarted at ${activity.localClassName}")
            /* app moved to foreground */
            isInBackground = false
        }

        override fun onActivityResumed(activity: Activity) {
            Log.d(TAG, "onActivityResumed at ${activity.localClassName}")
        }

        override fun onActivityPaused(activity: Activity) {
            Log.d(TAG, "onActivityPaused at ${activity.localClassName}")
        }

        override fun onActivityStopped(activity: Activity) {
            Log.d(TAG, "onActivityStopped at ${activity.localClassName}")
            /* app moved to background */
            isInBackground = true
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            Log.d(TAG, "onActivitySaveInstanceState at ${activity.localClassName}")
        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onActivityDestroyed at ${activity.localClassName}")
        }
    }
}
