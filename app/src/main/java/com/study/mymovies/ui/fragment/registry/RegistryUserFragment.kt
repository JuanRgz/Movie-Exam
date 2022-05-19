package com.study.mymovies.ui.fragment.registry

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.study.mymovies.R
import com.study.mymovies.core.App
import com.study.mymovies.databinding.FragmentRegistryUserBinding
import com.study.mymovies.ui.common.BaseFragment
import com.study.mymovies.ui.fragment.registry.data.model.UserModel
import com.study.mymovies.ui.utils.ResponseDialog
import com.study.mymovies.ui.utils.uriToBase64

class RegistryUserFragment : BaseFragment<FragmentRegistryUserBinding>() {
    private lateinit var resultLaunch: ActivityResultLauncher<Intent>

    val viewModel: RegistryUserViewModel by viewModels()
    override fun initElements() {
        viewModel.apply{

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        resultLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data = result.data?.data
                data?.let {
                    bind.imagen.setImageURI(it)
                    viewModel.imageString = uriToBase64(it)
                }
            }
        }
    }

    override fun getLayout(): Int = R.layout.fragment_registry_user

    override fun initObservers() {

    }

    override fun initView(){
        bind.apply{
            save.setOnClickListener {
                val name = nombre.text.toString()
                val phone = telefono.text.toString()
                val email = correo.text.toString()
                val address = direccion.text.toString()
                val imagen = viewModel.imageString
                viewModel.apply {
                    val user = UserModel(
                        fullName = name,
                        phone = phone,
                        email = email,
                        address = address,
                        image = imagen
                    )
                    if(validateUser(user)){
                        viewModel.saveUser(user)
                        cleanFields()
                        ResponseDialog.showDialog(requireActivity(), "Usuario guardado exitosamente", success = true)
                    } else{
                        ResponseDialog.showDialog(requireActivity(), "asegurate de llenar los campos de forma correcta")
                    }
                }
            }
            imagen.setOnClickListener {
                val i = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                resultLaunch.launch(i)
            }
        }
    }

    private fun cleanFields(){
        viewModel.imageString = ""
        bind.apply {
            nombre.requestFocus()
            nombre.setText("")
            telefono.setText("")
            correo.setText("")
            direccion.setText("")
            imagen.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.default_user))
        }
    }

}