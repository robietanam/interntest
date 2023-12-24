package com.suitmedia.suitmediamobiletestintern.ui.third

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.suitmediamobiletestintern.data.retrofit.UserResponseDataItem
import com.suitmedia.suitmediamobiletestintern.databinding.ActivityThirdBinding
import com.suitmedia.suitmediamobiletestintern.ui.UsersViewModelFactory

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var adapter: UsersAdapter
    private lateinit var viewModel: ThirdViewModel
    companion object {
        const val THIRD_INTENT_KEY = "THIRD KEY"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "Third Screen"
        actionbar.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this, UsersViewModelFactory.getInstance(this))[ThirdViewModel::class.java]


        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager

        adapter = UsersAdapter()

        binding.rvUsers.adapter = adapter

        getData()

        binding.srlRefresh.setOnRefreshListener {
            getData()
            binding.srlRefresh.isRefreshing = true;
            adapter.refresh()
        }

    }
    private fun getData(){
        viewModel.getPaging.observe(this){
            binding.srlRefresh.isRefreshing = false;
            if (it != null){
                setUsers(it)
            } else {
                //AuthHelper.logOut(this, tokenViewModel)
            }
        }
    }

    private fun setUsers(story: PagingData<UserResponseDataItem>){

        adapter.submitData(lifecycle,story)

        adapter.setOnItemClickCallback(object : UsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserResponseDataItem) {
                setResult(Activity.RESULT_OK, intent.putExtra(THIRD_INTENT_KEY, "${data.firstName} ${data.lastName}"))
                finish()
            }
        })
    }
}