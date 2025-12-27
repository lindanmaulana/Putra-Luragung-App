package com.pab.putraluragungtrans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DashboardAccountFragment : Fragment() {
    private lateinit var db: AppDatabase
    private lateinit var session: SessionManager

    private lateinit var profileName: TextView
    private lateinit var profileEmail: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getDatabase(requireContext())
        session = SessionManager(requireContext())

        val actionProfileAccount = view.findViewById<View>(R.id.actionProfileAccount)
        val actionChangePassword = view.findViewById<View>(R.id.actionChangePassword)
        val actionLogout = view.findViewById<View>(R.id.actionLogout)

        profileName = view.findViewById<TextView>(R.id.profileName)
        profileEmail = view.findViewById<TextView>(R.id.profileEmail)

        actionProfileAccount.setOnClickListener {(requireActivity() as BaseActivity).navigateTo(
            AccountProfileActivity::class.java)}

        actionChangePassword.setOnClickListener {
            (requireActivity() as BaseActivity).navigateTo(AccountChangePasswordActivity::class.java)}

        actionLogout.setOnClickListener {
            session.logout()

            (requireActivity() as BaseActivity).navigateTo(SigninAuth::class.java, isFinal = true)
        }
    }

    override fun onResume() {
        super.onResume()

        val emailUser = session.getEmail()
        if (emailUser == null) {
            if (isAdded) {
                session.logout()
                (activity as? BaseActivity)?.navigateTo(SigninAuth::class.java, isFinal = true)
            }
            return
        }

        lifecycleScope.launch {
            val dataUser = db.userDao().getUserByEmail(emailUser)

            if (isAdded && dataUser != null) {
                profileName.text = "${dataUser.firstName} ${dataUser.lastName}"
                profileEmail.text = dataUser.email
            }
        }
    }
}