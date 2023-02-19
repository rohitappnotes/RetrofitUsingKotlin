package net.larntech.retrofit_users.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import net.larntech.retrofit_users.databinding.ActivityLoginBinding
import net.larntech.retrofit_users.model.request.LoginRequest
import net.larntech.retrofit_users.model.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        initData();
    }

    private fun initData(){

        clickListener();

    }

    private fun clickListener(){
        binding.tvCreateAccount.setOnClickListener {
            moveToRegister();
        }

        binding.btnLogin.setOnClickListener {
            getInputs();
        }

    }

    private fun moveToRegister(){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun getInputs(){

        val username = binding.etUsername.text.toString();
        val password = binding.edPassword.text.toString();

        if(username.isNotEmpty() && password.isNotEmpty()){
            loginUser(username,password);
        }else{
            showMessage("All inputs required ...")
        }


    }



    private fun showMessage(message: String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }




    private fun loginUser(userName:String, password: String){

        val loginRequest = LoginRequest(userName,password);

        val apiCall = ApiClient.getApiService().loginUser(loginRequest);
        apiCall.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
              if(response.isSuccessful){
                  moveToDashboard(response.body()!!.username);
              }else{
                  showMessage("Unable to login, Please check your credential and try again")
              }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                showMessage("An error occurred "+t.localizedMessage)
            }

        })



    }


    private fun moveToDashboard(username: String){
        startActivity(Intent(this,DashboardActivity::class.java).putExtra("username",username))
    }



}