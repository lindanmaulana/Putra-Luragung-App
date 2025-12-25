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

        val titleUsername = view.findViewById<TextView>(R.id.titleUsername)

        val actionSearch = view.findViewById<EditText>(R.id.actionSearch)
        val actionToAllBus: Button = view.findViewById(R.id.actionToAllBus)

        val session = SessionManager(requireContext())
        val email = session.getEmail()

        when {
            email == null -> {
                session.logout()
                val intent = Intent(requireActivity(), SigninAuth::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
                requireActivity().finish()
            }

            else -> {
                lifecycleScope.launch {
                    val user = db.userDao().getUserByEmail(email)
                    user?.let {
                        titleUsername.text = "${user.firstName} ${user.lastName}"
                    }
                }
            }
        }

        actionSearch.setOnClickListener {(requireActivity() as BaseActivity).navigateTo(
            BusSearchActivity::class.java)}

        actionToAllBus.setOnClickListener {
            callback.navigateTo(R.id.nav_bus)
        }
    }
}