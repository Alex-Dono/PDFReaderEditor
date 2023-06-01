package com.pdfreadereditor

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.pdfreadereditor.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var selectFileLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        selectFileLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            // Handle the returned Uri
            if (uri != null) {
                val intent = Intent(this, ReadActivity::class.java).apply {
                    putExtra("fileUri", uri.toString())
                }
                startActivity(intent)
            }
        }

        val selectPDFToRead: Button = binding.selectPDFToRead

        selectPDFToRead.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            } else {
                // Permission has been granted. Start the file chooser.
                selectFileLauncher.launch(arrayOf("application/pdf"))
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) { // The request code we used when we requested the permission
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted. You can now start the file chooser.
                selectFileLauncher.launch(arrayOf("application/pdf"))
            } else {
                // Permission was denied. You should explain to the user why the app needs the permission.
                // And maybe provide a way to open the permission request dialog again.
            }
        }
    }



}