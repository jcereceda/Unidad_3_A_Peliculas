package com.utad.unidad_3_a_peliculas

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.utad.unidad_3_a_peliculas.databinding.FragmentHomeBinding
import com.utad.unidad_3_a_peliculas.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    val args: InfoFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       (activity as AppCompatActivity).supportActionBar?.title = ""
        (activity as AppCompatActivity).supportActionBar?.show()


        val miPeli = args.pelicula
        val img = binding.imgPelicula
        Picasso.get().load(miPeli.linkFoto2).into(img)

        val argumento = binding.argumento
        val texto  = "-\tAño: " + miPeli.year + "\n\n-\tDirector: " + miPeli.director + "\n\nSinopsis: \n" + miPeli.argumento
        argumento.text = texto

        val toolbar = binding.toolbar
        toolbar.title = miPeli.titulo

    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }
    override fun onPause() {
        super.onPause()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

}