package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import org.mariuszgromada.math.mxparser.Expression
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                CalculatorScreen()
            }
        }
    }
}

@Composable
fun CalculatorButton(modifier: Modifier = Modifier,
                     text: String="",
                     isFunction: Boolean = false,
                     onClick: (String) -> Unit) {
    Button(modifier = modifier
        .size(78.dp)
        .clip(CircleShape)
        .padding(4.dp),
        border = ButtonDefaults.outlinedButtonBorder,
        onClick = {onClick(text)},
        colors = ButtonDefaults.buttonColors(
            contentColor = if(isFunction && text == "=" || text == "AC"){
                MaterialTheme.colorScheme.secondary
            }else if(isFunction) {
                MaterialTheme.colorScheme.error
            } else {
                MaterialTheme.colorScheme.tertiary
            }
        )
    ) {
        Text(text = text, style = TextStyle(fontSize = 24.sp,
            color = if(isFunction && text != "=" && text!="AC") {
                Color(0xFFFF9800)
            } else {
                MaterialTheme.colorScheme.onPrimary
            }
        ))
    }
}

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
            .background(Color.Black).systemBarsPadding()
    ) {
        var expression by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }
        Column(
            modifier = modifier.weight(1f).padding(16.dp, top = 35.dp)
        ) {

            Box(
                modifier = modifier.fillMaxWidth(), // Fill the max width
                contentAlignment = Alignment.CenterEnd // Align content to the right
            ) {
                Text(
                    text = expression,
                    style = TextStyle(
                        fontSize = 60.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.End
                    ),
                    maxLines = 3
                )
            }

            Spacer(modifier = modifier.height(22.dp))

            Box(
                modifier = modifier.fillMaxWidth(), // Fill the max width
                contentAlignment = Alignment.CenterEnd // Align content to the right
            ) {
                Text(
                    text = result,
                    style = TextStyle(
                        fontSize = 72.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.End
                    )
                )
            }
        }

        val operators = listOf("+", "-", "x", "%")

        fun isLastCharOperator(expression: String): Boolean {
            return expression.isNotEmpty() && operators.contains(expression.last().toString())
        }

        Column() {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    isFunction = true, modifier = modifier.weight(2f), text = "AC",
                    onClick = {
                        expression = ""
                        result = ""
                    }
                )

                CalculatorButton(
                    isFunction = true, modifier = modifier.weight(1f), text = "⌫",
                    onClick = {
                        expression = expression.dropLast(1)
                    }
                )

                CalculatorButton(
                    isFunction = true, modifier = modifier.weight(1f), text = "%",
                    onClick = {
                        if (!isLastCharOperator(expression)) {
                            expression += "%"
                        }
                    }
                )

            }

            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    isFunction = false, modifier = modifier.weight(1f), text = "7",
                    onClick = {
                        expression += "7"
                    }
                )
                CalculatorButton(
                    isFunction = false, modifier = modifier.weight(1f), text = "8",
                    onClick = {
                        expression += "8"
                    }
                )
                CalculatorButton(
                    isFunction = false, modifier = modifier.weight(1f), text = "9",
                    onClick = {
                        expression += "9"
                    }
                )
                CalculatorButton(
                    isFunction = true, modifier = modifier.weight(1f), text = "x",
                    onClick = {
                        if (!isLastCharOperator(expression)) {
                            expression += "x"
                        }
                    }
                )
            }

            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    isFunction = false, modifier = modifier.weight(1f), text = "4",
                    onClick = {
                        expression += "4"
                    }
                )
                CalculatorButton(
                    isFunction = false, modifier = modifier.weight(1f), text = "5",
                    onClick = {
                        expression += "5"
                    }
                )
                CalculatorButton(
                    isFunction = false, modifier = modifier.weight(1f), text = "6",
                    onClick = {
                        expression += "6"
                    }
                )
                CalculatorButton(
                    isFunction = true, modifier = modifier.weight(1f), text = "-",
                    onClick = {
                        if (!isLastCharOperator(expression)) {
                            expression += "-"
                        }
                    }
                )
            }

            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    isFunction = false, modifier = modifier.weight(1f), text = "1",
                    onClick = {
                        expression += "1"
                    }
                )
                CalculatorButton(
                    isFunction = false, modifier = modifier.weight(1f), text = "2",
                    onClick = {
                        expression += "2"
                    }
                )
                CalculatorButton(
                    isFunction = false, modifier = modifier.weight(1f), text = "3",
                    onClick = {
                        expression += "3"
                    }
                )
                CalculatorButton(
                    isFunction = true, modifier = modifier.weight(1f), text = "+",
                    onClick = {
                        if (!isLastCharOperator(expression)) {
                            expression += "+"
                        }
                    }
                )
            }

            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    isFunction = false, modifier = modifier.weight(2f), text = "0",
                    onClick = {
                        expression += "0"
                    }
                )

                CalculatorButton(
                    isFunction = false, modifier = modifier.weight(1f), text = ".",
                    onClick = {
                        expression += "."
                    }
                )

                CalculatorButton(
                    isFunction = true, modifier = modifier.weight(1f), text = "=",
                    onClick = {
                        result = solveExpression(expression)
                    }
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorScreen()
}

@SuppressLint("DefaultLocale")
fun solveExpression(expression: String): String {
    var answer = ""
    try {
        val replacedExpression = expression.replace("%", "/").replace("x", "*")
        val cleanedExpression = replacedExpression.trimEnd { it in listOf('+', '-', '*', '/') }
        val result = Expression(cleanedExpression).calculate()

        // Check if the result is an integer
        answer = if (result % 1 == 0.0) {
            result.toInt().toString() // Convert to integer string if no decimal part
        } else {
            String.format("%.4f", result).trimEnd('0').trimEnd('.') // Format to 4 decimal places and remove trailing zeros
        }

        return answer
    } catch (E: Exception) {
        E.printStackTrace()
        return ""
    }
}


