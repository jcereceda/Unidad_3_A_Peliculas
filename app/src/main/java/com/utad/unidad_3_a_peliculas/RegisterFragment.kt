package com.utad.unidad_3_a_peliculas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.squareup.picasso.Picasso
import com.utad.unidad_3_a_peliculas.databinding.FragmentRegisterBinding
import java.text.SimpleDateFormat
import java.util.*


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        // Poner logo
        val logo = binding.logo
        var url = "https://cdn.pixabay.com/photo/2015/02/12/11/54/popcorn-633627_960_720.png"
        Picasso.get().load(url).into(logo)



        // Pulsar boton de login
        binding.btnALogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat)
        var date = Date()
        val timeInMillis = date.time
        val constraintBuilder = CalendarConstraints.Builder().setOpenAt(timeInMillis).build()
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Fecha de nacimiento")
            .setCalendarConstraints(constraintBuilder)
            .build()

        binding.FechaNac.setEndIconOnClickListener {
            // Botón de icono textfield de fecha de nacimineto
            datePicker.show(getParentFragmentManager(),"datepicker")
        }
        // Al pulsar el boton de ok del datepicker
        datePicker.addOnPositiveButtonClickListener {
            date = Date(it)
            var formattedDate = ""
            formattedDate = sdf.format(date)
            binding.tfFechaNac.setText(formattedDate)
        }


        binding.tfEmail.doAfterTextChanged {
            var email: String = binding.tfEmail.text.toString()
            if (!isEmailValid(email)) {
                binding.layEmail.error = "Formato invalido"
            } else {
                binding.layEmail.error = null
            }
        }
        binding.tfNombre.doAfterTextChanged {
            if (binding.tfNombre.text.toString() == "") {
                binding.layNombre.error = "Completar campo"
            } else {
                binding.layNombre.error = null
            }
        }
        binding.tfFechaNac.doAfterTextChanged {
            if (binding.tfFechaNac.text.toString() == "") {
                binding.FechaNac.error = "Completar campo"
            } else {
                binding.FechaNac.error = null
            }
        }
        binding.tfPassword.doAfterTextChanged {
            if (binding.tfPassword.text.toString() == "") {
                binding.layPassword.error = "Completar campo"
            } else {
                binding.layPassword.error = null
            }
        }
        binding.tfConfirmPass.doAfterTextChanged {
            if (binding.tfPassword.text.toString() != binding.tfConfirmPass.text.toString()) {
                binding.layConfirmPass.error = "No coincidentes"
            } else {
                binding.layConfirmPass.error = null
            }
        }
        // Al pusar el boton registrar
        binding.btnRegister.setOnClickListener {


            if(comprobarValores()){
                var email = binding.tfEmail.text.toString()
                Toast.makeText(this.context, "Usuario registrado", Toast.LENGTH_SHORT).show()
                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                findNavController().navigate(action)
            }

        }
    }

    private fun comprobarValores(): Boolean {
        var isValid = true
        var email: String = binding.tfEmail.text.toString()
        if (!isEmailValid(email))
            isValid = false

        if(binding.tfNombre.text.toString() == "")
            isValid = false

        if(binding.tfFechaNac.text.toString() == "")
            isValid = false

        if(binding.tfPassword.text.toString() == "")
            isValid = false

        if(binding.tfPassword.text.toString() != binding.tfConfirmPass.text.toString())
            isValid = false

        return isValid;
    }

    // Funcion para comprobar el formato correcto de un email
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }


}