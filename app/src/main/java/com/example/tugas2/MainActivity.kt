package com.example.tugas2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.colorResource

data class Artwork(
    val title: String,
    val imageRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceApp()

        }
    }
}

@Composable
fun ArtSpaceApp() {
    val artworks = listOf(
        Artwork("Pengajian memperingati isra' mi'raj", R.drawable.pengajian),
        Artwork("Merancang lego", R.drawable.lego),
        Artwork("Rihlah edukatif", R.drawable.rihlah),
        Artwork("makan siang", R.drawable.makan),
        Artwork("Berenang bersama", R.drawable.berenang),
    )

    var currentIndex by remember { mutableStateOf(0) }

    val swipeGesture = Modifier.pointerInput(Unit) {
        detectHorizontalDragGestures { change, dragAmount ->
            change.consume()
            if (dragAmount < -50 && currentIndex < artworks.size - 1) {
                currentIndex++
            } else if (dragAmount > 50 && currentIndex > 0) {
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().then(swipeGesture)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = artworks[currentIndex].imageRes),
                contentDescription = "Artworks",
                modifier = Modifier.fillMaxWidth().height(500.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(colorResource(id = R.color.lavender_blue)).padding(18.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = artworks[currentIndex].title,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { if (currentIndex > 0) currentIndex-- }) {
                Text("Previous")
            }
            Button(onClick = { if (currentIndex < artworks.size - 1) currentIndex++ }) {
                Text("Next")
            }
        }

    }

}



