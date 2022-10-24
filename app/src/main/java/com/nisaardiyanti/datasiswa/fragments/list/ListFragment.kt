package com.nisaardiyanti.datasiswa.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import  com.nisaardiyanti.datasiswa.R
import  com.nisaardiyanti.datasiswa.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Mengembang tata letak untuk fragmen ini
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Tambah menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Hapus") { _, _ ->
            //Akan menampilkan buttom dengan tulisan hapus
            mUserViewModel.deleteAllUsers()
            Toast.makeText(
                requireContext(),
                "Data Berhasil Dihapus",
                //Akan menampilkan buttom dengan tulisan Data Berhasil Dihapus
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Batal") { _, _ -> }
        //Akan menampilkan buttom dengan tulisan Batal
        builder.setTitle("Data akan dihapus?")
        //Akan menampilkan buttom dengan tulisan Data akan dihapus
        builder.setMessage("Apakah anda akan menghapus data?")
        //Akan menampilkan buttom dengan tulisan Apakah anda akan menghapus data
        builder.create().show()
    }
}