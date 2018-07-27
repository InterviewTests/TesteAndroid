package com.rafhack.testeandroid.investment.riskView

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.constraint.ConstraintSet.PARENT_ID
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.rafhack.testeandroid.R

class RiskView : ConstraintLayout {

    var risk = 1
        set(value) {
            field = value
            updateRiskIndicator()
        }

    private lateinit var riskMap: HashMap<Int, View>
    private lateinit var risk1: View
    private lateinit var risk2: View
    private lateinit var risk3: View
    private lateinit var risk4: View
    private lateinit var risk5: View
    private lateinit var indicator: TextView

    constructor(context: Context) : this(context, null) {
        if (isInEditMode) setupView()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        if (isInEditMode) setupView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setupView()
    }

    private fun setupView() {
        View.inflate(context, R.layout.risk_view, this)
        risk1 = findViewById(R.id.risk_view_risk_1)
        risk2 = findViewById(R.id.risk_view_risk_2)
        risk3 = findViewById(R.id.risk_view_risk_3)
        risk4 = findViewById(R.id.risk_view_risk_4)
        risk5 = findViewById(R.id.risk_view_risk_5)
        indicator = findViewById(R.id.risk_view_tvw_indicator)
        riskMap = hashMapOf(Pair(1, risk1), Pair(2, risk2), Pair(3, risk3), Pair(4, risk4), Pair(5, risk5))
    }

    private fun updateRiskIndicator() {
        riskMap.values.forEach {
            (it.layoutParams as LinearLayout.LayoutParams).height =
                    resources.getDimensionPixelSize(R.dimen.risk_disabled)
        }

        (riskMap[risk]?.layoutParams as LinearLayout.LayoutParams).height =
                resources.getDimensionPixelSize(R.dimen.risk_enabled)

        val set = ConstraintSet()
        val id = R.id.risk_view_tvw_indicator
        val index = riskMap.values.indexOf(riskMap[risk]!!)
        set.clone(this.getChildAt(0) as ConstraintLayout)
        set.setHorizontalBias(id, ((index + index + 1) * 10).toFloat() / 100)
        set.applyTo(this.getChildAt(0) as ConstraintLayout)
    }
}