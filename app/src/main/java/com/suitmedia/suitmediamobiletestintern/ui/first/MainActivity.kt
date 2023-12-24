package com.suitmedia.suitmediamobiletestintern.ui.first

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.suitmedia.suitmediamobiletestintern.R
import com.suitmedia.suitmediamobiletestintern.databinding.ActivityMainBinding
import com.suitmedia.suitmediamobiletestintern.ui.second.SecondActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Message")
                .setMessage(
                    when (isPalindrome(binding.edPalindrome.text.toString())) {
                        1 -> resources.getString(R.string.ispalindrome)
                        -1 -> resources.getString(R.string.too_short)
                        else -> resources.getString(R.string.not_palindrome)
                    }
                )
                .setNeutralButton("Tutup") { dialog, which ->
                    // Respond to neutral button press
                }
                .show()
        }

        binding.btnFirstNext.setOnClickListener {
            if (binding.edNama.text != null && isPalindrome(binding.edPalindrome.text.toString()) == 1) {
                val intentDetail = Intent(this, SecondActivity::class.java)
                intentDetail.putExtra(
                    SecondActivity.SECOND_INTENT_KEY,
                    binding.edNama.text.toString()
                )
                startActivity(intentDetail)
            } else {
                MaterialAlertDialogBuilder(this)
                    .setMessage("Mohon lengkapi data dahulu")
                    .setNeutralButton("Tutup") { dialog, which ->
                        // Respond to neutral button press
                    }
                    .show()
            }
        }
    }

    private fun isPalindrome(str: String): Int {
        if (str.isNullOrEmpty() || str.length <= 1) return -1
        val trim = str.trim()
        var start = 0
        var end = trim.length - 1
        while (start < end) {
            if (trim[start] != trim[end]) {
                return 0
            }
            start++
            end--
        }
        return 1
    }
}
