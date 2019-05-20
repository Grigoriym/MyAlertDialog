package com.grappim.myalertdialog

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.layout_my_alert_dialog.*

const val TITLE_TEXT_SIZE = 20f
const val DESCRIPTION_TEXT_SIZE = 14f

class MyAlertDialog private constructor(builder: Builder) :
  AlertDialog(builder.context, builder.animationStyle) {

  private var mTitle: String? = null
  private var mTitleTextColor: Int? = null

  private var mDescription: String? = null
  private var mDescriptionTextColor: Int? = null

  private var mCircleBackgroundColor: Int? = null
  private var mWindowBackgroundColor: Int? = null

  private var mBtnConfirmText: String? = null
  private var mBtnNeutralText: String? = null
  private var mBtnCancelText: String? = null

  private var mConfirmListener: (view: View) -> Unit = {}
  private var mCancelListener: (view: View) -> Unit = {}
  private var mNeutralListener: (view: View) -> Unit = {}

  private var mCancelable: Boolean = false

  private var mBtnConfirmColor: Int? = null
  private var mBtnConfirmTextColor: Int? = null

  private var mBtnCancelColor: Int? = null
  private var mBtnCancelTextColor: Int? = null

  private var mBtnNeutralColor: Int? = null
  private var mBtnNeutralTextColor: Int? = null

  private var mTitleTextSize: Float = TITLE_TEXT_SIZE
  private var mDescriptionTextSize: Float = DESCRIPTION_TEXT_SIZE

  companion object {
    inline fun build(
      context: Context,
      animationStyle: Int = 0,
      block: Builder.() -> Unit
    ): MyAlertDialog =
      Builder(context, animationStyle).apply(block).build()
  }

  class Builder(
    val context: Context,
    val animationStyle: Int = 0
  ) {
    var title: String? = null
    var titleTextColor: Int? = null

    var description: String? = null
    var descriptionTextColor: Int? = null

    var btnConfirmText: String? = null
    var btnNeutralText: String? = null
    var btnCancelText: String? = null

    var confirmListener: (view: View) -> Unit = {}
    var cancelListener: (view: View) -> Unit = {}
    var neutralListener: (view: View) -> Unit = {}

    var cancelable: Boolean = false

    var btnConfirmColor: Int? = null
    var btnConfirmTextColor: Int? = null

    var btnCancelColor: Int? = null
    var btnCancelTextColor: Int? = null

    var btnNeutralColor: Int? = null
    var btnNeutralTextColor: Int? = null

    var titleTextSize: Float = TITLE_TEXT_SIZE
    var descriptionTextSize: Float = DESCRIPTION_TEXT_SIZE

    var circleBackgroundColor: Int? = null
    var windowBackgroundColor: Int? = null

    fun build(): MyAlertDialog = MyAlertDialog(this).apply {
      mTitle = title
      mDescription = description

      mTitleTextColor = titleTextColor
      mDescriptionTextColor = descriptionTextColor

      mBtnConfirmText = btnConfirmText
      mBtnCancelText = btnCancelText
      mBtnNeutralText = btnNeutralText

      mConfirmListener = confirmListener
      mCancelListener = cancelListener
      mNeutralListener = neutralListener

      mBtnConfirmColor = btnConfirmColor
      mBtnConfirmTextColor = btnConfirmTextColor

      mBtnCancelColor = btnCancelColor
      mBtnCancelTextColor = btnCancelTextColor

      mBtnNeutralColor = btnNeutralColor
      mBtnNeutralTextColor = btnNeutralTextColor

      mCancelable = cancelable

      mTitleTextSize = titleTextSize
      mDescriptionTextSize = descriptionTextSize

      mCircleBackgroundColor = circleBackgroundColor
      mWindowBackgroundColor = windowBackgroundColor
    }
  }

  private fun setViews() {
    tvTitle.apply {
      text = mTitle ?: "".also { tvTitle.gone() }
      setTextColor(mTitleTextColor ?: Color.BLACK)
      textSize = mTitleTextSize
    }
    tvDescription.apply {
      text = mDescription ?: "".also { tvDescription.gone() }
      setTextColor(mDescriptionTextColor ?: Color.BLACK)
      textSize = mDescriptionTextSize
    }

    this.setCancelable(mCancelable)

    btnCancel.apply {
      text = mBtnCancelText.also { btnCancel.show() } ?: "".also { btnCancel.gone() }
      setTextColor(mBtnCancelTextColor ?: Color.WHITE)
      setBackgroundColor(mBtnCancelColor ?: Color.parseColor("#008577"))
      setOnClickListener {
        it.setOnClickListener(mCancelListener)
        this@MyAlertDialog.dismiss()
      }
    }

    btnConfirm.apply {
      text = mBtnConfirmText.also { btnConfirm.show() } ?: "".also { btnConfirm.gone() }
      setTextColor(mBtnConfirmTextColor ?: Color.WHITE)
      setBackgroundColor(mBtnConfirmColor ?: Color.parseColor("#008577"))
      setOnClickListener {
        it.setOnClickListener(mConfirmListener)
        this@MyAlertDialog.dismiss()
      }
    }

    btnNeutral.apply {
      text = mBtnNeutralText.also { btnNeutral.show() } ?: "".also { btnNeutral.gone() }
      setTextColor(mBtnNeutralTextColor ?: Color.WHITE)
      setBackgroundColor(mBtnNeutralColor ?: Color.parseColor("#008577"))
      setOnClickListener {
        it.setOnClickListener(mNeutralListener)
        this@MyAlertDialog.dismiss()
      }
    }

    clSec.background.setColorFilter(
      mWindowBackgroundColor ?: Color.WHITE,
      PorterDuff.Mode.SRC_ATOP
    )
    clCircleView.background.setColorFilter(
      mCircleBackgroundColor ?: Color.WHITE,
      PorterDuff.Mode.SRC_ATOP
    )

    if (mCancelable) {
      cstViewRight.setOnClickListener {
        dismiss()
      }
      cstViewLeft.setOnClickListener {
        dismiss()
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.layout_my_alert_dialog)
    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    setViews()
  }
}