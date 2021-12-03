package com.example.mobileapp.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapp.databinding.FragmentHomeBinding
import android.widget.Toast
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.example.mobileapp.R
import org.json.JSONException
import org.json.JSONObject
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions


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
            val scanner = IntentIntegrator.forSupportFragment(this)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

           val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            Toast.makeText(activity, "start scanning!", Toast.LENGTH_LONG).show()
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(activity, R.string.result_not_found, Toast.LENGTH_LONG).show()
                } else {
                    val obj = result.contents
                    Toast.makeText(activity, obj, Toast.LENGTH_LONG).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
    }
}