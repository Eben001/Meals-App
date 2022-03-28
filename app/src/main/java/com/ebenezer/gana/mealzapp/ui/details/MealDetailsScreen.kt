package com.ebenezer.gana.mealzapp.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ebenezer.gana.mealzapp.ui.meals.AppBar
import com.ebenezer.model.response.MealsResponse

@Composable
fun MealDetailsScreen(meal: MealsResponse?, navController: NavController?) {

    Scaffold(topBar = {
        AppBar(title = "Food Details", icon = Icons.Default.ArrowBack) {
            navController?.navigateUp()
        }
    }) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(meal?.imageUrl)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp),
                    )
                }

            }

            val scroll = rememberScrollState(0)
            Text(
                text = meal?.description ?: "default name",
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(scroll)
            )


        }

    }


}