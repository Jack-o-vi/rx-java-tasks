package com.zeienko.rxjavatutorialapp.ui.fragments.task01

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zeienko.rxjavatutorialapp.R
import com.zeienko.rxjavatutorialapp.ui.fragments.AbstractTaskFragment
import kotlinx.android.synthetic.main.task1_fragment.*

class TaskOneFragment : AbstractTaskFragment(), TaskOneContract.TaskOneView {

    private var presenter: TaskOneContract.TaskOnePresenter? = null


    companion object {
        val TAG = TaskOneFragment::class.java.simpleName

        fun newInstance(): TaskOneFragment {
            return TaskOneFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflateView(R.layout.task1_fragment, inflater, container)

        Log.d(TAG, "onCreateView")

        presenter = TaskOnePresenter()
        return view
    }

    override fun getFragmentTitle(): CharSequence? {
        return TAG
    }

    override fun onResume() {
        super.onResume()
        presenter?.bind(this)
    }

    override fun onPause() {
        super.onPause()
        presenter?.unbind()
    }

    override fun showToast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar() {
        progressBar?.let {
            it.visibility = View.VISIBLE
        }
    }

    override fun hideProgressBar() {
        progressBar?.let {
            it.visibility = View.GONE
        }
    }


    override fun setText(string: String?) {
        tvInformation?.text = string
    }
}