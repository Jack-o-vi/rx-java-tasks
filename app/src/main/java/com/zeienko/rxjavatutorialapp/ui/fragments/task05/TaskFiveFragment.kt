package com.zeienko.rxjavatutorialapp.ui.fragments.task05

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zeienko.rxjavatutorialapp.R
import com.zeienko.rxjavatutorialapp.ui.fragments.AbstractTaskFragment

class TaskFiveFragment : AbstractTaskFragment(), TaskFiveContract.TaskFiveView {
    override fun showToast(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }

    var presenter: TaskFiveContract.TaskFivePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = TaskFivePresenter()
        return inflateView(R.layout.task1_fragment, inflater = inflater, container = container)
    }

    override fun getFragmentTitle(): CharSequence? {
        return TAG
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter?.bind(this)
    }

    override fun onPause() {
        super.onPause()
        presenter?.unbind()
    }

    companion object {
        fun newInstance(): Fragment {
            return TaskFiveFragment()
        }

        val TAG = TaskFiveFragment::class.java.simpleName
    }
}