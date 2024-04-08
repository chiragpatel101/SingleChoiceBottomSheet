package com.singlechoicebottomsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.singlechoicebottomsheet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedUser : UserEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenBottomSheet.setOnClickListener { view ->
            openBottomSheet()
        }
    }

    private fun openBottomSheet() {
        val list = arrayListOf<UserEntity>()
        for (i in 0..50) {
            val user = UserEntity((i+1),"Item ${i+1}","itemNo${i + 1}@gmail.com")
            list.add(user)
        }

        val bottomSheet = SingleChoiceBottomSheetFragment<UserEntity>(dialogTitle = "User List"
            , itemList = list,
            displayFieldName = "email",
            preSelectedItem = selectedUser,allowSearchIntoList = true,
            ResourcesCompat.getDrawable(resources,R.drawable.toolbar_background,null)!!
        ) { selectedItem ->
            Toast.makeText(this@MainActivity,selectedItem.name,Toast.LENGTH_SHORT).show()
            selectedUser = selectedItem
        }

        bottomSheet.show(supportFragmentManager, bottomSheet.tag)

    }


}