package com.example.mobileapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.budiyev.android.codescanner.CodeScanner
import com.example.mobileapp.R
import com.example.mobileapp.databinding.FragmentHomeBinding
import com.example.mobileapp.user_login
import com.google.zxing.integration.android.IntentIntegrator
import java.net.HttpURLConnection
import java.net.URL

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var qrScanIntegrator: IntentIntegrator

    private lateinit var codeScanner: CodeScanner

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val button = binding.scanQrButton
        button.setOnClickListener { view ->
            buttonOnClick(view)
        }

        updateButtonsState()

        return root
    }

    private fun updateButtonsState()
    {
//        if (user_login != "") {
//            activity?.findViewById<View>(R.id.nav_register)?.visibility = View.INVISIBLE
//            activity?.findViewById<View>(R.id.nav_login)?.visibility = View.INVISIBLE
//        }
//        else {
//            activity?.findViewById<View>(R.id.nav_register)?.visibility = View.VISIBLE
//            activity?.findViewById<View>(R.id.nav_login)?.visibility = View.VISIBLE
//            activity?.findViewById<View>(R.id.nav_logout)?.visibility = View.INVISIBLE
//        }
    }

    private fun buttonOnClick(view: View) {

        if (user_login == "") {
            Toast.makeText(activity, "Please, login first", Toast.LENGTH_LONG).show()
        }
        else {
            val scanner = IntentIntegrator.forSupportFragment(this)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

           val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(activity, R.string.result_not_found, Toast.LENGTH_LONG).show()
                } else {
                    val obj = result.contents
                    makeLoginRequest()

                    Toast.makeText(activity, "OK!", Toast.LENGTH_LONG).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
    }


    private fun makeLoginRequest() {

        val mURL = URL("https://5f9e-37-214-46-183.ngrok.io/api/v1/comming/put/$user_login")

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"

            println("URL : $url")
            println("requestMethod : $requestMethod")
            println("Response Code : $responseCode")
            println("Response mess : $responseMessage")

        }
    }
}