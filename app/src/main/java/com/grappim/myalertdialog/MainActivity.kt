package com.grappim.myalertdialog

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

  private lateinit var mad: MyAlertDialog

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    mad = MyAlertDialog.build(this) {
      title = "title"
      description = "description fgwegwegwegwe weggweg wegwe gweg wegw4 we wefwe "

      titleTextColor = Color.parseColor("#3949AB")
      descriptionTextColor = Color.parseColor("#FF9800")

      btnCancelText = "cancel"
      btnConfirmText = "confirm"
      btnNeutralText = "neutral"

      confirmListener = {
        Timber.d("3t23qtg2qgt24gwegsdg")
      }

      cancelListener = {
        Timber.d("Cancel")
      }

      neutralListener = {
        Timber.d("234g24g24g24g43g3")
      }

      cancelable = true

      titleTextSize = 26f
      descriptionTextSize = 16f

      btnConfirmColor = Color.CYAN
      btnNeutralColor = null
      btnCancelColor = Color.LTGRAY

      btnConfirmTextColor = Color.YELLOW
      btnNeutralTextColor = null
      btnCancelColor = Color.parseColor("#458962")

      windowBackgroundColor = Color.RED
      circleBackgroundColor = Color.parseColor("#795548")
    }

    btnShow.setOnClickListener {
      mad.show()
    }
  }

  override fun onPause() {
    super.onPause()
    mad.dismiss()
  }
}
