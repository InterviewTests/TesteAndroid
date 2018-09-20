package santander.com.br.invest.contract

import android.os.Bundle

interface PresenterFragment<in V> {
  fun onCreate(savedInstanceState: Bundle?)
  fun bindView(view: V)
}
