package com.example.td

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.td.ui.theme.TDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDTheme {
                // A surface container with a beige background
                Surface(
                    modifier = Modifier.fillMaxSize().background(Color.LightGray),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Search Bar
                        SearchBar()

                        // Horizontal List of Images with Captions
                        ImageList()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var textState by remember { mutableStateOf(TextFieldValue()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textState,
            onValueChange = { newText ->
                textState = newText
            },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            placeholder = { Text("Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },

            )
    }
}

@Composable
fun ImageList() {
    val imageList = listOf(
        "ab1_inversions",
        "ab2_quick_yoga",
        "ab3_stretching",
        "ab4_tabata",
        "ab5_hiit",
        "ab6_pre_natal_yoga"
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(imageList) { imageCaption ->
            ImageItem(imageCaption = imageCaption)
        }
    }
}

@Composable
fun ImageItem(imageCaption: String) {
    val imageResId = getResourceIdByName(imageCaption, "drawable")

    if (imageResId != 0) {
        val painter = rememberImagePainter(imageResId)

        Column(
            modifier = Modifier
                .padding(8.dp)
                .width(120.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = null, // Content description for accessibility
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray, shape = MaterialTheme.shapes.medium)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = imageCaption,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

private fun getResourceIdByName(name: String, resourceType: String): Int {
    val res = Resources.getSystem()
    return res.getIdentifier(name, resourceType, "android")
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TDTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            // Search Bar
            SearchBar()

            // Horizontal List of Images with Captions
            ImageList()
        }
    }
}
