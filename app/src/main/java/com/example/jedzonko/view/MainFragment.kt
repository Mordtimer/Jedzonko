package com.example.jedzonko.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.jedzonko.R
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment() : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonScan).setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_scanFragment)
        }
        //todo
        //view.findViewById<BottomNavigationView>(R.id.bottomNavigation).setOnNavigationItemSelectedListener {
        //when(it.itemId){
        //R.id.menuHome -> tutaj zrobić akcję przeniesienia do danego fragmentu
        //R.id.menuHome ->
        //R.id.menuHome ->
        //}
        //}
    }
}