package com.pab.putraluragungtrans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InvoiceAdapter(
    private val listTicket: List<Ticket>,
    private val clickListener: (Ticket) -> Unit
) : RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder>() {

    class InvoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvRouteTitle: TextView = itemView.findViewById(R.id.tvRouteTitle)
        val tvDetailAction: TextView = itemView.findViewById(R.id.tvDetailAction)
        val tvDepartureDate: TextView = itemView.findViewById(R.id.tvDepartureDate)

        val tvOriginCity: TextView = itemView.findViewById(R.id.tvOriginCity)
        val tvOriginTerminal: TextView = itemView.findViewById(R.id.tvOriginTerminal)
        val tvDepartureTime: TextView = itemView.findViewById(R.id.tvDepartureTime)

        val tvDestinationCity: TextView = itemView.findViewById(R.id.tvDestinationCity)
        val tvDestinationTerminal: TextView = itemView.findViewById(R.id.tvDestinationTerminal)
        val tvArrivalTime: TextView = itemView.findViewById(R.id.tvArrivalTime)

        val tvDistance: TextView = itemView.findViewById(R.id.tvDistance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_invoice, parent, false)
        return InvoiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: InvoiceViewHolder, position: Int) {
        val item = listTicket[position]

        holder.tvRouteTitle.text = item.routeTitle
        holder.tvDetailAction.text = item.detailActionText
        holder.tvDepartureDate.text = item.departureDate

        holder.tvOriginCity.text = item.originCity
        holder.tvOriginTerminal.text = item.originTerminal
        holder.tvDepartureTime.text = item.departureTime

        holder.tvDestinationCity.text = item.destinationCity
        holder.tvDestinationTerminal.text = item.destinationTerminal
        holder.tvArrivalTime.text = item.arrivalTime

        holder.tvDistance.text = item.distanceKm

        // Klik item
        holder.itemView.setOnClickListener {
            clickListener(item)
        }

        // Klik tombol detail
        holder.tvDetailAction.setOnClickListener {
            clickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return listTicket.size
    }
}
