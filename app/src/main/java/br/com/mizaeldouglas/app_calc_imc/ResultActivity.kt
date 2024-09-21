package br.com.mizaeldouglas.app_calc_imc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    private lateinit var txtResult: TextView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtResult = findViewById(R.id.txt_result)
        btnBack = findViewById(R.id.btn_back)

        val bundle = intent.extras

        bundle?.let {
            val peso = it.getString("peso")!!.toDouble()
            val altura = it.getString("altura")!!.toDouble()
            val imc = peso / (altura * altura)


            val result = when {
                imc < 18.5 -> "Você está Abaixo do peso, Seu IMC é: %.2f".format(imc)
                imc in 18.5..24.9 -> "Seu peso está normal, Seu IMC é: %.2f".format(imc)
                imc in 25.0..29.9 -> "Você está com Sobrepeso, Seu IMC é: %.2f".format(imc)
                imc in 30.0..39.9 -> "Você está com Obesidade, Seu IMC é: %.2f".format(imc)
                else -> "Você está com Obesidade grave, Seu IMC é: %.2f".format(imc)
            }


            txtResult.text = result

            val color = when {
                imc < 18.5 -> R.color.abaixoDoPeso
                imc in 18.5..24.9 -> R.color.normal
                imc in 25.0..29.9 -> R.color.sobrepeso
                imc in 30.0..39.9 -> R.color.obsidade
                else -> R.color.obesidadeFatal
            }
            txtResult.setTextColor(ContextCompat.getColor(this, color))

        }

        btnBack.setOnClickListener {
            finish()
        }

    }
}