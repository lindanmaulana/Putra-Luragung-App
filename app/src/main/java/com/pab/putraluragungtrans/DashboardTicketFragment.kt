package com.pab.putraluragungtrans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardTicketFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Siapkan data invoice (dummy dulu)
        val invoiceList = createSampleInvoiceList()

        // 2. Cari RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.containerRecycleViewListTicket)

        // 3. Inisialisasi Adapter
        val invoiceAdapter = TicketAdapter(invoiceList) { selectedTicket ->
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
                busName = "Bintang Malam",
                detailActionText = "Detail",
                departureDate = "12 Jan 2025",

                originCity = "Kuningan",
                originTerminal = "Terminal Kertawangunan",
                departureTime = "07:00",

                destinationCity = "Jakarta",
                destinationTerminal = "Kp Rambutan",
                arrivalTime = "12:30",
            ),
            Ticket(
                busName = "Patas Cikolkol",
                detailActionText = "Detail",
                departureDate = "14 Jan 2025",

                originCity = "Kuningan",
                originTerminal = "Terminal Kertawangunan",
                departureTime = "08:00",

                destinationCity = "Tanggerang",
                destinationTerminal = "Terminal Mandala",
                arrivalTime = "11:20",
            ),
            Ticket(
                busName = "Raka Logis Bangor",
                detailActionText = "Detail",
                departureDate = "15 Jan 2025",

                originCity = "Kuningan",
                originTerminal = "Terminal Kertawangunan",
                departureTime = "05:30",

                destinationCity = "Bogor",
                destinationTerminal = "Baranangsiang",
                arrivalTime = "10:45",
            )
        )
    }


}