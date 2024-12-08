package com.olbareum.olbareum

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.olbareum.olbareum.databinding.ActivityLoginBinding
import com.olbareum.olbareum.retrofit.dto.user.UserLoginRequestDto
import com.olbareum.olbareum.retrofit.view_model.UserViewModel

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.accessToken.observe(this) {
            MyApplication.preferences.setAccessToken(it)
            startActivity(Intent(this, HomeActivity::class.java))
        }

        viewModel.errorMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        binding.loginButton.setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))

            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                val userLoginRequestDto = UserLoginRequestDto(email, password)
                viewModel.userLogin(userLoginRequestDto)
            }
        }

        binding.signupButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}