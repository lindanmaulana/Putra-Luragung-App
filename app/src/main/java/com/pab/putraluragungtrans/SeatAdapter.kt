package com.pab.putraluragungtrans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pab.putraluragungtrans.R

class SeatAdapter(
    private val seatList: List<Seat>,
    private val onSeatClick: (Seat) -> Unit
) : RecyclerView.Adapter<SeatAdapter.SeatViewHolder>() {

    // ViewHolder menggunakan findViewById
    class SeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewSeat: View = itemView.findViewById(R.id.viewSeat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        // Inflate menggunakan layout R langsung
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_seat, parent, false)
        return SeatViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        val seat = seatList[position]

        // Logika Lorong (kolom ke-3)
        if (position % 5 == 2) {
            holder.itemView.visibility = View.INVISIBLE
            holder.itemView.isEnabled = false
        } else {
            holder.itemView.visibility = View.VISIBLE
            holder.itemView.isEnabled = true

            // Set warna manual ke viewSeat
            when {
                seat.isBooked -> {
                    holder.viewSeat.setBackgroundResource(R.drawable.seat_booked_icon)
                    holder.itemView.isEnabled = false
                }
                seat.isSelected -> {
                    holder.viewSeat.setBackgroundResource(R.drawable.seat_selected_icon)
                }
                else -> {
                    holder.viewSeat.setBackgroundResource(R.drawable.seat_icon)
                }
            }

            holder.itemView.setOnClickListener {
                onSeatClick(seat)
            }
        }
    }

    override fun getItemCount(): Int = seatList.size
}