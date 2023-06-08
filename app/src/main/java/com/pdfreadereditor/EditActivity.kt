package com.pdfreadereditor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.pdfreadereditor.databinding.ActivityEditBinding
import com.pdftron.pdf.config.ViewerConfig
import com.pdftron.pdf.controls.DocumentActivity

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val uriString = intent.getStringExtra("fileUri")
        val uri = Uri.parse(uriString)

        val config = ViewerConfig.Builder().openUrlCachePath(this.cacheDir.absolutePath).build()

        val intent = DocumentActivity.IntentBuilder.fromActivityClass(this, DocumentActivity::class.java)
            .withUri(uri)
            .usingConfig(config)
            .usingTheme(R.style.Theme_PDFReaderEditor)
            .build()
        startActivity(intent)
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Return to Home Page?")
            .setMessage("Leave and return to the home page without saving?")
            .setNegativeButton(android.R.string.no, null)
            .setPositiveButton(android.R.string.yes) { _, _ ->
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }.create().show()
    }


}