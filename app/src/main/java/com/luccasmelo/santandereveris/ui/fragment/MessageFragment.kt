package com.luccasmelo.santandereveris.ui.fragment

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.luccasmelo.santandereveris.R
import com.luccasmelo.santandereveris.databinding.InvestmentFragmentBinding
import com.luccasmelo.santandereveris.databinding.MessageFragmentBinding

class MessageFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: MessageFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.message_fragment,container,false)
        binding.txtClickContact.setOnClickListener{
            findNavController().navigate(R.id.action_contact)
        }
        return binding.root
    }
}