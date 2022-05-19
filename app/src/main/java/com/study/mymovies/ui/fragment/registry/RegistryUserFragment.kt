package com.study.mymovies.ui.fragment.registry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.viewModels
import com.study.mymovies.R
import com.study.mymovies.databinding.FragmentRegistryUserBinding
import com.study.mymovies.ui.common.BaseFragment

class RegistryUserFragment : BaseFragment<FragmentRegistryUserBinding>() {
    private lateinit var resultLaunch: ActivityResultLauncher<Intent>

    val viewModel: RegistryUserViewModel by viewModels()
    override fun initElements() {
        viewModel.apply{

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        /*resultLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data = result.data?.data
                data?.let {
                    //val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, it)
                    bind.imagen.setImageURI(it)
                    viewModel.imageString = uriToBase64(it)
                }
            }
        }*/
    }

    override fun getLayout(): Int = R.layout.fragment_registry_user

    override fun initObservers() {

    }

    override fun initView(){
        bind.apply{
            save.setOnClickListener {
                val name = nombre.text
                val phone = telefono.text
                val email = correo.text
                val address = direccion.text
                //viewModel.saveUser(UserModel())
            }
        }
    }

}