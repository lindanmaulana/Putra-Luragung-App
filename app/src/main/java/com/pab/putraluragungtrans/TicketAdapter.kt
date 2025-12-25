package com.pab.putraluragungtrans

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pab.putraluragungtrans.BusAdapter.BusViewHolder

class TicketAdapter(
    private val ticketList: List<Ticket>,
    private val clickListener: (Ticket) -> Unit
) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvBusName: TextView = itemView.findViewById(R.id.tvBusName)
        val tvDetailAction: TextView = itemView.findViewById(R.id.tvDetailAction)
        val tvDepartureDate: TextView = itemView.findViewById(R.id.tvDepartureDate)

        val tvOriginCity: TextView = itemView.findViewById(R.id.tvOriginCity)
        val tvOriginTerminal: TextView = itemView.findViewById(R.id.tvOriginTerminal)
        val tvDepartureTime: TextView = itemView.findViewById(R.id.tvDepartureTime)

        val tvDestinationCity: TextView = itemView.findViewById(R.id.tvDestinationCity)
        val tvDestinationTerminal: TextView = itemView.findViewById(R.id.tvDestinationTerminal)
        val tvArrivalTime: TextView = itemView.findViewById(R.id.tvArrivalTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_ticket_list, parent, false)
        return TicketViewHolder(view)
    }


    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val item = ticketList[position]

        holder.tvBusName.text = item.busName
        holder.tvDepartureDate.text = item.departureDate

        holder.tvOriginCity.text = item.originCity
        holder.tvOriginTerminal.text = item.originTerminal
        holder.tvDepartureTime.text = item.departureTime

        holder.tvDestinationCity.text = item.destinationCity
        holder.tvDestinationTerminal.text = item.destinationTerminal
        holder.tvArrivalTime.text = item.arrivalTime

        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, TicketDetail::class.java)
            intent.putExtra("EXTRA_TICKET", ticketList[position])
            context.startActivity(intent)

            clickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }
}
