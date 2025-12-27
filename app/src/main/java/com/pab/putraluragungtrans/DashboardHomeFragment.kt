package com.pab.putraluragungtrans

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DashboardHomeFragment : Fragment() {
    private lateinit var callback: NavigationCallbackDashboard
    private lateinit var db: AppDatabase
    private lateinit var session: SessionManager
    private lateinit var titleUsername: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationCallbackDashboard) {
            callback = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getDatabase(requireContext())
        titleUsername = view.findViewById<TextView>(R.id.titleUsername)
        session = SessionManager(requireContext())

        val actionSearch = view.findViewById<EditText>(R.id.actionSearch)
        val actionToAllBus: Button = view.findViewById(R.id.actionToAllBus)

        actionSearch.setOnClickListener {(requireActivity() as BaseActivity).navigateTo(
            BusSearchActivity::class.java)}

        actionToAllBus.setOnClickListener {
            callback.navigateTo(R.id.nav_bus)
        }
    }

    override fun onResume() {
        super.onResume()

        val userEmail = session.getEmail()

        if (userEmail == null) {
            session.logout()
            (requireActivity() as BaseActivity).navigateTo(SigninAuth::class.java, isFinal = true)
            return
        }

        lifecycleScope.launch {
            val user = db.userDao().getUserByEmail(userEmail)

            if (isAdded && user != null) titleUsername.text = "${user.firstName} ${user.lastName}"
        }
    }
}