package com.revolut.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Handler
import androidx.lifecycle.lifecycleScope
import com.revolut.R
import com.revolut.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    init {
        lifecycleScope.launchWhenCreated {
            setContentView(R.layout.activity_splash)
            mDelayHandler = Handler()
            mDelayHandler!!.postDelayed(mRunnable, DELAY)
        }
    }

    public override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private var mDelayHandler: Handler? = null
    private val DELAY: Long = 1500
}