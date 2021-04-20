package com.example.fragmentactivity.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentactivity.auth.login.LoginFragmentDirections

import com.example.fragmentactivity.core.domain.User
import com.example.fragmentactivity.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = ArrayList<User>()
        val adapter = UserListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        val fireStore = FirebaseFirestore.getInstance()
        fireStore.collection("users").get().addOnSuccessListener {
            querySnapShot -> querySnapShot.forEach {
                document -> list.add(
            User(
                document.id,
                document.getString("name") ?: "",
                document.getString("city") ?: ""
            )
                )
             }
            adapter.submitList(list)
            }.addOnFailureListener {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }

        binding.imageButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            findNavController().navigate(action)
        }
        }
    }