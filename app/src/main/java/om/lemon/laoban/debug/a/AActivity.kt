package om.lemon.laoban.debug.a

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import om.lemon.laoban.debug.R


class AActivity : AppCompatActivity() {

    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
    }
}