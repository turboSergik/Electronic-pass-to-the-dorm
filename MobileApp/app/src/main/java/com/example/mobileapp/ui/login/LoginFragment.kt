package com.example.mobileapp.ui.login

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobileapp.R
import com.example.mobileapp.databinding.FragmentLoginBinding
import java.io.BufferedReader
import java.io.InputStreamReader


import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
                ViewModelProvider(this).get(LoginViewModel::class.java)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLogin
        loginViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val button = binding.button
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

        val login: String = _binding?.fragmentLoginLogin?.text.toString()
        val password: String = _binding?.fragmentLoginPassword?.text.toString()

        val myToast = Toast.makeText(context, "${login}, ${password}!", Toast.LENGTH_SHORT)
        // myToast.show()

        makeLoginRequest()

        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)

        findNavController().navigate(R.id.nav_home)
    }

    private fun makeLoginRequest() {

        var reqParam = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode("lol", "UTF-8")
        reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode("kek", "UTF-8")

        val mURL = URL("https://jsonplaceholder.typicode.com/posts")

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"

            println("URL : $url")
            println("Response Code : $responseCode")

            val myToast = Toast.makeText(context, "$url response code = $responseCode", Toast.LENGTH_SHORT)
            myToast.show()

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                println("Response : $response")
            }
        }
    }
}