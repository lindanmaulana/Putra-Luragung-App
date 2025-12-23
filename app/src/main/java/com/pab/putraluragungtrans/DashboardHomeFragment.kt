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

class DashboardHomeFragment : Fragment() {
    private lateinit var callback: NavigationCallbackDashboard

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is NavigationCallbackDashboard) {
            callback = context
        } else {
            throw RuntimeException("$context must implement NavigationCallback")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionSearch = view.findViewById<EditText>(R.id.actionSearch)
        val actionToAllBus: Button = view.findViewById(R.id.actionToAllBus)

        actionSearch.setOnClickListener {
            val intent = Intent(requireActivity(), BusSearchActivity::class.java)

            startActivity(intent)
        }

        actionToAllBus.setOnClickListener {
            callback.navigateTo(R.id.nav_bus)
        }
    }
}