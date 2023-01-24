package com.utad.unidad_3_a_peliculas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.utad.unidad_3_a_peliculas.databinding.FragmentLoginBinding


class Login_fragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        // Poner logo
        val logo = binding.logo
        var url = "https://cdn.pixabay.com/photo/2015/02/12/11/54/popcorn-633627_960_720.png"
        Picasso.get().load(url).into(logo)




        // Ir a registro
        binding.btnARegistro.setOnClickListener{
            val action = Login_fragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        // Al pulsar boton login
        binding.btnLogin.setOnClickListener{
            var email = binding.tfEmail.text.toString()
            if(isEmailValid(email) && binding.tfPassword.text.toString() != ""){
                binding.lblError.text = ""
                var user = email
                val action = Login_fragmentDirections.actionLoginFragmentToHomeFragment()
                findNavController().navigate(action)
            } else {
                binding.lblError.text = "Incorrecto"
            }

        }
    }

    // Funcion para comprobar el formato correcto de un email
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }
}