package com.ebenezer.model

import com.ebenezer.model.api.MealsApi
import com.ebenezer.model.response.MealsCategoriesResponse
import com.ebenezer.model.response.MealsResponse

class MealsRepository(private val mealsApi: MealsApi = MealsApi()) {

    private var cachedMeals = listOf<MealsResponse>()

    suspend fun getMeals():MealsCategoriesResponse {
        //return mealsApi.getMeals()
        val response = mealsApi.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMeal(id:String):MealsResponse? {
        return cachedMeals.firstOrNull(){
            it.id == id
        }
    }

    companion object{
        @Volatile
        private var instance:MealsRepository? = null

        fun getInstance() = instance?: synchronized(this){
            instance?: MealsRepository().also {
                instance = it
            }
        }
    }
}