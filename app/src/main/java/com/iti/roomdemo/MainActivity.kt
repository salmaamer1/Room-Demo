package com.iti.roomdemo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iti.roomdemo.databinding.ActivityMainBinding
import com.iti.roomdemo.db.Subscriber
import com.iti.roomdemo.db.SubscriberDatabase
import com.iti.roomdemo.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = SubscriberDatabase.GetInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscribeViewModelFactory(repository )
        subscriberViewModel=ViewModelProvider(this, factory ).get(SubscriberViewModel::class.java)
        binding.myViewModel =subscriberViewModel
        binding.lifecycleOwner = this
        initRecylerView()
        subscriberViewModel.message.observe(this, Observer {
      it.getContentIfNotHandled()?.let {
          Toast.makeText(this,it,Toast.LENGTH_LONG).show()
      }
        })
    }
    private fun initRecylerView(){
        binding.subscriberRecycleView.layoutManager =LinearLayoutManager(this)
        displaySubscribeList()
    }
    private fun displaySubscribeList(){
     subscriberViewModel.subscribers.observe(this, Observer {
    Log.i("MYTAG",it.toString())
        binding.subscriberRecycleView.adapter = MyRecyclerViewAdapter(it,{selecteditem:Subscriber ->ListItemclicked(selecteditem)})
})

            }
    private fun ListItemclicked(subscriber: Subscriber){
      //  Toast.makeText(this,"selected name is ${subscriber.name}",Toast.LENGTH_LONG).show()
        subscriberViewModel.intitUpdateAndDelte(subscriber)

    }

}