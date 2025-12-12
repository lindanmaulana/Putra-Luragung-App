package com.pab.putraluragungtrans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardInvoiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invoice_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Siapkan data invoice (dummy dulu)
        val invoiceList = createSampleInvoiceList()

        // 2. Cari RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.containerRecycleViewListInvoice)

        // 3. Inisialisasi Adapter
        val invoiceAdapter = InvoiceAdapter(invoiceList) { selectedTicket ->
            // --- Logic jika item invoice diklik ---
            // Contoh navigasi:
            // val action = InvoiceFragmentDirections.actionToInvoiceDetail(selectedTicket)
            // findNavController().navigate(action)
        }

        recyclerView.adapter = invoiceAdapter

        // 4. Atur layout manager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun createSampleInvoiceList(): List<Ticket> {
        return listOf(
            Ticket(
                routeTitle = "Kuningan → Jakarta",
                detailActionText = "Detail",
                departureDate = "12 Jan 2025",

                originCity = "Kuningan",
                originTerminal = "Terminal Kuningan",
                departureTime = "07:00",

                destinationCity = "Jakarta",
                destinationTerminal = "Kp Rambutan",
                arrivalTime = "12:30",

                distanceKm = "251 KM"
            ),
            Ticket(
                routeTitle = "Cirebon → Bandung",
                detailActionText = "Detail",
                departureDate = "14 Jan 2025",

                originCity = "Cirebon",
                originTerminal = "Harjamukti",
                departureTime = "08:00",

                destinationCity = "Bandung",
                destinationTerminal = "Cicaheum",
                arrivalTime = "11:20",

                distanceKm = "134 KM"
            ),
            Ticket(
                routeTitle = "Kuningan → Bogor",
                detailActionText = "Detail",
                departureDate = "15 Jan 2025",

                originCity = "Kuningan",
                originTerminal = "Terminal Kuningan",
                departureTime = "05:30",

                destinationCity = "Bogor",
                destinationTerminal = "Baranangsiang",
                arrivalTime = "10:45",

                distanceKm = "210 KM"
            )
        )
    }


}