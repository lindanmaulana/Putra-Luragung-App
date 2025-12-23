package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DashboardAccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionProfileAccount = view.findViewById<View>(R.id.actionProfileAccount)
        val actionLogout = view.findViewById<View>(R.id.actionLogout)

        actionProfileAccount.setOnClickListener {
            val intent = Intent(requireActivity(), AccountProfileActivity::class.java)

            startActivity(intent)
        }

        actionLogout.setOnClickListener {
            val session = SessionManager(requireContext())
            session.logout()

            val intent = Intent(requireActivity(), SigninAuth::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            requireActivity().finish()
        }
    }
}