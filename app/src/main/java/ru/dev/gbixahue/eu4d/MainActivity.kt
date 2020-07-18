package ru.dev.gbixahue.eu4d

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.gbixahue.eu4d.android.extension.view.find
import ru.gbixahue.eu4d.android.extension.view.inflate

class MainActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val view: ViewGroup = llContainer.inflate(R.layout.test)
    val tv: TextView = view.find(R.id.tvLoreIpsum)
  }
}
