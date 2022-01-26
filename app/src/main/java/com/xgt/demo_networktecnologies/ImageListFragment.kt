package com.xgt.demo_networktecnologies

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.xgt.demo_networktecnologies.databinding.FragmentImageListBinding
import com.xgt.demo_networktecnologies.model.Image
import com.xgt.demo_networktecnologies.network.TecnologyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ImageListFragment : Fragment() {

    private var _binding: FragmentImageListBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = ImageAdapter {
        val clipMan = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val myClip = ClipData.newPlainText("NetworkTechnologies", it.imageUrl)
        clipMan.setPrimaryClip(myClip)
        Toast.makeText(requireContext(), "image url añadida al portapapeles", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvImages.layoutManager = GridLayoutManager(context, 2)
        binding.rvImages.adapter = adapter


        TecnologyApi.service.getImages().enqueue(object : Callback<List<Image>> {
            override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {
                if (response.isSuccessful) {
                    adapter.submitList(response.body())
                } else {
                    Toast.makeText(context, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                Toast.makeText(context, "Error en la conexión", Toast.LENGTH_SHORT).show()
                Log.e("requestData", "error", t)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}