package vcmsa.ci.budgettracker

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val incomeInput = findViewById<EditText>(R.id.incomeInput)
        val expense1Input = findViewById<EditText>(R.id.expense1Input)
        val expense2Input = findViewById<EditText>(R.id.expense2Input)
        val expense3Input = findViewById<EditText>(R.id.expense3Input)
        val expense4Input = findViewById<EditText>(R.id.expense4Input)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val balanceText = findViewById<TextView>(R.id.balanceText)
        val expenseFeedbackText = findViewById<TextView>(R.id.expenseFeedbackText)
        val individualFeedbackText = findViewById<TextView>(R.id.individualFeedbackText)

        calculateButton.setOnClickListener {
            // Get values
            val incomeStr = incomeInput.text.toString()
            val e1 = expense1Input.text.toString()
            val e2 = expense2Input.text.toString()
            val e3 = expense3Input.text.toString()
            val e4 = expense4Input.text.toString()

            // Simple empty check
            if (incomeStr.isEmpty() || e1.isEmpty() || e2.isEmpty() || e3.isEmpty() || e4.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Convert to numbers
            val income = incomeStr.toDouble()
            val ex1 = e1.toDouble()
            val ex2 = e2.toDouble()
            val ex3 = e3.toDouble()
            val ex4 = e4.toDouble()

            // Calculate total expenses and balance
            val totalExpenses = ex1 + ex2 + ex3 + ex4
            val balance = income - totalExpenses

            if (balance >= 0) {
                balanceText.text = "You are saving money!\nBalance: R$balance"
                balanceText.setTextColor(Color.GREEN)
            } else {
                balanceText.text = "You are overspending!\nBalance: R$balance"
                balanceText.setTextColor(Color.RED)
            }

            val percent = (totalExpenses / income) * 100
            if (percent > 30) {
                expenseFeedbackText.text = "Your total expenses are too high."
                expenseFeedbackText.setTextColor(Color.RED)
            } else if (percent < 5) {
                expenseFeedbackText.text = "Nice! Your expenses are very low."
                expenseFeedbackText.setTextColor(Color.GREEN)
            } else {
                expenseFeedbackText.text = "Your total expenses are okay."
                expenseFeedbackText.setTextColor(Color.BLACK)
            }

       }
    }
}
