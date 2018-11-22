package com.zeienko.rxjavatutorialapp.ui.activity

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.zeienko.rxjavatutorialapp.ui.fragments.task01.TaskOneFragment
import com.zeienko.rxjavatutorialapp.ui.fragments.task02.TaskTwoFragment
import com.zeienko.rxjavatutorialapp.ui.fragments.task03.TaskThreeFragment
import com.zeienko.rxjavatutorialapp.ui.fragments.task04.TaskFourFragment
import com.zeienko.rxjavatutorialapp.ui.fragments.task05.TaskFiveFragment
import com.zeienko.rxjavatutorialapp.ui.fragments.task07.TaskSevenFragment
import com.zeienko.rxjavatutorialapp.ui.fragments.task08.TaskEightFragment
import com.zeienko.rxjavatutorialapp.ui.fragments.task09.TaskNineFragment
import com.zeienko.rxjavatutorialapp.ui.fragments.task10.TaskTenFragment
import com.zeienko.rxjavatutorialapp.ui.fragments.task11.TaskElevenFragment

class TaskActivity : AbstractTasksActivity() {

    override fun createFragment(): Fragment {
        val fragmentTask = intent.getStringExtra(TASK_FRAGMENT_KEY)
        return when (fragmentTask) {
            activitiesList[0] -> TaskOneFragment.newInstance()
            activitiesList[1] -> TaskTwoFragment.newInstance()
            activitiesList[2] -> TaskThreeFragment.newInstance()
            activitiesList[3] -> TaskFourFragment.newInstance()
            activitiesList[4] -> TaskFiveFragment.newInstance()
            activitiesList[5] -> TaskSevenFragment.newInstance()
            activitiesList[6] -> TaskEightFragment.newInstance()
            activitiesList[7] -> TaskNineFragment.newInstance()
            activitiesList[8] -> TaskTenFragment.newInstance()
            activitiesList[9] -> TaskElevenFragment.newInstance()
            else -> TaskOneFragment.newInstance()
        }
    }

    companion object {
        private const val TASK_FRAGMENT_KEY = "fragment_key"
        val activitiesList = listOf(
            "TaskOneFragment",
            "TaskTwoFragment",
            "TaskThreeFragment",
            "TaskFourFragment",
            "TaskFiveFragment",
            "TaskSevenFragment",
            "TaskEightFragment",
            "TaskNineFragment",
            "TaskTenFragment",
            "TaskElevenFragment"
        )

        fun newInstance(context: Context, task: String? = null): Intent {
            val intent = Intent(context, TaskActivity::class.java)
            task?.let {
                intent.putExtra(TASK_FRAGMENT_KEY, it)
            }
            return intent
        }
    }

}