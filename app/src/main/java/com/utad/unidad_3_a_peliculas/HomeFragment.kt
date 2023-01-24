package com.utad.unidad_3_a_peliculas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.utad.unidad_3_a_peliculas.adapters.*
import com.utad.unidad_3_a_peliculas.databinding.FragmentHomeBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cargarAdapters()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun cargarAdapters() {
        val json = readJsonFromFile("peliculas.json")
        val todos = Gson().fromJson(json, PeliculaResponse::class.java)

        // Dividir por generos
        var dramaList: ArrayList<Pelicula> = arrayListOf()
        var serieList: ArrayList<Pelicula> = arrayListOf()
        var comediaList: ArrayList<Pelicula> = arrayListOf()
        var belicoList: ArrayList<Pelicula> = arrayListOf()
        var accionList: ArrayList<Pelicula> = arrayListOf()
        for (item: Pelicula in todos.data) {
            when (item.genero) {
                "serie" -> {
                    serieList.add(item)
                }
                "drama" -> {
                    dramaList.add(item)
                }
                "comedia" -> {
                    comediaList.add(item)

                }
                "belico" -> {
                    belicoList.add(item)
                }
                "accion" -> {
                    accionList.add(item)
                }
                else -> {
                    Log.i("MainActivity","Genero no contemplado")
                }
            }
        }
        // Adapter drama
        val miDramaAdapter = DramaAdapter(dramaList) {
            val action = HomeFragmentDirections.actionHomeFragmentToInfoFragment(it)
            findNavController().navigate(action)
        }
        var dramaRecyclerView = binding.DramaRecyclerView
        dramaRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        dramaRecyclerView.adapter = miDramaAdapter

        // Adapter series
        val miSerieAdapter = SeriesAdapter(serieList) {
            val action = HomeFragmentDirections.actionHomeFragmentToInfoFragment(it)
            findNavController().navigate(action)
        }
        var serieRecyclerView = binding.SeriesRecyclerView
        serieRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        serieRecyclerView.adapter = miSerieAdapter

        // Adapter comedia
        val miComediaAdapter = ComediaAdapter(comediaList) {
            val action = HomeFragmentDirections.actionHomeFragmentToInfoFragment(it)
            findNavController().navigate(action)
        }
        var comediaRecycerView = binding.ComediaRecyclerView
        comediaRecycerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        comediaRecycerView.adapter = miComediaAdapter

        // Adapter accion
        val miAccionAdapter = AccionAdapter(accionList) {
            val action = HomeFragmentDirections.actionHomeFragmentToInfoFragment(it)
            findNavController().navigate(action)
        }
        var accionRecyclerView = binding.AccionRecyclerView
        accionRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        accionRecyclerView.adapter = miAccionAdapter

        // Adapter belico
        val miBelicoAdapter = BelicoAdapter(belicoList) {
            val action = HomeFragmentDirections.actionHomeFragmentToInfoFragment(it)
            findNavController().navigate(action)
        }
        var belicoRecyclerView = binding.BelicoRecyclerView
        belicoRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        belicoRecyclerView.adapter = miBelicoAdapter
    }
    private fun irPantallaInfo(peli: Pelicula){
        val action = HomeFragmentDirections.actionHomeFragmentToInfoFragment(peli)
        findNavController().navigate(action)
    }

    private fun readJsonFromFile(fileName: String): String {
        var json = ""
        try {
            val bufferedReader = BufferedReader(
                InputStreamReader(activity?.assets?.open(fileName))
            )
            val paramsBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                paramsBuilder.append(line)
                line = bufferedReader.readLine()
            }
            json = paramsBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }
}
