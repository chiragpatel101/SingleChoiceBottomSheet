package com.singlechoicebottomsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.widget.Toast
import com.singlechoicebottomsheet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
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

        val bottomSheet = SingleChoiceBottomSheetFragment<UserEntity>("User List",list,"name",selectedUser){
            Toast.makeText(this@MainActivity,it.name,Toast.LENGTH_SHORT).show()
            selectedUser = it
        }

        bottomSheet.show(supportFragmentManager, bottomSheet.tag)

    }


}