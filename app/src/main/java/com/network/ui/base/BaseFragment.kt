package com.network.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

public abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected open lateinit var mTag: String
    protected open var mViewBinding: VB? = null
    protected open var rootView: View? = null

    protected abstract fun getFragmentClassName(): String
    protected abstract fun doInOnCreate(savedInstanceState: Bundle?)
    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    protected abstract fun init()

    private fun initTag() {
        mTag = getFragmentClassName()
        if (mTag.length > 23) {
            mTag = mTag.substring(0, 22) // first 22 chars
        }
    }

    private fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mViewBinding = getViewBinding(inflater, container)
        rootView =  mViewBinding?.root
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTag()
        Log.i(mTag, "onCreate(Bundle savedInstanceState)")

        doInOnCreate(savedInstanceState)
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(mTag, "onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)")
        initViewBinding(inflater, container)
        return rootView
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(mTag, "onViewCreated(@NonNull View view, Bundle savedInstanceState)")

        init()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(mTag, "onActivityCreated(@Nullable Bundle savedInstanceState)")
    }

    override fun onStart() {
        super.onStart()
        Log.i(mTag, "onStart()")
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

    override fun onDestroyView() {
        super.onDestroyView();
        Log.i(mTag, "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(mTag, "onDestroy()")
    }

    override fun onDetach() {
        if (null != mViewBinding) {
            mViewBinding = null
        }
        if (null != rootView) {
            rootView = null
        }
        super.onDetach()
        Log.i(mTag, "onDetach()")
    }
}
