package com.iti.roomdemo
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iti.roomdemo.db.Subscriber
import com.iti.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository):ViewModel(),Observable {
    val subscribers = repository.subscribers
    private var isupdateOrDelte = false
    private lateinit var subscribertoUpdateOrDelete :Subscriber
    @Bindable
    val inputName = MutableLiveData<String?>()
    @Bindable
    val inputEmail = MutableLiveData<String?>()
    @Bindable
    val save_or_update_button_ = MutableLiveData<String>()
    @Bindable
    val clear_or_delte_all_button = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get()=statusMessage

    init {
        save_or_update_button_.value ="SAVE"
        clear_or_delte_all_button.value="CLEAR All"
    }
    fun SaveOrUpdate(){
        if (isupdateOrDelte){
            subscribertoUpdateOrDelete.name =inputName.value!!
            subscribertoUpdateOrDelete.email=inputEmail.value!!
            update(subscribertoUpdateOrDelete)
        }
            else {
            val name: String = inputName.value!!
            val email: String = inputEmail.value!!
            insert(Subscriber(id = 0, name, email))
            inputName.value = null
            inputEmail.value = null
        }
    }
     fun ClearOrDelete(){
         if(isupdateOrDelte){
             delete(subscribertoUpdateOrDelete)
         }else {
             ClearAll()
         }

    }
    fun insert(subscriber: Subscriber):Job =
        viewModelScope.launch {
          val newRowId:Long= repository.insert(subscriber)
            statusMessage.value = Event("Subscriber inserted sucessfully")
            if (newRowId>-1){
                statusMessage.value= Event("Subscriber Inserted Successfully")
            }else{
                statusMessage.value= Event("Error Occurred")
            }
        }
    fun update(subscriber: Subscriber):Job=
        viewModelScope.launch {
            val noOfRowId =
            repository.update(subscriber)
            if (noOfRowId>0) {
                inputName.value = null
                inputEmail.value = null
                isupdateOrDelte = false
                save_or_update_button_.value = "Save"
                clear_or_delte_all_button.value = "ClearAll"
                statusMessage.value = Event(" $noOfRowId Row Updated sucessfully")
            } else {
                statusMessage.value = Event(" Error Occured")

            }
            }
    fun delete(subscriber: Subscriber):Job=
        viewModelScope.launch {
            val noofROwsDeleted=
            repository.delete(subscriber)
            if (noofROwsDeleted>0){
            inputName.value=null
            inputEmail.value=null
            isupdateOrDelte = false
            save_or_update_button_.value ="Save"
            clear_or_delte_all_button.value="ClearAll"
            statusMessage.value = Event("$noofROwsDeleted Rows Deleted sucessfully")
        }
            else{
             statusMessage.value = Event("Error Occured")

            }}
    fun ClearAll():Job=
         viewModelScope.launch {
        val noofROwsDleted= repository.deleteAll()
        if (noofROwsDleted>0){
         statusMessage.value = Event(" $noofROwsDleted  Subscribers Deleted sucessfully")
        }
      else {
  statusMessage.value = Event("Erorr Occured")

             }
             }
    fun intitUpdateAndDelte(subscriber: Subscriber){
        inputName.value=subscriber.name
        inputEmail.value=subscriber.email
        isupdateOrDelte = true
        subscribertoUpdateOrDelete =subscriber
        save_or_update_button_.value ="Update"
        clear_or_delte_all_button.value="Delete"

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}