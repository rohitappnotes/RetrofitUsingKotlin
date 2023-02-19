package net.larntech.retrofit_users.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import net.larntech.retrofit_users.databinding.ActivityRegisterBinding
import net.larntech.retrofit_users.model.request.RegisterRequest
import net.larntech.retrofit_users.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        initData()
    }

    private fun initData(){
        clickListener();

    }



    private fun clickListener(){
        binding.tvLogin.setOnClickListener {
            moveToLogin();
        }

        binding.btnRegister.setOnClickListener {
            getInputs();
        }
    }

    private fun getInputs(){
        val username = binding.etUsername.text.toString()
        val useremail = binding.etUseremail.text.toString()
        val password = binding.edPassword.text.toString()
        val cpassword = binding.edcPassword.text.toString()

        if(username.isNotEmpty()  && useremail.isNotEmpty()  && password.isNotEmpty() && cpassword.isNotEmpty()){

            if(password == cpassword){
                //registration
                registerUser(username,useremail,password,"2021-12-03 05:54:29")


            }else{
                showMessage("Password Mismatch")
            }


        }else{
            showMessage("All inputs required ...")
        }

    }

    private fun registerUser(username: String, userEmail: String, password: String, joinedDate: String){
        val registerRequest = RegisterRequest(username, userEmail, password, joinedDate);

        val apiCall = ApiClient.getApiService().registerUser(registerRequest)
        apiCall.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                //
                if(response.isSuccessful){
                    moveToLogin()
                }else{
                    showMessage("Unable to register ...")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
              //
                showMessage("An error occurred "+t.localizedMessage)
            }

        })

    }



    private fun showMessage(message: String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    private fun moveToLogin(){
        startActivity(Intent(this,LoginActivity::class.java))
    }
}