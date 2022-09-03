package br.com.paula.calculadora

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_config, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val percent = findViewById<View>(R.id.btnPercent)
        val raiz = findViewById<View>(R.id.btnRaiz)
        val exponencial = findViewById<View>(R.id.btnExponencial)

        return when (item.itemId) {
            R.id.action_config1 -> {
                percent.setVisibility(View.INVISIBLE);
                raiz.setVisibility(View.INVISIBLE);
                exponencial.setVisibility(View.INVISIBLE);
                true
            }
            R.id.action_config2 -> {
                percent.setVisibility(View.VISIBLE);
                raiz.setVisibility(View.VISIBLE);
                exponencial.setVisibility(View.VISIBLE);
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun getButtonValue(view: View) {
        val button = findViewById<View>(view.id) as Button
        val buttonValue = button.text.toString()
        val editTextVal1 = findViewById<View>(R.id.editTxtVal1) as EditText
        val editTextVal2 = findViewById<View>(R.id.editTxtVal2) as EditText
        if (editTextVal1.isFocused) {
            editTextVal1.setText(editTextVal1.text.toString() + buttonValue)
        } else if (editTextVal2.isFocused) {
            editTextVal2.setText(editTextVal2.text.toString() + buttonValue)
        } else {
            Toast.makeText(
                this,
                "Selecione um valor!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun calculateByOperationCode(view: View) {
        val editTextVal1 = findViewById<View>(R.id.editTxtVal1) as EditText
        val editTextVal2 = findViewById<View>(R.id.editTxtVal2) as EditText
        if (editTextVal1.text.toString().trim { it <= ' ' }
                .isEmpty() || editTextVal2.text.toString().trim { it <= ' ' }
                .isEmpty()) {
            Toast.makeText(
                this,
                "É necessário preencher campos de valor!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val button = findViewById<View>(view.id) as Button
            val textButton = button.text.toString()
            val editTextResultado = findViewById<View>(R.id.editTextResultado) as EditText
            val doubleVal1 = editTextVal1.text.toString().toDouble()
            val doubleVal2 = editTextVal2.text.toString().toDouble()
            when (textButton) {
                "+" -> editTextResultado.setText(calculate(1, doubleVal1, doubleVal2).toString())
                "-" -> editTextResultado.setText(calculate(2, doubleVal1, doubleVal2).toString())
                "×" -> editTextResultado.setText(calculate(3, doubleVal1, doubleVal2).toString())
                "÷" -> editTextResultado.setText(calculate(4, doubleVal1, doubleVal2).toString())
                "%" -> editTextResultado.setText(calculate(5, doubleVal1, doubleVal2).toString())
                "E²" -> editTextResultado.setText(calculate(6, doubleVal1, doubleVal2).toString())
                "R²" -> editTextResultado.setText(calculate(7, doubleVal1, doubleVal2).toString())
            }
        }
    }

    fun calculate(operationCode: Int, valor1: Double, valor2: Double): Double {
        var resultado = 0.00
        when (operationCode) {
            1 -> resultado = valor1 + valor2
            2 -> resultado = valor1 - valor2
            3 -> resultado = valor1 * valor2
            4 -> resultado = valor1 / valor2
            5 -> resultado = valor2 / valor1 * 100
            6 -> resultado = valor2 * (valor1 * valor1)
            7 -> resultado = valor1 * valor1
        }
        return resultado
    }

    fun cleanFields(view: View?) {
        val editTextVal1 = findViewById<View>(R.id.editTxtVal1) as EditText
        val editTextVal2 = findViewById<View>(R.id.editTxtVal2) as EditText
        val editTextResultado = findViewById<View>(R.id.editTextResultado) as EditText
        editTextVal1.setText("")
        editTextVal2.setText("")
        editTextResultado.setText("")
    }

}