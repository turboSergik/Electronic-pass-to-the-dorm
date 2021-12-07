package com.example.mobileapp.ui.login


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobileapp.R
import com.example.mobileapp.databinding.FragmentLoginBinding
import com.example.mobileapp.user_login
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
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

        makeLoginRequest(view)

        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun makeLoginRequest(view: View) {

        val login: String = _binding?.fragmentLoginLogin?.text.toString()
        val password: String = _binding?.fragmentLoginPassword?.text.toString()

        if (login == "" ||
            password == ""
        ) {
            Toast.makeText(context, "Fill all fields!", Toast.LENGTH_SHORT).show()
            return
        }

        var reqParam = "";
        reqParam += buildRequestParam("login", login, true)
        reqParam += buildRequestParam("password", password)

        val mURL = URL("https://5f9e-37-214-46-183.ngrok.io/api/v1/users/login")

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "POST"

            val wr = OutputStreamWriter(getOutputStream());
            wr.write(reqParam);
            wr.flush();

            println("URL : $url")

            println("reqParam : $reqParam")
            println("requestMethod : $requestMethod")

            println("Response Code : $responseCode")
            println("Response mess : $responseMessage")

            try {
                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    println("Response : $response")

                    val answer = JSONObject("$response")
                    user_login = answer.get("login").toString()

                    findNavController().navigate(R.id.nav_home)
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Incorrect login or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buildRequestParam(key: String, value: String, need_literal: Boolean = false): String {
        var literal = ""
        if(!need_literal) literal = "&";
        return literal + URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8")
    }

}