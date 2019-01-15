package com.luccasmelo.santandereveris.util

import android.graphics.Color
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.InputType
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.constraintlayout.widget.ConstraintSet
import com.luccasmelo.santandereveris.R
import com.luccasmelo.santandereveris.R.id.imageView
import com.luccasmelo.santandereveris.data.model.InvestmentInformation
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.luccasmelo.santandereveris.data.model.ContactForm
import com.luccasmelo.santandereveris.data.model.TypeField
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.GenericFont
import com.mikepenz.iconics.typeface.IIcon
import org.w3c.dom.Text
import android.text.InputFilter
import android.widget.TextView




@BindingAdapter("risk")
fun ImageView.risk(investmentInformation: InvestmentInformation?) {

    val riskId = when (investmentInformation?.screen?.risk) {
        1 -> R.id.risk_1
        2 -> R.id.invest_risk_2
        3 -> R.id.invest_risk_3
        4 -> R.id.invest_risk_4
        5 -> R.id.invest_risk_5
        else -> {
            Log.e("RISK", "Invalid risk level")
            null
        }

    }
    riskId?.let {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this.parent as ConstraintLayout?)
        constraintSet.connect(R.id.risk_arrow, ConstraintSet.BOTTOM, riskId, ConstraintSet.TOP, 0)
        constraintSet.connect(R.id.risk_arrow, ConstraintSet.END, riskId, ConstraintSet.END, 0)
        constraintSet.connect(R.id.risk_arrow, ConstraintSet.START, riskId, ConstraintSet.START, 0)
        constraintSet.setMargin(R.id.risk_arrow, ConstraintSet.BOTTOM, "5dp".toDp(resources))
        constraintSet.applyTo(this.parent as ConstraintLayout?)
    }


}

@BindingAdapter("risk")
fun View.risk(investmentInformation: InvestmentInformation?) {
    val risk = investmentInformation?.screen?.risk
    val resize =
        (risk == 1 && id == R.id.risk_1) ||
                (risk == 2 && id == R.id.invest_risk_2) ||
                (risk == 3 && id == R.id.invest_risk_3) ||
                (risk == 4 && id == R.id.invest_risk_4) ||
                (risk == 5 && id == R.id.invest_risk_5)
    if (resize) {


        layoutParams.height = "15dp".toDp(resources)


    }


}

@BindingAdapter("iconBlack")
fun ImageView.iconBlack(icon: IIcon) {
    this.background = IconicsDrawable(context)
        .icon(icon)
        .color(Color.BLACK)
        .sizeDp(16)
}

@BindingAdapter("icon")
fun ImageView.icon(icon: IIcon) {
    this.background = IconicsDrawable(context)
        .icon(icon)
        .color(ContextCompat.getColor(context,R.color.colorPrimary))
        .sizeDp(24)
}

@BindingAdapter("topSpacing")
fun View.paddingTop(topSpacing: Int?) {
    setPadding(0,topSpacing?:0,0,0)
}
@BindingAdapter("maskT")
fun EditText.maskT(cells: ContactForm.Cells){
    when(cells.typefield){
        TypeField.email->{


            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }
        TypeField.telNumber ->{
            addTextChangedListener(PhoneNumberFormattingTextWatcher())
            inputType = InputType.TYPE_CLASS_PHONE
            val maxLength = 13
            val fArray = arrayOfNulls<InputFilter>(1)
            fArray[0] = InputFilter.LengthFilter(maxLength)
            filters = fArray
        }
        TypeField.text -> {

            inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME


        }
    }
}