package com.rezaaprilyas.devtest.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rezaaprilyas.devtest.R
import com.rezaaprilyas.devtest.databinding.FragmentDialogActionEmployeeBinding
import com.rezaaprilyas.devtest.domain.model.EmployeesModel
import com.rezaaprilyas.devtest.utils.extensions.serializable

class ActionEmployeeDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentDialogActionEmployeeBinding
    private var employees: EmployeesModel? = null

    private var onAddItemClickListener: ((employee: EmployeesModel, add: Boolean) -> Unit)? = null
    fun setOnAddItemClickListener(onItemClickListener: (employee: EmployeesModel, add: Boolean) -> Unit) {
        this.onAddItemClickListener = onItemClickListener
    }

    private var onDeleteItemClickListener: ((idEmployee: String) -> Unit)? = null
    fun setOnDeleteItemClickListener(onItemClickListener: (idEmployee: String) -> Unit) {
        this.onDeleteItemClickListener = onItemClickListener
    }

    companion object {
        const val ARG_ITEM = "arg_item"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDialogActionEmployeeBinding.inflate(inflater, container, false)
        employees = arguments?.serializable(ARG_ITEM)
        val bottomSheetBehavior = (dialog as BottomSheetDialog).behavior
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        with(binding){
            employees.let {em ->
                setUpConditionUi()
                setUpOnClick()

                etNameEmployee.setText(em?.name.orEmpty())
                etSalaryEmployee.setText(em?.salary.orEmpty())
                etAgeEmployee.setText(em?.age.orEmpty())
            }
        }
        return binding.root
    }

    private fun setUpOnClick(){
        with(binding){
            employees.let { em ->
                imgDeleteEmployee.setOnClickListener {
                    onDeleteItemClickListener?.invoke(em?.id.orEmpty())
                    dismiss()
                }

                btnSimpanOrUpdate.setOnClickListener {
                    if (validation()){
                        val employee = EmployeesModel(
                            id = em?.id.orEmpty(),
                            name = etNameEmployee.text.toString(),
                            salary = etSalaryEmployee.text.toString(),
                            age = etAgeEmployee.text.toString()
                        )
                        onAddItemClickListener?.invoke(employee, employees == null)
                        dismiss()
                    }
                }
            }
        }
    }

    private fun setUpConditionUi(){
        with(binding){
            employees.let { em ->
                if (employees != null) {
                    val valueIdEmployee = "${getString(R.string.id_employee)} ${em?.id.orEmpty()}"
                    tvIdEmployee.text = valueIdEmployee
                    btnSimpanOrUpdate.text = getString(R.string.update)
                } else {
                    imgDeleteEmployee.isVisible = false
                    tvIdEmployee.text = getString(R.string.add_employee)
                    btnSimpanOrUpdate.text = getString(R.string.simpan)
                }
            }
        }
    }

    private fun validation(): Boolean{
        var condition = true
        with(binding){
            if (etNameEmployee.text.isNullOrBlank()){
                condition = false
                etNameEmployee.error = getString(R.string.tidak_boleh_kosong)
            }

            if (etSalaryEmployee.text.isNullOrBlank()){
                condition = false
                etSalaryEmployee.error = getString(R.string.tidak_boleh_kosong)
            }

            if (etAgeEmployee.text.isNullOrBlank()){
                condition = false
                etAgeEmployee.error = getString(R.string.tidak_boleh_kosong)
            }
        }
        return condition
    }
}