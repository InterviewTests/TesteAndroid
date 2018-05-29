package andcordeiro.com.br.testeandroid.histories.contact

import andcordeiro.com.br.testeandroid.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_message_sucess.*


class MessageSucessFragment : Fragment() {

    companion object {
        fun newInstance(): MessageSucessFragment = MessageSucessFragment()
    }

    override fun onResume() {
        super.onResume()
        newMessage.setOnClickListener {
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(this.id, ContactFragment.newInstance())
            fragmentTransaction.commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_message_sucess, container, false)

}
