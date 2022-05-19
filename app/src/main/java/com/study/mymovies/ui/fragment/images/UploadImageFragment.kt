package com.study.mymovies.ui.fragment.images

import androidx.fragment.app.viewModels
import com.study.movies.ui.fragment.images.UploadImageViewModel
import com.study.mymovies.R
import com.study.mymovies.databinding.FragmentUploadImageBinding
import com.study.mymovies.ui.common.BaseFragment

class UploadImageFragment : BaseFragment<FragmentUploadImageBinding>() {

    val viewModel: UploadImageViewModel by viewModels()
    override fun initElements() {
        viewModel.apply{
            // Adding new location
        }
    }

    override fun getLayout(): Int = R.layout.fragment_upload_image

    override fun initObservers() {

    }

    override fun initView() {
        bind.apply {

        }
    }
}