package com.suitmedia.suitmediamobiletestintern.ui.third

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.suitmediamobiletestintern.R
import com.suitmedia.suitmediamobiletestintern.data.retrofit.UserResponseDataItem
import com.suitmedia.suitmediamobiletestintern.databinding.ActivityThirdBinding
import com.suitmedia.suitmediamobiletestintern.ui.UsersViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        actionbar!!.title = getString(R.string.title_third_screen)
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

    private fun showList(state: Boolean){

        if (state){
            binding.srlRefresh.visibility = View.VISIBLE
            binding.tvNodata.visibility = View.GONE
        } else {
            binding.srlRefresh.visibility = View.GONE
            binding.tvNodata.visibility = View.VISIBLE
        }
    }
    private fun getData(){
        viewModel.getPaging.observe(this){ pagingData ->

            lifecycleScope.launch {
                adapter.loadStateFlow.collectLatest {
                    showList( it.refresh !is LoadState.Error)
                }
            }

            binding.srlRefresh.isRefreshing = false;

            if (pagingData != null){
                setUsers(pagingData)
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