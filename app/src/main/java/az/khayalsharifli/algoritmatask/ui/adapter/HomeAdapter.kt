package az.khayalsharifli.algoritmatask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import az.khayalsharifli.algoritmatask.base.BaseDiffUtil
import az.khayalsharifli.algoritmatask.databinding.ItemHomeBinding
import az.khayalsharifli.algoritmatask.model.LocalDto

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var emptyList = emptyList<LocalDto>()

    class HomeViewHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): HomeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHomeBinding.inflate(layoutInflater, parent, false)
                return HomeViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent)
    }

    override fun getItemCount() = emptyList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.textView.text = emptyList[position].mainSting
    }

    fun setData(newList: List<LocalDto>) {
        val diffUtil = BaseDiffUtil(emptyList, newList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        emptyList = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }
}