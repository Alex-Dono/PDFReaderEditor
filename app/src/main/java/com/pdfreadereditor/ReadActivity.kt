package com.pdfreadereditor

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pdfreadereditor.databinding.ActivityReadBinding
import com.pdftron.pdf.config.ViewerConfig
import com.pdftron.pdf.controls.DocumentActivity
import java.io.File

class ReadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReadBinding.inflate(layoutInflater)

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

}