package com.ebenezer.gana.mealzapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebenezer.model.MealsRepository
import com.ebenezer.model.response.MealsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(
    private val repository:
    MealsRepository = MealsRepository.getInstance()
) : ViewModel() {

    init {

        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    val mealsState: MutableState<List<MealsResponse>> =
        mutableStateOf(emptyList<MealsResponse>())

    private suspend fun getMeals(): List<MealsResponse> {
        return repository.getMeals().categories
    }


}