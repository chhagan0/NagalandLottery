package com.papayacoders.nagalandlotterysambadresult.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.papayacoders.nagalandlotterysambadresult.R
import com.papayacoders.nagalandlotterysambadresult.ShowOldResult
import com.papayacoders.nagalandlotterysambadresult.config.Oldresult
import com.papayacoders.nagalandlotterysambadresult.result.SingleOldresult2
import com.papayacoders.nagalandlotterysambadresult.result.SingleResult

class MyAdapter(val userList:ArrayList<Oldresult>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var db = Firebase.firestore
    var result1=String
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
        holder.datee.text = userList[position].text
        holder.res1.text = userList[position].image1
        holder.res2.text = userList[position].image2
        holder.res3.text = userList[position].image3

        holder.itemView.findViewById<MaterialCardView>(R.id.btnonepm).setOnClickListener {
            val intent = Intent(holder.itemView.context, SingleResult::class.java)
            intent.putExtra("result", userList[position].image1)
            holder.itemView.context.startActivity(intent)
            d("CHHAGANnb","Clicked ${userList[position].image1}")
        }


        holder.itemView.findViewById<MaterialCardView>(R.id.btnsixpm).setOnClickListener {
            val intent = Intent(holder.itemView.context, SingleOldresult2::class.java)
            intent.putExtra("result1",userList[position].image2)
            holder.itemView.context.startActivity(intent)
            d("CHHAGANnb","Clicked2 ${userList[position].image2}")
        }
        holder.itemView.findViewById<MaterialCardView>(R.id.btneightpm).setOnClickListener {
            val intent = Intent(holder.itemView.context, SingleResult::class.java)
            intent.putExtra("result", userList[position].image3)

            holder.itemView.context.startActivity(intent)
        }
//        holder.itemView.findViewById<MaterialCardView>(R.id.itemcrd).setOnClickListener {
//            val intent = Intent(holder.itemView.context, ShowOldResult::class.java)
//            intent.putExtra("result1", userList[position].image1)
//            intent.putExtra("result2", userList[position].image2)
//            intent.putExtra("result3", userList[position].image3)
//            holder.itemView.context.startActivity(intent)
////        }
//        }
//    private fun retrieveDataFromFirestore(  context: Context) {
//        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
//        val documentRef = firestore.collection("images")
//
//        documentRef.get()
//            .addOnSuccessListener { it ->
//
//                for (data in it.documents) {
//                    val dataa = data.id
//                    val item = Oldresult(data.id, data.getString("text") ?: "")
//                    Log.d("CHAGANN", "db Success: ${item.image1}")
//val result1=Oldresult(data.id,data.getString("image1"))
//
//
//                        val intent = Intent(context, SingleResult::class.java)
//    intent.putExtra("result",result1.toString())
//
//     context.startActivity(intent)
//                }
//
//            }
//            .addOnFailureListener { exception ->
//                Toast.makeText(context, "Error retrieving data: ${exception.message}", Toast.LENGTH_SHORT).show()
//            }
//    }

    }
}