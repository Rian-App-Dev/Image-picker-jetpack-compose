package com.example.intentapp

import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter


@Preview(showSystemUi = true)
@Composable
fun IntentDetail(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var imageUri by remember {
        mutableStateOf<Uri?>(Uri.parse("android.resource://${context.packageName}/${R.drawable.img}"))
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            imageUri = it?: imageUri
        }
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUri),
            contentDescription = null,
            modifier = Modifier
                .border(2.dp, Color.White, RoundedCornerShape(20.dp))
                .size(200.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    launcher.launch("image/*")
                },
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Syed Reazul Ferdous",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "Android Developer",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "I'm Syed Reazul Ferdous. a first-year CSE student at the University of BUBT.Passionte about coding and Android app development.",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Get connected with me",
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ) {
            IconButton(
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/syed-riazul-ferdous-63801326a/")
                    )
                    context.startActivity(intent)
                },
                modifier = Modifier.size(50.dp),
            ) {

                Image(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.linkedin),
                    contentDescription = "linkedin link"
                )

            }
            IconButton(
                onClick = {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Rian-App-Dev"))
                    context.startActivity(intent)
                },
                modifier = Modifier.size(50.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.github),
                    contentDescription = "GitHub link",
                    modifier = Modifier.size(50.dp)
                )
            }
            IconButton(
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/rf.rianrian")
                    )
                    context.startActivity(intent)
                },
                modifier = Modifier.size(50.dp),
            ) {
                Image(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "facebook link"
                )
            }


        }
    }






























    @Composable
    fun ImagePicker() {
        var imageUri by remember { mutableStateOf<Uri?>(null) }
        val context = LocalContext.current

        // Launcher to pick an image
        val imagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri: Uri? ->
                imageUri = uri
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { imagePickerLauncher.launch("image/*") }) {
                Text(text = "Pick Image")
            }
        }
    }

    @Composable
    fun MultiImagePicker() {
        var imageUris by rememberSaveable { mutableStateOf<List<Uri>>(emptyList()) }
        val context = LocalContext.current

        val imagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetMultipleContents()
        ) { uris: List<Uri> ->
            imageUris = imageUris + uris // Append selected images
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.height(300.dp)
            ) {
                items(imageUris) { uri ->
                    Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(2.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { imagePickerLauncher.launch("image/*") }) {
                Text(text = "Pick Images")
            }
        }
    }
}
