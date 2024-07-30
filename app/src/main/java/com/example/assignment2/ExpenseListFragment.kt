package com.example.assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExpenseListFragment : Fragment() {
    private lateinit var expenseAdapter: ExpenseAdapter
    private val expenseList = mutableListOf<Expense>()
    private var listener: ExpenseChangeListener? = null

    interface ExpenseChangeListener {
        fun onTotalExpensesChanged(total: Double)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_expense_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        expenseAdapter = ExpenseAdapter(expenseList) { position -> removeExpense(position) }
        recyclerView.adapter = expenseAdapter
        return view
    }

    fun addExpense(expense: Expense) {
        expenseList.add(expense)
        expenseAdapter.notifyItemInserted(expenseList.size - 1)
        updateTotalExpenses()
    }

    private fun removeExpense(position: Int) {
        expenseList.removeAt(position)
        expenseAdapter.notifyItemRemoved(position)
        updateTotalExpenses()
    }

    private fun updateTotalExpenses() {
        val total = expenseList.sumOf { it.amount }
        listener?.onTotalExpensesChanged(total)
    }

    fun setExpenseChangeListener(listener: ExpenseChangeListener) {
        this.listener = listener
    }

    fun sortExpensesByName() {
        expenseList.sortBy { it.name }
        expenseAdapter.notifyDataSetChanged()
    }

    fun sortExpensesByAmount() {
        expenseList.sortBy { it.amount }
        expenseAdapter.notifyDataSetChanged()
    }

    fun sortExpensesByCategory() {
        expenseList.sortBy { it.category }
        expenseAdapter.notifyDataSetChanged()
    }
}
