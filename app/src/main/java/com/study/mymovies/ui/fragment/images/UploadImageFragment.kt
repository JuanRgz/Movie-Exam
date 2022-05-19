package com.study.mymovies.ui.fragment.images

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.study.mymovies.R
import com.study.mymovies.databinding.FragmentUploadImageBinding
import com.study.mymovies.ui.common.BaseFragment


class UploadImageFragment : BaseFragment<FragmentUploadImageBinding>() {

    val viewModel: UploadImageViewModel by viewModels()

    private lateinit var resultLaunch: ActivityResultLauncher<Intent>
    override fun onAttach(context: Context) {
        super.onAttach(context)
        resultLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data = result.data?.data
                data?.let { uri ->
                    bind.loading.visibility = View.VISIBLE
                    viewModel.saveImage(uri, requireActivity()){
                        bind.loading.visibility = View.GONE
                    }
                }
            }
        }
    }
    override fun initElements() {
        viewModel.apply{

        }
    }

    override fun getLayout(): Int = R.layout.fragment_upload_image

    override fun initObservers() {

    }

    override fun initView() {
        bind.apply {
            uploadImage.setOnClickListener {
                val i = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                resultLaunch.launch(i)
            }
        }
    }
}