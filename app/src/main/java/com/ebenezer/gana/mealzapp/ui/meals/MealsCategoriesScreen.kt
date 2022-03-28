package com.ebenezer.gana.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ebenezer.gana.mealzapp.ui.theme.MealzAppTheme
import com.ebenezer.model.response.MealsResponse


@Composable
fun MealsCategoriesScreen(navigationCallback: (String) -> Unit) {

    val viewModel: MealsCategoriesViewModel = viewModel()

    val meals = viewModel.mealsState.value

    Scaffold(topBar = {
        AppBar(title = "Food Items", icon = Icons.Default.ShoppingCart) {}
    }) {

        LazyColumn(contentPadding = PaddingValues(16.dp)) {

            items(meals) { meal ->
                MealCategory(meal, navigationCallback)
            }
        }

    }




}

@Composable
fun AppBar(title: String, icon: ImageVector, onClick: () -> Unit) {
    TopAppBar(navigationIcon = {
        Icon(
            icon,
            contentDescription = "Home",
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .size(30.dp)
                .clickable { onClick.invoke() }

        )
    },
        title = { Text(text = title) })
}


@Composable
fun MealCategory(meal: MealsResponse, navigationCallback: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable { navigationCallback(meal.id) }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            //Image
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Profile picture description",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically),
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
            ) {

                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.h6
                )

                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = meal.description,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.subtitle2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpanded) 10 else 4


                    )

                }

            }

            Icon(
                imageVector = if (isExpanded)
                    Icons.Filled.KeyboardArrowUp else
                    Icons.Filled.KeyboardArrowDown, contentDescription = "Expand row icon",
                modifier = Modifier
                    .padding(16.dp)
                    .align(
                        if (isExpanded)
                            Alignment.Bottom
                        else
                            Alignment.CenterVertically
                    )
                    .clickable { isExpanded = !isExpanded }
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        MealsCategoriesScreen({})
    }
}