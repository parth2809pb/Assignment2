package com.example.assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment

class AddExpenseDialogFragment(private val listener: AddExpenseListener) : DialogFragment() {

    interface AddExpenseListener {
        fun onExpenseAdded(expense: Expense)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_expense_dialog, container, false)

        val expenseNameEditText = view.findViewById<EditText>(R.id.editText_expense_name)
        val amountEditText = view.findViewById<EditText>(R.id.editText_amount)
        val categorySpinner = view.findViewById<Spinner>(R.id.spinner_category)
        val addButton = view.findViewById<Button>(R.id.button_add)

        addButton.setOnClickListener {
            val expenseName = expenseNameEditText.text.toString()
            val amount = amountEditText.text.toString().toDouble()
            val category = categorySpinner.selectedItem.toString()
            val expense = Expense(expenseName, amount, category)
            listener.onExpenseAdded(expense)
            dismiss()
        }

        return view
    }
}
