package com.xgt.demo_networktecnologies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.xgt.demo_networktecnologies.databinding.FragmentTecnologyAddBinding
import com.xgt.demo_networktecnologies.databinding.FragmentTecnologyDetailBinding
import com.xgt.demo_networktecnologies.network.TecnologyApi
import com.xgt.demo_networktecnologies.network.request.TecnologyRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TecnologyAddFragment : Fragment() {

    private var _binding: FragmentTecnologyAddBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTecnologyAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddTecnology.setOnClickListener {
            if (validateFields()) {
                sendTechnologyToServer()
            }
        }
    }

    private fun validateFields(): Boolean {
//        binding.etTechName.error = null
        val techName = binding.tvNameTech.text.toString()
        if (techName.isEmpty()) {
//            binding.etTechName.error = "TechName is empty"
            showError("TechName is empty")
            return false
        }

        val techDescription = binding.tvTechDescroption.text.toString()
        if (techDescription.isEmpty()) {
            showError("TechDescription is empty")
            return false
        }
        return true
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun sendTechnologyToServer() {
        val techName = binding.tvNameTech.text.toString()
        val techDescription = binding.tvTechDescroption.text.toString()
        val techImageUrl = binding.tvImageUrl.text.toString()
        val techRequest = TecnologyRequest(techName, techDescription, techImageUrl)

        TecnologyApi.service.createTechnology(techRequest).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Añadido correctamente", Toast.LENGTH_SHORT).show()
                    resetScreen()
                } else {
                    showError("Error en la respuesta")
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("requestData", "error", t)
                showError("Error en la conexión")
            }
        })

    }

    private fun resetScreen() {
        binding.tvNameTech.text = null
        binding.tvTechDescroption.text = null
        binding.tvImageUrl.text = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}