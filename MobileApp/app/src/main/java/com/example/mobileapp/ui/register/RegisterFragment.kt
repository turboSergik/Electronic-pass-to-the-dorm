package com.example.mobileapp.ui.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel
    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        registerViewModel =
                ViewModelProvider(this).get(RegisterViewModel::class.java)

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textRegister
        registerViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val button = binding.registerButton
        button.setOnClickListener { view ->
            setButtonState(view)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setButtonState(view: View) {

        val login: String = _binding?.fragmentRegisterLogin?.text.toString()
        val password: String = _binding?.fragmentRegisterPassword?.text.toString()
        var roomNumber: String = _binding?.fragmentRegisterRoom?.text.toString()
        var email: String = _binding?.fragmentRegisterEmail?.text.toString()
        var phone: String = _binding?.fragmentRegisterPhone?.text.toString()

        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)

        val myToast = Toast.makeText(context, "${login}, ${password}!", Toast.LENGTH_SHORT)
        myToast.show()
    }
}