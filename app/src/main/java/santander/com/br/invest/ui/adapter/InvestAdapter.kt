package santander.com.br.invest.ui.adapter

import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_info_investment.view.*
import santander.com.br.invest.R
import santander.com.br.invest.extension.visible
import santander.com.br.invest.model.Info

class InvestAdapter(private val infoList: ArrayList<Info>) : RecyclerView.Adapter<InvestAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info_investment, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(infoList[position])
  }

  override fun getItemCount() = infoList.size

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(info: Info) {
      itemView.txtTitle.text = info.name
      if (info.data != null) {
        itemView.txtDescription.text = info.data
      } else {
        itemView.txtDescription.text = "Baixar"
        itemView.imgDownload.visible()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          itemView.txtDescription.setTextAppearance(R.style.regular_14_red)
        } else {
          itemView.txtDescription.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
        }
      }
    }
  }

}
