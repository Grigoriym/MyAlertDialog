package com.grappim.myalertdialog

import android.view.View

fun View.gone(): View {
  if (visibility != View.GONE) {
    visibility = View.GONE
  }
  return this
}

fun View.hide(): View {
  if (visibility != View.INVISIBLE) {
    visibility = View.INVISIBLE
  }
  return this
}

fun View.show(): View {
  if (visibility != View.VISIBLE) {
    visibility = View.VISIBLE
  }
  return this
}