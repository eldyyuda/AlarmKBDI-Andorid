package com.example.alarmtest.ui.filter

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.alarmtest.databinding.FilterSheetComponentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheet : BottomSheetDialogFragment() {

    private val filterViewModel: FilterViewModel by lazy {
        ViewModelProvider(requireParentFragment())[FilterViewModel::class.java]
    }

    private var _binding: FilterSheetComponentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilterSheetComponentBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onCancel(dialog: DialogInterface) {
        filterViewModel.closeBottomSheet()
        super.onCancel(dialog)
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}