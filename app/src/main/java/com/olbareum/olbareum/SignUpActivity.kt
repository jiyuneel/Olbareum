package com.olbareum.olbareum

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.olbareum.olbareum.databinding.ActivitySignUpBinding
import com.olbareum.olbareum.retrofit.dto.user.UserCreateRequestDto
import com.olbareum.olbareum.retrofit.view_model.UserViewModel

class SignUpActivity : BaseActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.signupButton.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val passwordConfirm = binding.passwordConfirm.text.toString().trim()
            val nickname = binding.nickname.text.toString().trim()
            val phoneNumber = binding.phoneNumber.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (passwordConfirm.isEmpty()) {
                Toast.makeText(this, "비밀번호를 다시 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (nickname.isEmpty()) {
                Toast.makeText(this, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                val userCreateRequestDto = UserCreateRequestDto(
                    email = email,
                    password = password,
                    username = nickname,
                    nickname = nickname
                )
                viewModel.userSignUp(userCreateRequestDto)
            }
        }
    }
}