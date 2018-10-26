package br.com.andreyneto.testesantander.ui.invest

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreyneto.testesantander.R
import br.com.andreyneto.testesantander.model.Info
import br.com.andreyneto.testesantander.model.MoreInfo
import br.com.andreyneto.testesantander.toPercentage
import kotlinx.android.synthetic.main.list_more_info.view.*

class MoreInfoAdapter(private val moreInfo: MoreInfo,
                      private val context: Context) : RecyclerView.Adapter<MoreInfoAdapter.ViewHolder>() {
    private var infos: List<Info> = listOf(
            Info(context.getString(R.string.mes), moreInfo.month.fund, moreInfo.month.CDI),
            Info(context.getString(R.string.ano), moreInfo.year.fund, moreInfo.year.CDI),
            Info(context.getString(R.string.last), moreInfo.twelveMonths.fund, moreInfo.twelveMonths.CDI)
    )

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_more_info, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val info = infos[p1]
        p0.bindView(info)
    }

    override fun getItemCount(): Int {
        return infos.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(info: Info) {
            itemView.more_info_data.text = info.data?.toPercentage()
            itemView.more_info_fund.text = info.fund.toPercentage()
            itemView.more_info_title.text = info.name as String
        }
    }
}