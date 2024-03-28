package com.singlechoicebottomsheet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.singlechoicebottomsheet.databinding.FragmentSingleChoiceBottomSheetBinding


public class SingleChoiceBottomSheetFragment<T>(private val dialogTitle : String = "",
                                         private val itemList: List<T>,
                                         private val displayFieldName : String,
                                         private val preSelectedItem : T? = null,
                                         private val onItemSelected : (T) -> Unit) : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var adapter: SingleChoiceBottomSheetAdapter<T>
    private lateinit var binding : FragmentSingleChoiceBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.setOnShowListener { dialog ->

            val bottomSheetNew = dialog as BottomSheetDialog

            val bottomSheet: FrameLayout =
                bottomSheetNew.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!

            val layoutParams = bottomSheet.layoutParams
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
            bottomSheet.layoutParams = layoutParams

            val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
            behavior.skipCollapsed = true
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
        binding = FragmentSingleChoiceBottomSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        adapter = SingleChoiceBottomSheetAdapter(itemList,displayFieldName,preSelectedItem){
            dismissAllowingStateLoss()
            onItemSelected.invoke(it)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                val filteredResults = if (s.toString().isNullOrEmpty()) {
                    itemList
                } else {
                    itemList.filter {
                        getDisplayTextForFilter(it).contains(s.toString(), true)
                    }
                }

                adapter.updateFilterList(filteredResults)

                if (filteredResults.isEmpty()) {
                    binding.tvNoData.visibility = View.VISIBLE
                } else {
                    binding.tvNoData.visibility = View.GONE
                }

                if (s.toString().isNotBlank()){
                    binding.btnClear.visibility = View.VISIBLE
                }else{
                    binding.btnClear.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnBack.setOnClickListener(this)
        binding.btnClear.setOnClickListener(this)

        if (dialogTitle.isNotBlank()){
            binding.tvTitle.text = dialogTitle
        }

    }

    private fun getDisplayTextForFilter(it: T): String {
        val obj = it!!
        return if (!displayFieldName.isNullOrBlank()) {
            val displayValue = obj::class.java.getDeclaredField(displayFieldName)
            displayValue.isAccessible = true

            displayValue.get(obj)?.toString() ?: ""
        }else{
            obj.toString()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnBack ->{
                dismissAllowingStateLoss()
            }
            R.id.btnClear ->{
                binding.etSearch.setText("")
            }
        }
    }
}
