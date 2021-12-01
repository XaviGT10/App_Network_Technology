package com.xgt.demo_networktecnologies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.xgt.demo_networktecnologies.databinding.FragmentTecnologyDetailBinding
import com.xgt.demo_networktecnologies.network.TecnologyService
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
        binding.tvNoSeQue.text = args.techId
        args.techId?.let{
            requestData(it)
        } ?: showError()
        //Toast.makeText(context, "TechId: ${args.techId}", Toast.LENGTH_SHORT).show()
        //if (args.techId != null){
      //    requestData(args.techId!!)
       // } else {
         //   showError()
       // }

    }

    private fun showError() {
        Toast.makeText(context, "TechId nulo", Toast.LENGTH_SHORT).show()
    }

    private fun requestData(techId: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.1.204.115:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: TecnologyService = retrofit.create(TecnologyService::class.java)
        service.getTecnologyById(args.techId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}