package com.example.assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(
    private val expenseList: List<Expense>,
    private val onRemoveClick: (Int) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(view: View, private val onRemoveClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        val expenseName: TextView = view.findViewById(R.id.expense_name)
        val expenseCategory: TextView = view.findViewById(R.id.expense_category)
        val expenseAmount: TextView = view.findViewById(R.id.expense_amount)
        private val removeText: TextView = view.findViewById(R.id.text_remove)

        init {
            removeText.setOnClickListener {
                onRemoveClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(view, onRemoveClick)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenseList[position]
        holder.expenseName.text = expense.name
        holder.expenseCategory.text = expense.category
        holder.expenseAmount.text = "$%.2f".format(expense.amount)
    }

    override fun getItemCount() = expenseList.size
}
