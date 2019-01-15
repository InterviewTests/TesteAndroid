package com.luccasmelo.santandereveris.ui.fragment

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.luccasmelo.santandereveris.R
import com.luccasmelo.santandereveris.data.model.ContactForm
import com.luccasmelo.santandereveris.databinding.ContactFragmentBinding
import com.luccasmelo.santandereveris.databinding.MessageFragmentBinding
import com.luccasmelo.santandereveris.ui.adapter.ContactAdapter
import com.luccasmelo.santandereveris.view_model.ContactViewModel
import java.util.*

class ContactFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ContactFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.contact_fragment, container, false)
        val viewModel = ContactViewModel(findNavController())
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        viewModel.contactForm.observe(this, androidx.lifecycle.Observer { contactForm ->
            setupLayout(binding, contactForm, viewModel)
        })
        return binding.root
    }

    private fun setupLayout(
        binding: ContactFragmentBinding,
        contactForm: ContactForm,
        viewModel: ContactViewModel
    ) {
        binding.rcvContact.layoutManager = LinearLayoutManager(context)
        val contactAdapter = ContactAdapter(activity as AppCompatActivity, contactForm.cells!!)
        binding.rcvContact.adapter = contactAdapter
        contactAdapter.sendLister = {
            viewModel.sendClick()
        }
    }
}