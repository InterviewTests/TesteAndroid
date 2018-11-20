package com.galdino.testandroid.plataform.views.home

import android.support.v4.app.Fragment
import android.view.View
import com.galdino.testandroid.R
import com.galdino.testandroid.plataform.views.BaseActivity
import com.galdino.testandroid.plataform.views.contact.ContactFragment
import com.galdino.testandroid.plataform.views.investment.InvestmentFragment
import com.galdino.testandroid.util.MyAnimationUtils
import kotlinx.android.synthetic.main.form_activity.*
import org.koin.android.ext.android.inject

class HomeActivity : BaseActivity(), HomeContract.View, View.OnClickListener
{
    private val mPresenter: HomeContract.Presenter by inject()
    private var mInvestmentFragment: InvestmentFragment? = null
    private val mContactFragment: ContactFragment = ContactFragment.newInstance()
    override fun getLayoutResource(): Int {
        return R.layout.form_activity
    }

    override fun onInitView() {
        mPresenter.attach(this)
        loadListeners()
        mPresenter.initialize()
    }

    private fun loadListeners() {
        btInvestment.setOnClickListener(this)
        btContact.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            btInvestment.id-> mPresenter.onInvestmentClicked()
            btContact.id-> mPresenter.onContactClicked()
        }
    }

    override fun inflateInvestment() {
        MyAnimationUtils.translateShow(btInvestmentPressed, this,null,null, null)
        MyAnimationUtils.translateHide(btContactPressed, this,null,null, null)

        if(mInvestmentFragment == null)
        {
            mInvestmentFragment = InvestmentFragment.newInstance()
        }
        inflateFragment(mInvestmentFragment!!)
    }

    override fun inflateContact() {
        MyAnimationUtils.translateShow(btContactPressed, this,null,null, null)
        MyAnimationUtils.translateHide(btInvestmentPressed, this,null,null, null)
//        if(mContactFragment == null)
//        {
//            mContactFragment = ContactFragment.newInstance()
//        }
        inflateFragment(mContactFragment)
    }

    private fun inflateFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(flRoot.id, fragment)
                .commit()
    }
}
