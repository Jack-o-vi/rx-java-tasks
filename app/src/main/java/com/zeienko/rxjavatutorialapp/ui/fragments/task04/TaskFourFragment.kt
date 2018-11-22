package com.zeienko.rxjavatutorialapp.ui.fragments.task04

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zeienko.rxjavatutorialapp.R
import com.zeienko.rxjavatutorialapp.ui.fragments.AbstractTaskFragment
import kotlinx.android.synthetic.main.task1_fragment.*

class TaskFourFragment : AbstractTaskFragment(), TaskFourContract.TaskFourView {
    private var presenter: TaskFourContract.TaskFourPresenter? = null

    companion object {
        val TAG = TaskFourFragment::class.java.simpleName

        fun newInstance(): TaskFourFragment {
            return TaskFourFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflateView(R.layout.task1_fragment, inflater, container)
        Log.d(TAG, "onCreateView")
        setText(TAG)
        presenter = TaskFourPresenter()
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter?.bind(this)
    }

    override fun getFragmentTitle(): CharSequence? {
        return TAG
    }

    override fun onPause() {
        super.onPause()
        presenter?.unbind()
    }

    override fun showToast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }

    override fun setText(string: String?) {
        tvInformation?.text = string
    }
}