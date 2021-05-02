package ir.ah.app.foodlover.data.remot.repositoeies.search

import ir.ah.app.foodlover.data.model.appetizer.Appetizer
import ir.ah.app.foodlover.data.model.beverages.Beverages
import ir.ah.app.foodlover.data.model.dessert.Dessert
import ir.ah.app.foodlover.data.model.maincourse.MainCourse
import ir.ah.app.foodlover.data.model.restaurant.Restaurant
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantCategoryName
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantImageName
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantName
import ir.ah.app.foodlover.data.remot.repositoeies.SearchRepository
import java.util.*
import kotlin.random.Random

class SearchRepositoryFake : SearchRepository {

    override suspend fun getSearch(searchQuery: String): List<Restaurant> {
        val restaurantList: ArrayList<Restaurant> = arrayListOf()
        val restaurantAppetizerList: ArrayList<Appetizer> = arrayListOf()
        val restaurantMainCourseList: ArrayList<MainCourse> = arrayListOf()
        val restaurantDessertList: ArrayList<Dessert> = arrayListOf()
        val restaurantBeveragesList: ArrayList<Beverages> = arrayListOf()
        val searchList: ArrayList<Restaurant> = arrayListOf()
        for (i in 0..9) {
            val category = StringBuilder()
            for (j in 1..3) {
                if (j < 3) {
                    category.append(restaurantCategoryName.get(Random.nextInt(0, 9)))
                    category.append("،")
                } else {
                    category.append(restaurantCategoryName.get(Random.nextInt(0, 9)))
                }
            }
            for (j in 1..20) {
                val appetizer = Appetizer(
                        j,
                        FakeData.restaurantAppetizerName.get(Random.nextInt(0, FakeData.restaurantAppetizerName.size - 1)),
                        FakeData.restaurantAppetizerImage.get(Random.nextInt(0, FakeData.restaurantAppetizerImage.size - 1)),
                        FakeData.restaurantAppetizerPrice.get(Random.nextInt(0, FakeData.restaurantAppetizerPrice.size - 1))
                )
                restaurantAppetizerList.add(appetizer)
                val mainCourse = MainCourse(
                        j,
                        FakeData.restaurantMainCourseName.get(Random.nextInt(0, FakeData.restaurantMainCourseName.size - 1)),
                        FakeData.restaurantMainCourseImage.get(Random.nextInt(0, FakeData.restaurantMainCourseImage.size - 1)),
                        FakeData.restaurantMainCoursePrice.get(Random.nextInt(0, FakeData.restaurantMainCoursePrice.size - 1))
                )
                restaurantMainCourseList.add(mainCourse)
                val dessert = Dessert(
                        j,
                        FakeData.restaurantDessertName.get(Random.nextInt(0, FakeData.restaurantDessertName.size - 1)),
                        FakeData.restaurantDessertImage.get(Random.nextInt(0, FakeData.restaurantDessertImage.size - 1)),
                        FakeData.restaurantDessertPrice.get(Random.nextInt(0, FakeData.restaurantDessertPrice.size - 1))
                )
                restaurantDessertList.add(dessert)
                val beverages = Beverages(
                        j,
                        FakeData.restaurantBeveragesName.get(Random.nextInt(0, FakeData.restaurantBeveragesName.size - 1)),
                        FakeData.restaurantBeveragesImage.get(Random.nextInt(0, FakeData.restaurantBeveragesImage.size - 1)),
                        FakeData.restaurantBeveragesPrice.get(Random.nextInt(0, FakeData.restaurantBeveragesPrice.size - 1))
                )
                restaurantBeveragesList.add(beverages)

            }
            val restaurant = Restaurant(
                    i,
                    restaurantName.get(i),
                    category.toString(),
                    Random.nextInt(10, 100),
                    Random.nextInt(10, 30).toString() + "'",
                    Random.nextInt(10, 30).toString() + "دقیقه",
                    restaurantImageName.get(Random.nextInt(0, 9)),
                    restaurantAppetizerList,
                    restaurantMainCourseList,
                    restaurantDessertList,
                    restaurantBeveragesList
            )
            restaurantList.add(restaurant)

        }
        restaurantList.forEach { restaurant ->
            if (restaurant.title.equals(searchQuery) || restaurant.categories.equals(searchQuery)) {
                searchList.add(restaurant)
            } else {
                restaurant.appetizer.forEach {
                    if (it.title.equals(searchQuery)) {
                        searchList.add(restaurant)
                    }
                }
                restaurant.mainCourse.forEach {
                    if (it.title.equals(searchQuery)) {
                        searchList.add(restaurant)
                    }
                }
                restaurant.dessert.forEach {
                    if (it.title.equals(searchQuery)) {
                        searchList.add(restaurant)
                    }
                }
                restaurant.beverages.forEach {
                    if (it.title.equals(searchQuery)) {
                        searchList.add(restaurant)
                    }
                }
            }


        }

        return searchList
    }


}




