package com.example.animeprac

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animeprac.ui.theme.AnimePracTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimePracTheme {
                AnimatableSample()
            }
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("RememberReturnType")
@Composable
private fun AnimatableSample(){
    var isAnimated by remember { mutableStateOf(false) }
    var isVisiable by remember { mutableStateOf(false) }

    val color = remember { Animatable(Color.DarkGray) }
    val transition = rememberInfiniteTransition(label = "Infinite Transition")
    val animateColor by transition.animateColor(
        initialValue = Color(0xFF60DDAD),
        targetValue = Color(0xFF4285F4),
        animationSpec = infiniteRepeatable(animation = tween(1000), repeatMode = RepeatMode.Reverse ),
        label = "Color Animation"
    )

//    LaunchedEffect(isAnimated) {
//        color.animateTo(if (isAnimated) Color.Green else Color.Red, animationSpec = tween(2000) )
//    }

//    Column (
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(bottom = 30.dp)
//        , horizontalAlignment = Alignment.CenterHorizontally
//            , verticalArrangement = Arrangement.Bottom
//    ){
//        Box (
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.9f)
//                .background(color.value)
//        )
//        Button(
//            onClick = { isVisiable = !isVisiable },
//            modifier = Modifier.padding(top = 20.dp))
//        {
//            Text(text = "Animate Color",
//                color = animateColor)
//        }
//    }
    AnimatedContent(
        targetState = isVisiable,
        modifier = Modifier.fillMaxWidth(),
        content = { isVisiable ->
            if (isVisiable){
                Box(modifier = Modifier.background(Color.Blue))
            } else {
                Box(modifier = Modifier.background(Color.Black))
            }
        },
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith fadeOut()
        }, label = "FadeInAndOut"
    )
//    Column (
//        modifier = Modifier
//            .fillMaxSize()
//            , horizontalAlignment = Alignment.CenterHorizontally
//        , verticalArrangement = Arrangement.Center
//    ){
//        Text(text = "animation text", fontSize = 50.sp, color = animateColor)
//    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp)
        , horizontalAlignment = Alignment.CenterHorizontally
            , verticalArrangement = Arrangement.Bottom
    ){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .background(color.value)
        )
        Button(
            onClick = { isVisiable = !isVisiable },
            modifier = Modifier.padding(top = 20.dp))
        {
            Text(text = "Animate Color",
                color = animateColor)
        }
    }
}

