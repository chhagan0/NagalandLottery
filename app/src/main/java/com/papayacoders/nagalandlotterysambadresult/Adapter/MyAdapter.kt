package com.papayacoders.nagalandlotterysambadresult.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.papayacoders.nagalandlotterysambadresult.MainActivity
import com.papayacoders.nagalandlotterysambadresult.R
import com.papayacoders.nagalandlotterysambadresult.ShowOldResult
import com.papayacoders.nagalandlotterysambadresult.config.Oldresult
import com.papayacoders.nagalandlotterysambadresult.result.SingleResult

class MyAdapter(val userList:ArrayList<Oldresult>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val datee:TextView=itemView.findViewById(R.id.date)
        val res1:TextView=itemView.findViewById(R.id.eightpmid)
        val res2:TextView=itemView.findViewById(R.id.sixpmid)
        val res3:TextView=itemView.findViewById(R.id.eightpmid)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
val itemView=LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler,parent,false )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
return userList.size   }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
holder.datee.text =userList[position].date
 holder.res1.text=userList[position].result1
 holder.res2.text=userList[position].result2
 holder.res3.text=userList[position].result3
holder.itemView.findViewById<MaterialCardView>(R.id.btnonepm).setOnClickListener {
    val intent = Intent(holder.itemView.context, SingleResult::class.java)
    intent.putExtra("result", userList[position].result1)

    holder.itemView.context.startActivity(intent)
}
holder.itemView.findViewById<MaterialCardView>(R.id.btnsixpm).setOnClickListener {
    val intent = Intent(holder.itemView.context, SingleResult::class.java)
    intent.putExtra("result", userList[position].result2)

    holder.itemView.context.startActivity(intent)
}
holder.itemView.findViewById<MaterialCardView>(R.id.btneightpm).setOnClickListener {
    val intent = Intent(holder.itemView.context, SingleResult::class.java)
    intent.putExtra("result", userList[position].result3)

    holder.itemView.context.startActivity(intent)
}
        holder.itemView.findViewById<MaterialCardView>(R.id.itemcrd ).setOnClickListener {
            val intent = Intent(holder.itemView.context, ShowOldResult::class.java)
            intent.putExtra("result1", userList[position].result1)
            intent.putExtra("result2", userList[position].result3)
            intent.putExtra("result3", userList[position].result3)
             holder.itemView.context.startActivity(intent)
        }
    }


}