package com.network.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected open lateinit var mTag: String
    protected open var mViewBinding: VB? = null
    protected open var rootView: View? = null

    protected abstract fun getActivityClassName(): String
    protected abstract fun doInOnCreate(savedInstanceState: Bundle?)
    protected abstract fun getViewBinding(inflater: LayoutInflater): VB
    protected abstract fun init()

    private fun initTag() {
        mTag = getActivityClassName()
        if (mTag.length > 23) {
            mTag = mTag.substring(0, 22) // first 22 chars
        }
    }

    private fun initViewBinding(inflater: LayoutInflater) {
        mViewBinding = getViewBinding(inflater)
        rootView = mViewBinding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTag()
        Log.i(mTag, "onCreate(Bundle savedInstanceState)")

        doInOnCreate(savedInstanceState)

        initViewBinding(layoutInflater)
        setContentView(rootView)

        init()
    }

    override fun onStart() {
        super.onStart()
        Log.i(mTag, "onStart()")
    }

    override fun onRestart() { /* Only called after onStop() */
        super.onRestart()
        Log.i(mTag, "onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i(mTag, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i(mTag, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(mTag, "onStop()")
    }

    override fun onDestroy() {
        if (null != mViewBinding) {
            mViewBinding = null
        }
        if (null != rootView) {
            rootView = null
        }
        super.onDestroy()
        Log.i(mTag, "onDestroy()")
    }
}