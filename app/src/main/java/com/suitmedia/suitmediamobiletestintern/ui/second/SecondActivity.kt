package com.suitmedia.suitmediamobiletestintern.ui.second

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.suitmedia.suitmediamobiletestintern.databinding.ActivitySecondBinding
import com.suitmedia.suitmediamobiletestintern.ui.third.ThirdActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySecondBinding

    companion object {
        const val SECOND_INTENT_KEY = "SECOND KEY"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                binding.tvSelectedUsername.text =  it.data?.getStringExtra(ThirdActivity.THIRD_INTENT_KEY)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "Second Screen"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val username = intent.getStringExtra(SECOND_INTENT_KEY)

        binding.tvUsername.text = username

        binding.btnSecondNext.setOnClickListener {
            val intentDetail = Intent(this, ThirdActivity::class.java)
            getResult.launch(intentDetail)
        }

    }
}