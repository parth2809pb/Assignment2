package com.example.assignment2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AddExpenseDialogFragment.AddExpenseListener, ExpenseListFragment.ExpenseChangeListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var expenseListFragment: ExpenseListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        expenseListFragment = ExpenseListFragment()
        expenseListFragment.setExpenseChangeListener(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, expenseListFragment)
            .commit()

        binding.buttonAddExpense.setOnClickListener {
            val dialog = AddExpenseDialogFragment(this)
            dialog.show(supportFragmentManager, "AddExpenseDialog")
        }
    }

    override fun onExpenseAdded(expense: Expense) {
        expenseListFragment.addExpense(expense)
    }

    override fun onTotalExpensesChanged(total: Double) {
        binding.textViewTotalExpenses.text = "Total Expenses: $%.2f".format(total)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sort_by_name -> {
                expenseListFragment.sortExpensesByName()
                true
            }
            R.id.menu_sort_by_amount -> {
                expenseListFragment.sortExpensesByAmount()
                true
            }
            R.id.menu_sort_by_category -> {
                expenseListFragment.sortExpensesByCategory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
