package com.rezaaprilyas.devtest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<V : ViewBinding> : Fragment() {

    private var _binding: V? = null
    protected val binding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderView()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): V

    abstract fun renderView()

    protected fun toast(
        message: String,
        duration: Int = Toast.LENGTH_SHORT
    ) {
        if (context != null) {
            Toast.makeText(context, message, duration).show()
        }
    }
}