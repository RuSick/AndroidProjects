package com.example.simplecalc
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.simplecalc.R
import com.example.simplecalc.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializationComponentsView()
        setListeners()
    }

    private fun initializationComponentsView() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun setListeners() {
        mainBinding.buttonClear.setOnClickListener { performAcAction() }
        mainBinding.button0.setOnClickListener { mainBinding.input.append("0") }
        mainBinding.button1.setOnClickListener { mainBinding.input.append("1") }
        mainBinding.button2.setOnClickListener { mainBinding.input.append("2") }
        mainBinding.button3.setOnClickListener { mainBinding.input.append("3") }
        mainBinding.button4.setOnClickListener { mainBinding.input.append("4") }
        mainBinding.button5.setOnClickListener { mainBinding.input.append("5") }
        mainBinding.button6.setOnClickListener { mainBinding.input.append("6") }
        mainBinding.button7.setOnClickListener { mainBinding.input.append("7") }
        mainBinding.button8.setOnClickListener { mainBinding.input.append("8") }
        mainBinding.button9.setOnClickListener { mainBinding.input.append("9") }
        mainBinding.buttonDot.setOnClickListener { mainBinding.input.append(".") }
        mainBinding.buttonAddition.setOnClickListener { mainBinding.input.append(" + ") }
        mainBinding.buttonSubtraction.setOnClickListener { mainBinding.input.append(" - ") }
        mainBinding.buttonMultiply.setOnClickListener { mainBinding.input.append(" * ") }
        mainBinding.buttonDivision.setOnClickListener { mainBinding.input.append(" / ") }
        mainBinding.buttonBracketLeft.setOnClickListener { mainBinding.input.append(" ( ") }
        mainBinding.buttonBracketRight.setOnClickListener { mainBinding.input.append(" ) ") }
        mainBinding.buttonEquals.setOnClickListener { calculateAction() }
    }

    private fun performAcAction() {
        mainBinding.input.text = ""
        mainBinding.output.text = ""
    }

    private fun calculateAction() {
        val expression = ExpressionBuilder(mainBinding.input.text.toString()).build()
        val (result, longResult) = getCalculateResult(expression)

        when (result == longResult.toDouble()) {
            true -> mainBinding.output.text = longResult.toString()
            false -> mainBinding.output.text = result.toString()
        }

    }

    private fun getCalculateResult(expression: Expression): Pair<Double, Long> {
        return try {
            val result = expression.evaluate()
            val longResult = result.toLong()
            Pair(result, longResult)
        } catch (ex: Exception) {
            Pair(0.0, 0)
        }
    }


}