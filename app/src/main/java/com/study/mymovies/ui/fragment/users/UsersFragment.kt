package com.study.mymovies.ui.fragment.users

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.viewModels
import com.study.mymovies.R
import com.study.mymovies.databinding.FragmentUsersBinding
import com.study.mymovies.ui.common.BaseFragment
import com.study.mymovies.ui.utils.ResponseDialog


class UsersFragment: BaseFragment<FragmentUsersBinding>() {
    private lateinit var resultLaunch: ActivityResultLauncher<Intent>

    val viewModel: UserViewModel by viewModels()
    override fun initElements() {
        viewModel.apply{

        }
    }

    override fun getLayout(): Int = R.layout.fragment_users

    override fun initObservers() {

    }

    override fun initView(){
        bind.apply{

        }
    }

}