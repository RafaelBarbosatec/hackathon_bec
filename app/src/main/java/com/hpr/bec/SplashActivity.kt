package com.hpr.bec

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import br.com.iterative.abasteceai.Support.Util.AnimationsCustomUtil
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AnimationsCustomUtil.startAnimationSplash(imagview_logo,500)

        object: CountDownTimer(2000,100){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {

                startActivity(Intent(this@SplashActivity,TipoUsuarioActivity::class.java))
                finish()

            }

        }.start()
    }
}
