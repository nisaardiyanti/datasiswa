package com.nisaardiyanti.datasiswa.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nisaardiyanti.datasiswa.R
import com.nisaardiyanti.datasiswa.model.User
import  com.nisaardiyanti.datasiswa.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
//kelas Add Fragment
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Mengembang tata letak untuk fragmen ini
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName_et.text.toString()
        val lastName = addLastName_et.text.toString()
        val age = addAge_et.text

        if(inputCheck(firstName, lastName, age)){
            // Membuat Objek Pengguna
            val user = User(
                0,
                firstName,
                lastName,
                Integer.parseInt(age.toString())
            )
            // Tambahkan Data ke Basis Data
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Data Berhasil Ditambahkan!", Toast.LENGTH_LONG).show()
            //Akan menampilkan komentar data berhasil ditambahkan setelah menambahkan data
            // Navigasi kembali
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Masukkan data terlebih dahulu.", Toast.LENGTH_LONG).show()
            //Akan menampilkan komentar Masukkan data terlebih dahulu setelah menambahkan data
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}