package com.pab.putraluragungtrans

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnLayout
import com.google.android.material.button.MaterialButton
import java.io.File
import java.io.FileOutputStream

class TicketStruckActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticket_struck)

        val rootLayout = findViewById<View>(R.id.main)
        val actionBack = findViewById<MaterialButton>(R.id.actionBack)
        val actionShare = findViewById<MaterialButton>(R.id.actionShare)

        actionShare.setOnClickListener {
            actionBack.visibility = View.GONE
            actionShare.visibility = View.GONE

            rootLayout.post {
                val pdfFile = createPdfFromView(rootLayout)

                actionBack.visibility = View.VISIBLE
                actionShare.visibility = View.VISIBLE

                sharePdf(pdfFile)
            }
        }

        actionBack.setOnClickListener {
            navigateTo(Dashboard::class.java, R.id.nav_ticket)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun viewToBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(
            view.width,
            view.height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun createPdfFromView(view: View): File {
        val width = view.width
        val height = view.height.coerceAtMost(2000)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(width, height, 1).create()

        val page = pdfDocument.startPage(pageInfo)
        page.canvas.drawBitmap(bitmap, 0f, 0f, null)
        pdfDocument.finishPage(page)

        val file = File(cacheDir, "struk_tiket.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        return file
    }


    private fun sharePdf(pdfFile: File) {
        Log.d("PDF", pdfFile.absolutePath)

        val uri = FileProvider.getUriForFile(
            this,
            "${packageName}.provider",
            pdfFile
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        try {
            startActivity(
                Intent.createChooser(intent, "Bagikan struk tiket")
            )

        } catch (e: Exception) {
            Toast.makeText(this, "Tidak ada aplikasi untuk membuka PDF", Toast.LENGTH_SHORT).show()
        }
    }
}