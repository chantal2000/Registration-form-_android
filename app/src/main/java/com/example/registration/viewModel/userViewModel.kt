package com.example.registration.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import kotlinx.coroutines.launch
class userViewModel:ViewModel() {
    val userRepository= userRepository()
    val registrationLiveData=MutableLiveData<RegistrationResponse>()
    var errorLiveData=MutableLiveData<String>()
    fun registerStudent(){
        viewModelScope.launch {
            var response=userRepository.registerStudent(RegistrationRequest)
            if (response.isSuccessful){
                registrationLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}