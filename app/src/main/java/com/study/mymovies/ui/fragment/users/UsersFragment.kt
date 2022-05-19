package com.study.mymovies.ui.fragment.users

import androidx.fragment.app.viewModels
import com.study.mymovies.R
import com.study.mymovies.databinding.FragmentUsersBinding
import com.study.mymovies.ui.common.BaseFragment
import com.study.mymovies.ui.fragment.users.adapter.UserAdapter
import com.study.mymovies.ui.utils.ResponseDialog


class UsersFragment: BaseFragment<FragmentUsersBinding>() {

    val viewModel: UserViewModel by viewModels()
    override fun initElements() {
        viewModel.apply{
            getUsers{ list ->
                if(list.isEmpty()){
                    ResponseDialog.showDialog(requireActivity(), "No se encontraron resultados")
                }
                bind.recycler.adapter = UserAdapter(list)
            }
        }
    }

    override fun getLayout(): Int = R.layout.fragment_users

    override fun initObservers() {

    }

    override fun initView(){

    }

}