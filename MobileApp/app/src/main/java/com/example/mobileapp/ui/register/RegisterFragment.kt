package com.example.mobileapp.ui.register


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobileapp.R
import com.example.mobileapp.databinding.FragmentRegisterBinding
import com.example.mobileapp.user_login
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel
    private var _binding: FragmentRegisterBinding? = null
    private lateinit var viewOfLayout: View

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        registerViewModel =
                ViewModelProvider(this).get(RegisterViewModel::class.java)

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewOfLayout = inflater.inflate(R.layout.nav_header_main, container, false)

        val kek = viewOfLayout.findViewById(R.id.textView) as TextView
        kek.text = "mda mda"
        viewOfLayout.findViewById<TextView>(R.id.textView2).text = "mdamda"

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

    @SuppressLint("SetTextI18n")
    private fun setButtonState(view: View) {

        val login: String = _binding?.fragmentRegisterLogin?.text.toString()
        val password: String = _binding?.fragmentRegisterPassword?.text.toString()
        val name: String = _binding?.fragmentRegisterName?.text.toString()
        val surname: String = _binding?.fragmentRegisterSurname?.text.toString()
        val phoneNumber: String = _binding?.fragmentRegisterPhone?.text.toString()
        val email: String = _binding?.fragmentRegisterEmail?.text.toString()
        val roomNumber: String = _binding?.fragmentRegisterRoom?.text.toString()

        if (login == "" ||
            password == "" ||
            name == "" ||
            surname == "" ||
            phoneNumber == "" ||
            email == "" ||
            roomNumber == ""
                ) {
            Toast.makeText(context, "Fill all fields!", Toast.LENGTH_SHORT).show()
            return
        }

        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)


        var reqParam = "";
        reqParam += buildRequestParam("login", login, true)
        reqParam += buildRequestParam("password", password)
        reqParam += buildRequestParam("name", name)
        reqParam += buildRequestParam("surname", surname)
        reqParam += buildRequestParam("phone", phoneNumber)
        reqParam += buildRequestParam("email", email)
        reqParam += buildRequestParam("roomNumber", roomNumber)

        val mURL = URL("https://5f9e-37-214-46-183.ngrok.io/api/v1/users/create")

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
            }
            catch (e: Exception) {
                Toast.makeText(context, "This login already exist", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buildRequestParam(key: String, value: String, need_literal: Boolean = false): String {
        var literal = ""
        if(!need_literal) literal = "&";
        return literal + URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8")
    }
}