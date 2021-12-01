package com.furkankurt.kotlincatchjerry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.furkankurt.kotlincatchjerry.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score=0
    var imageArray=ArrayList<ImageView>()
    var handler=Handler(Looper.getMainLooper())
    var runnable= Runnable {  }


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ImageArray
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageVie4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageVie7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        hideImages()
        //CountDownTimer
        //15 saniye çalıştır ve her bir saniyede bir istediğimi yap.
        object :CountDownTimer(15500,1000){
            override fun onTick(millisUntilFinished: Long) {
            binding.timeText.text="Time: "+millisUntilFinished/1000
            }
            override fun onFinish() {
                binding.timeText.text="Time: 0"
                handler.removeCallbacks(runnable)
                for(image in imageArray)
                {
                    image.visibility=View.INVISIBLE
                }



                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes"){dialog,which->
                    //Oyunu baştan başlatıcaz.
                    val intent=intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialog,which->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }
                alert.show()
                            }
        }.start()

    }
    fun hideImages(){
        runnable=object:Runnable{
            override fun run() {
                for(image in imageArray)
                {
                    image.visibility=View.INVISIBLE
                }
                val random = Random()
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,500)
            }

        }
        handler.post(runnable)

    }
    fun score(view: View)
    {
        score++
        binding.scoreText.text="Score: "+score
    }
}