package com.example.carloancal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormatSymbols
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //buttonCalculate.setOnClickListener {
        //    calculateLoan(it) //if (view: View)
                //val price = findViewById<EditText>(R.id.editTextCarPrice)
        //}

        findViewById<Button>(R.id.buttonCalculate).setOnClickListener{
            calculateLoan()
        }
    }

    private fun calculateLoan(){

        //Validation
        if(editTextCarPrice.text.isEmpty()){
            editTextCarPrice.setError(getString(R.string.error_input))
            return  //means stop execute
        }

        if(editTextDownPayment.text.isEmpty()){
            editTextDownPayment.setError(getString(R.string.error_input))
            return
        }

        if(editTextLoanPeriod.text.isEmpty()){
            editTextLoanPeriod.setError(getString(R.string.error_input))
            return
        }

        if(editTextInterestRate.text.isEmpty()){
            editTextInterestRate.setError(getString(R.string.error_input))
            return
        }

        val carPrice: Int = editTextCarPrice.text.toString().toInt()
        val downPayment: Int = editTextDownPayment.text.toString().toInt()
        val loan: Int = carPrice - downPayment
        val interest:Float = loan * editTextInterestRate.text.toString().toFloat() * editTextLoanPeriod.text.toString().toFloat()
        val monthRepay:Float = (loan + interest) / editTextLoanPeriod.text.toString().toFloat() / 12

        val locale = Locale.getDefault()
        val currency = Currency.getInstance(locale)
        val symbol = currency.getSymbol()


        textViewLoan.setText(getString(R.string.loan) + " " + symbol + " ${loan}") // or ${carPrice - downPayment} --- can do calculation using dollar sign
        textViewInterest.setText(getString(R.string.interest) + " " + symbol + " ${interest}")
        textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment) + " " + symbol + " ${monthRepay}")

        Toast.makeText(this, "Calculate Loan", Toast.LENGTH_SHORT).show()


    }

    fun reset(view: View){
        editTextCarPrice.setText(null)
        editTextDownPayment.setText(null)
        editTextLoanPeriod.setText(null)
        editTextInterestRate.setText(null)

        Toast.makeText(this,"Reset", Toast.LENGTH_SHORT).show()
    }



}
