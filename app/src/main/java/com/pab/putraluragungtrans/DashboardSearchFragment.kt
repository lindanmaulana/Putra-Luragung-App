package com.pab.putraluragungtrans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardSearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        // 4. Atur Layout Manager (contoh: list vertikal)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun createSampleBusList(): List<Bus> {
        return listOf(
            Bus("Bintang Malam", "Kuningan - Jakarta", "Rp 120.000", R.drawable.bintang_malam_bus),
            Bus("Patas Cikolkol", "Kuningan - Cikolkol", "Rp 120.000", R.drawable.patas_cikolkol_bus),
            Bus("Raka Logis Bangor", "Kuningan - Bogor", "Rp 120.000", R.drawable.raka_logis_bangor_bus),
            Bus("Pratama Termuda", "Kuningan - Bogor", "Rp 120.000", R.drawable.pratama_bus),
            Bus("Bisnis", "Kuningan - Jakarta", "Rp 120.000", R.drawable.bisnis_bus),
            Bus("Kisanak", "Kuningan - Jakarta", "Rp 120.000", R.drawable.kisanak_bus),
            Bus("Kesepuhan", "Kuningan - Jakarta", "Rp 120.000", R.drawable.kesepuhan_bus),
            Bus("SP", "Kuningan - Jakarta", "Rp 120.000", R.drawable.sp_bus)
        )
    }
}