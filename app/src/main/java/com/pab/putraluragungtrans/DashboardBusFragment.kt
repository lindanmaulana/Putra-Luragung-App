package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardBusFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionSearchBar = view.findViewById<View>(R.id.actionSearchBar)

        actionSearchBar.setOnClickListener {
            val intent = Intent(requireActivity(), BusSearchActivity::class.java)

            startActivity(intent)
        }

        // 1. Siapkan data bus
        val sampleBusList = createSampleBusList()

        // 2. Cari RecyclerView di dalam layout fragment
        // Pastikan ID di fragment_search_dashboard.xml adalah 'recyclerViewBusList'
        val recyclerView = view.findViewById<RecyclerView>(R.id.containerRecycleViewListBus)

        // 3. Inisialisasi dan pasang Adapter
        val busAdapter = BusAdapter(sampleBusList) { selectedBus ->
            // --- Logic saat item bus diklik ---
            // Contoh: Navigasi ke Detail Bus, menggunakan objek selectedBus (Parcelable)
            // (Anda bisa menambahkan Intent atau Navigasi di sini)

            // Contoh penggunaan:
            // val action = SearchDashboardFragmentDirections.actionToDetailBus(selectedBus)
            // findNavController().navigate(action)
        }

        recyclerView.adapter = busAdapter

        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun createSampleBusList(): List<Bus> {
        return listOf(
            Bus("Bintang Malam", "Kuningan - Jakarta", "Rp 120.000", R.drawable.bus_bintangmalam),
            Bus("Patas Cikolkol", "Kuningan - Cikolkol", "Rp 120.000", R.drawable.bus_patas_cikokol),
            Bus("Raka Logis Bangor", "Kuningan - Bogor", "Rp 120.000", R.drawable.bus_raka_logis_bangor),
            Bus("Pratama Termuda", "Kuningan - Bogor", "Rp 120.000", R.drawable.bus_pratama_jb3plus),
            Bus("Bisnis", "Kuningan - Jakarta", "Rp 120.000", R.drawable.bus_bisnis),
            Bus("Kisanak", "Kuningan - Jakarta", "Rp 120.000", R.drawable.bus_kisanak),
            Bus("Kesepuhan", "Kuningan - Jakarta", "Rp 120.000", R.drawable.bus_kesepuhan),
            Bus("SP", "Kuningan - Jakarta", "Rp 120.000", R.drawable.bus_sp),
            Bus("Andalas", "Kuningan - Harapan Indah", "Rp 120.000", R.drawable.bus_andalas),
            Bus("Al Zidan", "Kuningan - Lebak Bulus", "Rp 100.000", R.drawable.bus_alzidan),
            Bus("Termuda Slonjor", "Kuningan - Cikokol", "Rp 120.000", R.drawable.bus_slonjor),
            Bus("Termuda Galaxy", "Kuningan - Bogor", "Rp 120.000", R.drawable.bus_galaxy)
        )
    }
}