package santander.com.br.invest.extension

import android.view.View

fun View.visible() {
  this.visibility = View.VISIBLE
}

fun View.gone() {
  this.visibility = View.GONE
}

fun View.invisible() {
  this.visibility = View.INVISIBLE
}
