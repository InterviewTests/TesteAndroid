package com.anabhomasi.androidapp.views.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.anabhomasi.androidapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TITLE = "title"
private const val ARG_PAGE = "page"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FundFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FundFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FundFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var title: String? = null
    private var page: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            page = it.getInt(ARG_PAGE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fund, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(page: Int) {
        listener?.onFragmentInteraction(page)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(page: Int)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FundFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(title: String, page: Int) =
                FundFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_TITLE, title)
                        putInt(ARG_PAGE, page)
                    }
                }
    }
}
