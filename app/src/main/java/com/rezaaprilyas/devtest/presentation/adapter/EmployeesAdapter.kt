package com.rezaaprilyas.devtest.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rezaaprilyas.devtest.databinding.ItemEmployeeBinding
import com.rezaaprilyas.devtest.domain.model.EmployeesModel
import com.rezaaprilyas.devtest.utils.extensions.toRupiah

class EmployeesAdapter : RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {
    private var list = arrayListOf<EmployeesModel>()
    private var onItemClickListener: ((employee: EmployeesModel, position: Int) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: (employee: EmployeesModel, position: Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemBinding =
            ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = list[position]
        holder.bind(employee, position)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listItem: ArrayList<EmployeesModel>) {
        this.list = listItem
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EmployeesModel, position: Int) {
            with(employee) {
                renderTextView(this)

            }
            binding.cvItemEmployee.setOnClickListener {
                onItemClickListener?.invoke(employee, position)
            }
        }

        private fun renderTextView(employee: EmployeesModel) {
            with(binding) {
                itemEmployeeName.text = employee.name.uppercase()
                itemEmployeeId.text = employee.id
                itemEmployeeSalary.text = employee.salary.toRupiah()
                itemEmployeeAge.text = employee.age
            }
        }
    }
}