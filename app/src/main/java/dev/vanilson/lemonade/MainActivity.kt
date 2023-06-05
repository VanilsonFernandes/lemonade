package dev.vanilson.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vanilson.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                LemonLife()
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {

    var lemonPhase by remember {
        mutableStateOf(1)
    }

    var lemonSqueezing by remember {
        mutableStateOf(1)
    }

    var resMap = when (lemonPhase) {
        1 -> {
            mapOf(
                "res" to R.drawable.lemon_tree,
                "desc" to R.string.a,
                "txt" to R.string.a1
            )
        }

        2 -> {
            mapOf(
                "res" to R.drawable.lemon_squeeze,
                "desc" to R.string.b,
                "txt" to R.string.b1
            )
        }

        3 -> {
            mapOf(
                "res" to R.drawable.lemon_drink,
                "desc" to R.string.c,
                "txt" to R.string.c1
            )
        }

        else -> {
            mapOf(
                "res" to R.drawable.lemon_restart,
                "desc" to R.string.d,
                "txt" to R.string.d1
            )
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {

                if (lemonPhase == 2 && lemonSqueezing < 4) {
                    lemonSqueezing++
                    return@Button
                }

                if (lemonPhase > 3) {
                    lemonPhase = 1
                    lemonSqueezing = 0
                } else {
                    lemonPhase++
                }
            },
            shape = RoundedCornerShape(corner = CornerSize(30.dp)),
            contentPadding = PaddingValues(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC2EBD1)
            )
        ) {
            Image(
                painter = painterResource(
                    id = resMap.getValue("res")
                ),
                contentDescription = stringResource(
                    id = resMap.getValue("desc")
                )
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(
            text = stringResource(id = resMap.getValue("txt")),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonLife() {
    App(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}