package com.example.jedzonko.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.jedzonko.R

class CalculatorFragment : Fragment(R.layout.calculator_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonSummary).setOnClickListener {
            view.findNavController().navigate(R.id.action_calculatorFragment_to_summaryFragment)
        }

        view.findViewById<Button>(R.id.buttonAddProduct).setOnClickListener {
            view.findNavController().navigate(R.id.action_calculatorFragment_to_scanFragment)
        }
    }
}