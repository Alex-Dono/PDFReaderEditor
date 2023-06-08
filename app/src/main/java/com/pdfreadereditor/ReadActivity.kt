package com.pdfreadereditor

import android.content.ContentProvider
import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentResolverCompat
import androidx.core.content.FileProvider
import com.github.barteksc.pdfviewer.PDFView
import com.pdfreadereditor.databinding.ActivityReadBinding
import java.io.FileNotFoundException

class ReadActivity : AppCompatActivity() {

    private lateinit var pdfView: PDFView
    private lateinit var binding: ActivityReadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReadBinding.inflate(layoutInflater)

        setContentView(binding.root)

        pdfView = binding.pdfView

        val uriString = intent.getStringExtra("fileUri")
        val uri = Uri.parse(uriString)

        try {
            if (uri != null) {
                pdfView.fromUri(uri)
                    .defaultPage(0)
                    .spacing(10) // in dp
                    .load()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }
}
