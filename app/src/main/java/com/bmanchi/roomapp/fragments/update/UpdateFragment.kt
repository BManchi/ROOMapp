package com.bmanchi.roomapp.fragments.update

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
import androidx.navigation.fragment.navArgs
import com.bmanchi.roomapp.R
import com.bmanchi.roomapp.model.User
import com.bmanchi.roomapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.updateTextTextPersonFirstName.setText(args.currentUser.firstName)
        view.updateTextTextPersonLastName.setText(args.currentUser.firstName)
        view.updateTextTextPersonAge.setText(args.currentUser.age.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }
        return view
    }

    private fun updateItem() {
        val firstName = updateTextTextPersonFirstName.text.toString()
        val lastName = updateTextTextPersonLastName.text.toString()
        val age = Integer.parseInt(updateTextTextPersonAge.text.toString())

        if (inputCheck(firstName, lastName, updateTextTextPersonAge.text)) {
            // Create User Object
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_LONG).show()
            // Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}