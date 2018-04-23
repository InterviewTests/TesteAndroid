package com.vctapps.santanderchallenge.asset.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.asset.presentation.model.HeaderInfos
import com.vctapps.santanderchallenge.asset.presentation.model.Info
import com.vctapps.santanderchallenge.asset.presentation.model.MoreInfos
import com.vctapps.santanderchallenge.asset.presentation.model.RiskInfo
import com.vctapps.santanderchallenge.asset.presentation.presenter.AssetPresenter
import com.vctapps.santanderchallenge.core.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_asset.*
import javax.inject.Inject

class AssetFragment: BaseFragment(), AssetView {

    @Inject
    lateinit var presenter: AssetPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_asset, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter.onStart()
    }

    override fun onDestroy() {
        presenter.onPause()

        super.onDestroy()
    }

    override fun setHeaderInfos(headerInfo: HeaderInfos) {
        headerView.setInfos(headerInfo)
    }

    override fun setRiskInfos(riskInfo: RiskInfo) {
        riskScale.setRiskInfo(riskInfo)
    }

    override fun setMoreInfos(moreInfos: MoreInfos) {
        moreInfo.setMoreInfos(moreInfos)
    }

    override fun setListInfos(infos: MutableList<Info>) {
        simpleInfoList.setSimpleInfos(infos)
    }

    override fun setDownInfos(infos: MutableList<Info>) {
        infoWithLink.setInfosWithLink(infos)
    }

    override fun showLoading() {
        super.showLoading()

        assetDetailView.visibility = View.GONE
    }

    override fun hideLoading() {
        super.hideLoading()

        assetDetailView.visibility = View.VISIBLE
    }

    override fun showError() {
        super.showError()

        assetDetailView.visibility = View.GONE
    }

    override fun hideError() {
        super.hideError()

        assetDetailView.visibility = View.VISIBLE
    }
}