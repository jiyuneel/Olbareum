package com.olbareum.olbareum.record

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.olbareum.olbareum.FeedbackActivity
import com.olbareum.olbareum.R
import com.olbareum.olbareum.databinding.ActivityRecordBinding
import com.olbareum.olbareum.retrofit.RetrofitService
import com.olbareum.olbareum.retrofit.dto.feedback.FeedbackResponseDto
import com.olbareum.olbareum.retrofit.view_model.FeedbackViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException

class RecordActivity : AppCompatActivity(), OnTimerTickListener {

    companion object {
        private const val REQUEST_RECORD_AUDIO_CODE = 200
    }

    private enum class State {
        RELEASE, RECORDING, PLAYING
    }

    private lateinit var timer: Timer

    private lateinit var binding: ActivityRecordBinding
    private val viewModel: FeedbackViewModel by viewModels()
    private var recorder: MediaRecorder? = null
    private var filename: String = ""
    private var state: State = State.RELEASE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        filename = "${externalCacheDir?.absolutePath}/result.3gp"
        timer = Timer(this)

        binding.recordButton.setOnClickListener {
            when (state) {
                State.RELEASE -> {
                    record()
                }

                State.RECORDING -> onRecord(false)
                State.PLAYING -> {
                }
            }
        }

        binding.analyzeButton.isEnabled = false
        binding.analyzeButton.alpha = 0.3f

        binding.analyzeButton.setOnClickListener {
            uploadAudioFile(binding.sentence.text.toString())
            startActivity(Intent(this, FeedbackActivity::class.java))
        }

        binding.backButton.setOnClickListener {
            finish()
        }

//        val dialog = Dialog(this)
//        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경을 투명하게
//        dialog.setContentView(ProgressBar(this)) // ProgressBar 위젯 생성
//        dialog.setCanceledOnTouchOutside(false) // 외부 터치 막음
//        dialog.setOnCancelListener { this.finish() } // 뒤로가기시 현재 액티비티 종료
//        dialog.show()
    }

    private fun record() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED -> {
                // 녹음 시작
                onRecord(true)
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this, android.Manifest.permission.RECORD_AUDIO
            ) -> {
                showPermissionRationaleDialog()
            }

            else -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO_CODE
                )
            }
        }
    }

    private fun onRecord(start: Boolean) = if (start) startRecording() else stopRecording()

    private fun startRecording() {
        state = State.RECORDING

        recorder = MediaRecorder(this).apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(filename)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e("testt", "prepare() failed $e")
            }

            start()
        }

        binding.waveformView.clearData()
        timer.start()

        binding.recordButton.setImageDrawable(
            ContextCompat.getDrawable(
                this, R.drawable.ic_stop
            )
        )

        binding.analyzeButton.isEnabled = false
        binding.analyzeButton.alpha = 0.3f
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null

        timer.stop()

        state = State.RELEASE

        binding.recordButton.setImageDrawable(
            ContextCompat.getDrawable(
                this, R.drawable.ic_mic
            )
        )

        binding.analyzeButton.isEnabled = true
        binding.analyzeButton.alpha = 1.0f
    }

    private fun showPermissionRationaleDialog() {
        AlertDialog.Builder(this).setMessage("녹음 권한을 켜주셔야 앱을 정상적으로 사용할 수 있습니다.")
            .setPositiveButton("권한 허용하기") { _, _ ->
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO_CODE
                )
            }.setNegativeButton("취소") { dialogInterface, _ -> dialogInterface.cancel() }.show()
    }

    private fun showPermissionSettingDialog() {
        AlertDialog.Builder(this)
            .setMessage("녹음 권한을 켜주셔야 앱을 정상적으로 사용할 수 있습니다. 앱 설정 화면에서 권한을 허용해주세요.")
            .setPositiveButton("권한 허용하러 가기") { _, _ ->
                navigateToAppSetting()
            }.setNegativeButton("취소") { dialogInterface, _ -> dialogInterface.cancel() }.show()
    }

    private fun navigateToAppSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null)
        }
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRecordPermissionGranted =
            requestCode == REQUEST_RECORD_AUDIO_CODE && grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if (audioRecordPermissionGranted) {
            onRecord(true)
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.RECORD_AUDIO
                )
            ) {
                showPermissionRationaleDialog()
            } else {
                showPermissionSettingDialog()
            }
        }
    }

    override fun onTick(duration: Long) {
        val millisecond = duration % 1000
        val second = (duration / 1000) % 60
        val minute = (duration / 1000 / 60)

        binding.timerTextView.text = String.format("%02d:%02d.%02d", minute, second, millisecond / 10)
        binding.waveformView.addAmplitude(recorder?.maxAmplitude?.toFloat() ?: 0f)
    }

    private fun uploadAudioFile(textSentence: String) {
        val file = File(filename)
        if (!file.exists()) {
            return
        }

        // 파일을 RequestBody로 변환하고 Multipart로 포장
        val requestFile = file.asRequestBody("audio/3gp".toMediaTypeOrNull())
        val audioPart = MultipartBody.Part.createFormData("audioFile", file.name, requestFile)

        viewModel.createFeedback(textSentence, audioPart)
    }
}