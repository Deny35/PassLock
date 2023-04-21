 package pl.denys.karol.passlock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint


 @AndroidEntryPoint
 class MainActivity : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {

         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

     }

 }