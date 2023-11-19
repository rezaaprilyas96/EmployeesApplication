package com.rezaaprilyas.devtest.presentation.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rezaaprilyas.devtest.R
import com.rezaaprilyas.devtest.base.BindingActivity
import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesInsertRequest
import com.rezaaprilyas.devtest.data.employees.dataseource.request.EmployeesUpdateRequest
import com.rezaaprilyas.devtest.databinding.ActivityMainBinding
import com.rezaaprilyas.devtest.domain.model.EmployeesDeleteModel
import com.rezaaprilyas.devtest.domain.model.EmployeesInsertModel
import com.rezaaprilyas.devtest.domain.model.EmployeesModel
import com.rezaaprilyas.devtest.domain.model.EmployeesUpdateModel
import com.rezaaprilyas.devtest.presentation.adapter.ActionEmployeeDialogFragment
import com.rezaaprilyas.devtest.presentation.adapter.EmployeesAdapter
import com.rezaaprilyas.devtest.utils.helpers.ColorPalette
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>() {
    private val employeesAdapter by lazy { EmployeesAdapter() }
    private val viewModel: MainViewModel by viewModels()

    override fun initViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun renderView() {
        setupActionBar()
        setUpOnBackpress()
        setupSwipeRefresh()
        setupEmployeesList()
        setUpAddScrollChengeListener()
        setUpOnClick()
        setUpObserve()
        requestGetEmployees()
        setUpDoAfterChange()
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
            setHomeAsUpIndicator(R.drawable.arrow_back_svg)
        }
    }

    private fun setupSwipeRefresh() {
        with(binding) {
            srMain.setColorSchemeColors(
                ColorPalette.YOUNG_YELLOW,
                ColorPalette.DARK_BLUE,
                ColorPalette.BLUE_WEDGE_WOOD
            )
            srMain.setOnRefreshListener {
                requestGetEmployees()
            }
        }
    }

    private fun setUpAddScrollChengeListener(){
        with(binding){
            rvMain.viewTreeObserver.addOnScrollChangedListener {
                if (!rvMain.canScrollVertically(1)) {
                    fabEmployee.animate().translationY(0f).duration = 200
                }
            }
        }
    }

    private fun setUpDoAfterChange() {
        binding.etMainSearchData.doAfterTextChanged {
            search(it.toString())
        }
    }

    private fun setUpOnClick() {
        with(binding) {
            fabAddEmloyee.setOnClickListener {
                viewModel.employeesObject = null
                showBottomSheet()
                fabEmployee.collapse()
            }
        }
    }

    private fun setupEmployeesList() {
        with(binding) {
            employeesAdapter.setOnItemClickListener { em, position ->
                viewModel.employeesObject = em
                viewModel.positionItem = position
                showBottomSheet()
            }

            rvMain.apply {
                setHasFixedSize(true)
                setItemViewCacheSize(20)
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                adapter = employeesAdapter
                isNestedScrollingEnabled = true
            }
        }
    }

    private fun requestGetEmployees() {
        viewModel.getEmployees()
    }

    private fun requetDeleteEmployee() {
        viewModel.deleteEmployees()
    }

    private fun requestInsertEmployees() {
        viewModel.insertEmployees()
    }

    private fun requestUpdateEmployees() {
        viewModel.updateEmployees()
    }

    private fun setUpObserve() {
        viewModel.employees.observe(this) { employees ->
            renderEmployeesList(employees)
        }

        viewModel.deleteEmployees.observe(this) { deleteEmoloyees ->
            renderDeleteEmployees(deleteEmoloyees)
        }

        viewModel.insertEmployees.observe(this) { isertEmployees ->
            renderInsertEmployees(isertEmployees)
        }

        viewModel.updateEmployees.observe(this) { updateEmployees ->
            renderUpdateEmployees(updateEmployees)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.srMain.isRefreshing = isLoading
        }

        viewModel.error.observe(this) { error ->
            toast("Error $error")
        }
    }

    private fun renderEmployeesList(employeesModel: List<EmployeesModel>) {
        viewModel.listEmployees = employeesModel as ArrayList<EmployeesModel>
        employeesAdapter.setData(viewModel.listEmployees)
        binding.etMainSearchData.setText("")
    }

    private fun renderDeleteEmployees(employeesDeleteModel: EmployeesDeleteModel) {
        viewModel.listEmployees.removeAt(viewModel.positionItem)
        employeesAdapter.setData(viewModel.listEmployees)
        toast(employeesDeleteModel.message)
    }

    private fun renderInsertEmployees(employeesInsertModel: EmployeesInsertModel) {
        val employees = EmployeesModel(
            id = employeesInsertModel.id,
            name = employeesInsertModel.name,
            age = employeesInsertModel.age,
            salary = employeesInsertModel.salary
        )
        viewModel.listEmployees.add(employees)
        employeesAdapter.setData(viewModel.listEmployees)
        toast(employeesInsertModel.message)
    }

    private fun renderUpdateEmployees(employeesUpdateModel: EmployeesUpdateModel) {
        val employees = EmployeesModel(
            id = viewModel.idEmployee.toString(),
            name = employeesUpdateModel.name,
            age = employeesUpdateModel.age,
            salary = employeesUpdateModel.salary
        )
        viewModel.listEmployees[viewModel.positionItem] = employees
        employeesAdapter.setData(viewModel.listEmployees)
        toast(employeesUpdateModel.message)
    }

    private fun showBottomSheet() {
        val bottomSheet = ActionEmployeeDialogFragment()
        with(bottomSheet) {
            bottomSheet.setStyle(
                BottomSheetDialogFragment.STYLE_NORMAL,
                R.style.AppBottomSheetDialogTheme
            )
            val argInfo = Bundle()
            argInfo.putSerializable(
                ActionEmployeeDialogFragment.ARG_ITEM,
                viewModel.employeesObject
            )
            arguments = argInfo
            show(supportFragmentManager, tag)

            setOnAddItemClickListener { employees, statusAdd ->
                if (statusAdd){
                    viewModel.employeesInsertRequest = EmployeesInsertRequest(
                        name = employees.name,
                        age = employees.age,
                        salary = employees.salary
                    )

                    requestInsertEmployees()
                } else{
                    viewModel.idEmployee = if (employees.id.isBlank()){
                        viewModel.idEmployee
                    }else{
                        employees.id.toInt()
                    }
                    viewModel.employeesUpdateRequest = EmployeesUpdateRequest(
                        name = employees.name,
                        age = employees.age,
                        salary = employees.salary
                    )

                    requestUpdateEmployees()
                }
            }

            setOnDeleteItemClickListener { id ->
                viewModel.idEmployee = if (id.isBlank()){
                    viewModel.idEmployee
                }else{
                    id.toInt()
                }

                requetDeleteEmployee()
            }
        }
    }

    private fun search (search: String) {
        val searchnya = search.lowercase()
        val searchEmployees = ArrayList<EmployeesModel>()

        viewModel.listEmployees.filter {
            it.name.lowercase().contains(searchnya) ||
            it.id.lowercase().contains(searchnya)
        }.forEach {
            searchEmployees.add(it)
        }

        employeesAdapter.setData(searchEmployees)
    }

    private fun setUpOnBackpress() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.fabEmployee.isExpanded && !viewModel.exit) {
                    binding.fabEmployee.collapse()
                } else {
                    finish()
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                viewModel.exit = true
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}