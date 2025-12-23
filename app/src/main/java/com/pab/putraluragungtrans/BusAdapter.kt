package com.pab.putraluragungtrans

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BusAdapter(
    private val busList: List<Bus>,
    private val clickListener: (Bus) -> Unit
) : RecyclerView.Adapter<BusAdapter.BusViewHolder>() {

    class BusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageBus: ImageView = itemView.findViewById(R.id.imageBus)
        val textBusName: TextView = itemView.findViewById(R.id.textBusName)
        val textBusRoute: TextView = itemView.findViewById(R.id.textBusRoute)
        val textBusPrice: TextView = itemView.findViewById(R.id.textBusPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_bus_list, parent, false)
        return BusViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        val currentBus = busList[position]

        holder.textBusName.text = currentBus.name
        holder.textBusRoute.text = currentBus.route
        holder.textBusPrice.text = currentBus.price

        holder.imageBus.setImageResource(currentBus.ImageUrl)

        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, BusDetailActivity::class.java)
            intent.putExtra("EXTRA_BUS", busList[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return busList.size
    }
}