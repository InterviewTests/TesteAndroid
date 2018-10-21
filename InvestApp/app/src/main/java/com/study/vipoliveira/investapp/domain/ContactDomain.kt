package com.study.vipoliveira.investapp.domain

import com.study.vipoliveira.investapp.data.network.contact.ContactDataSource
import com.study.vipoliveira.investapp.data.network.contact.entities.ContactResponse
import com.study.vipoliveira.investapp.data.network.investment.InvestmentDataSource
import com.study.vipoliveira.investapp.data.network.investment.entities.InvestResponse
import io.reactivex.Single

class ContactDomain(private val dataSource: ContactDataSource) {
    fun requestInvestment(): Single<ContactResponse>{
       return dataSource.getContact()
    }
}