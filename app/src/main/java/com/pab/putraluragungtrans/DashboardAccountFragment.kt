package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.se.omapi.Session
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DashboardAccountFragment : Fragment() {
    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getDatabase(requireContext())
        val session = SessionManager(requireContext())
        val emailUser = session.getEmail()

        val actionProfileAccount = view.findViewById<View>(R.id.actionProfileAccount)
        val actionChangePassword = view.findViewById<View>(R.id.actionChangePassword)
        val actionLogout = view.findViewById<View>(R.id.actionLogout)

        val profileName = view.findViewById<TextView>(R.id.profileName)
        val profileEmail = view.findViewById<TextView>(R.id.profileEmail)

        actionProfileAccount.setOnClickListener {(requireActivity() as BaseActivity).navigateTo(
            AccountProfileActivity::class.java)}

        actionChangePassword.setOnClickListener {
            (requireActivity() as BaseActivity).navigateTo(AccountChangePasswordActivity::class.java)}

        actionLogout.setOnClickListener {
            session.logout()

            val intent = Intent(requireActivity(), SigninAuth::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            requireActivity().finish()
        }

        lifecycleScope.launch {
            if (emailUser == null) {
                if (isAdded) {
                    session.logout()
                    (activity as? BaseActivity)?.navigateTo(SigninAuth::class.java, isFinal = true)
                }
                return@launch
            }

            val dataUser = db.userDao().getUserByEmail(emailUser)

            if (isAdded && dataUser != null) {
                profileName.text = "${dataUser.firstName} ${dataUser.lastName}"
                profileEmail.text = dataUser.email
            }
        }
    }
}