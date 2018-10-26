package br.com.andreyneto.testesantander.ui.invest

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.andreyneto.testesantander.R
import br.com.andreyneto.testesantander.model.Info
import kotlinx.android.synthetic.main.list_info.view.*

class InfoAdapter(private val infos: List<Info>,
                  private val context: Context,
                  private val presenter: InvestContract.Presenter?) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_info, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val info = infos[p1]
        p0.bindView(info, context, presenter)
    }

    override fun getItemCount(): Int {
        return infos.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(info: Info, ctx: Context, presenter: InvestContract.Presenter?) {
            info.data?.let { itemView.info_data.text = it as String? } ?: setDownload(info.name as String, ctx, presenter)
            itemView.info_title.text = info.name as String
        }

        private fun setDownload(name: String, ctx: Context, presenter: InvestContract.Presenter?){
            itemView.info_download.visibility = View.VISIBLE
            itemView.info_data.text = ctx.getString(R.string.baixar)
            itemView.info_data.setTextColor(
                    ContextCompat.getColor(ctx,R.color.colorPrimary))
            itemView.setOnClickListener {
                presenter?.toast(ctx, name)
            }
        }
    }
}