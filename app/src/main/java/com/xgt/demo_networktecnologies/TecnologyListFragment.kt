package com.xgt.demo_networktecnologies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.xgt.demo_networktecnologies.databinding.FragmentTecnologyListBinding
import com.xgt.demo_networktecnologies.model.Tecnology
import com.xgt.demo_networktecnologies.network.TecnologyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TecnologyListFragment : Fragment() {

    private var _binding: FragmentTecnologyListBinding? = null
    private val binding
        get() = _binding!!


    private val adapter = TecnologyAdapter({
        val action = TecnologyListFragmentDirections.actionTecnologyListFragmentToTecnologyDetailFragment(it.id)
        findNavController().navigate(action)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTecnologyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configUi()

        requestData()

    }

    private fun requestData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.1.204.118:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: TecnologyService = retrofit.create(TecnologyService::class.java)
        service.getTechnologies().enqueue(object : Callback<List<Tecnology>> {
            override fun onResponse(
                call: Call<List<Tecnology>>,
                response: Response<List<Tecnology>>
            ) {
                if (response.isSuccessful) {
                    adapter.submitList(response.body())
                } else {
                    Toast.makeText(context, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<List<Tecnology>>, t: Throwable) {
                Toast.makeText(context, "Error en la conexión", Toast.LENGTH_SHORT).show()
                Log.e("requestData", "error", t)
            }
        })
    }


    private fun configUi() {
        binding.fabAddTech.setOnClickListener{
            val action = TecnologyListFragmentDirections.actionTecnologyListFragmentToTecnologyAddFragment()
            findNavController().navigate(action)
        }

        binding.rvTecnologies.layoutManager = GridLayoutManager(context, 2)
        binding.rvTecnologies.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}