package com.the12smb.capstonefrucheck.view.upload

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.the12smb.capstonefrucheck.R
import com.the12smb.capstonefrucheck.data.local.model.UserPreference
import com.the12smb.capstonefrucheck.databinding.ActivityUploadBinding
import com.the12smb.capstonefrucheck.view.ViewModelFactory
import com.the12smb.capstonefrucheck.view.camera.CameraActivity
import com.the12smb.capstonefrucheck.view.home.HomeActivity
import com.the12smb.capstonefrucheck.view.profile.ProfileActivity
import com.the12smb.capstonefrucheck.view.reduceFileImage
import com.the12smb.capstonefrucheck.view.rotateFile
import com.the12smb.capstonefrucheck.view.uriToFile
import java.io.File

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var uploadViewModel: UploadViewModel

    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        checkPermission()
        setupBottomNav()
        setupView()
        setupAction()
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun checkPermission() {
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun setupViewModel() {
        val pref = UserPreference.getInstance(dataStore)
        uploadViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[UploadViewModel::class.java]
    }

    private fun setupView() {
        supportActionBar?.title = getString(R.string.upload)

        uploadViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setupAction() {
        binding.buttonCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            launcherIntentCameraX.launch(intent)
        }
        binding.buttonGallery.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            val chooser = Intent.createChooser(intent, "Pilih Sebuah Gambar")
            launcherIntentGallery.launch(chooser)
        }
        binding.buttonDelete.setOnClickListener{
            binding.imagePhoto.setImageResource(R.drawable.no_image)
        }
        binding.buttonAdd.setOnClickListener {
            if (getFile != null) {
                val file = reduceFileImage(getFile as File)

                val uploadImageRequest = uploadViewModel.upload(file)
                uploadImageRequest.observe(this) {
                    if (it) {
                        Toast.makeText(this, "Foto Terupload!", Toast.LENGTH_SHORT).show()
                        uploadViewModel.hasilscan.observe(this) { hasilscan ->
                            binding.tvFruit.text = hasilscan.predictedClass
                            binding.tvCondition.text = hasilscan.freshness
                        }
                    } else {
                        Toast.makeText(this, "Foto tidak terupload", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Tolong masukan foto buahnya dulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as? File

            myFile?.let { file ->
                getFile = file
                binding.imagePhoto.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri

            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this@UploadActivity)
                getFile = myFile
                binding.imagePhoto.setImageURI(uri)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Not getting permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }


    private fun setupBottomNav() {
        binding.bottomnav.selectedItemId = R.id.navigation_upload
        binding.bottomnav.itemIconTintList= null

        binding.bottomnav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_upload -> {
                    true
                }
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

}