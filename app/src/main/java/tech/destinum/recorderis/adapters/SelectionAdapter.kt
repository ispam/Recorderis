package tech.destinum.recorderis.adapters

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import java.util.ArrayList

import tech.destinum.recorderis.R
import tech.destinum.recorderis.activities.Form
import tech.destinum.recorderis.Data.Entities.Category

class SelectionAdapter(private val mCategories: ArrayList<Category>?) : RecyclerView.Adapter<SelectionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.format_selection, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: Category = mCategories!![position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return mCategories?.size ?: 0
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mTitle = view.findViewById<View>(R.id.tv_title) as TextView
        private var mDescription = view.findViewById<View>(R.id.tv_description) as TextView
        private var mImage = view.findViewById<View>(R.id.image_category) as ImageView
        private var mCardView = view.findViewById<View>(R.id.card_view) as CardView

        fun bind(category: Category){
            mTitle.text = category.name
            mDescription.text = category.description
            mImage.setImageResource(category.image)
            mCardView.setOnClickListener { view ->
                val i = Intent(view.context, Form::class.java)
                view.context.startActivity(i)
            }
            mCardView.setBackgroundColor(Color.TRANSPARENT)
        }

    }
}
