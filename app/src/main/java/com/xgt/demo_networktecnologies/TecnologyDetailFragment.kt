package com.xgt.demo_networktecnologies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xgt.demo_networktecnologies.databinding.FragmentTecnologyDetailBinding
import com.xgt.demo_networktecnologies.extension.imageUrl
import com.xgt.demo_networktecnologies.model.Tecnology
import com.xgt.demo_networktecnologies.network.TecnologyApi
import com.xgt.demo_networktecnologies.network.TecnologyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TecnologyDetailFragment : Fragment() {
    private val args: TecnologyDetailFragmentArgs by navArgs()
    private var _binding: FragmentTecnologyDetailBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTecnologyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    //    binding.tvNoSeQue.text = args.techId
        args.techId?.let{
            requestData(it)
        } ?: showError("TechId null")
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun requestData(techId: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.160.224:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: TecnologyService = retrofit.create(TecnologyService::class.java)
        service.getTechnologyById(args.techId).enqueue(object : Callback<Tecnology> {
            override fun onResponse(call: Call<Tecnology>, response: Response<Tecnology>) {
                if (response.isSuccessful) {
                    populateUi(response.body())
                } else {
                    showError("Error en la conexión")
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<Tecnology>, t: Throwable) {
                Log.e("requestData", "error", t)
                showError("Error en la conexión")
            }
        })
    }


    private fun populateUi(tecnology: Tecnology?) {
            tecnology?.let {
                binding.tvNoSeQue.text = it.id
                binding.tvName2.text = it.name
                binding.tvDescriptiion.text = it.description
                binding.ivAvatar.imageUrl(it.imageUrl)
                binding.btnDeleteTecnology.setOnClickListener { view ->
                    deleteTechnology(it)
                }
            }?: showError("Error to retrieve technology")
    }


    private fun deleteTechnology(technology: Tecnology) {
            TecnologyApi.service.deleteTechnologyById(technology.id).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        findNavController().popBackStack()
                    } else {
                        showError("Error al borrar la categoria con id: ${technology.id}")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("requestData", "error", t)
                    showError("Error en la conexión al borrar la tecnología")
                }
            })
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}