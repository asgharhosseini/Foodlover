package ir.ah.app.foodlover.data.remot.repositoeies.restaurant

import ir.ah.app.foodlover.data.model.appetizer.Appetizer
import ir.ah.app.foodlover.data.model.beverages.Beverages
import ir.ah.app.foodlover.data.model.dessert.Dessert
import ir.ah.app.foodlover.data.model.maincourse.MainCourse
import ir.ah.app.foodlover.data.model.restaurant.Restaurant
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantAppetizerImage
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantAppetizerName
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantAppetizerPrice
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantBeveragesImage
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantBeveragesName
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantBeveragesPrice
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantCategoryName
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantDessertImage
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantDessertName
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantDessertPrice
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantImageName
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantMainCourseImage
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantMainCourseName
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantMainCoursePrice
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.restaurantName
import ir.ah.app.foodlover.data.remot.repositoeies.RestaurantRepository
import java.util.*
import kotlin.random.Random

class RestaurantRepositoryFake : RestaurantRepository {

    override suspend fun getRestaurant(): Restaurant {
        val restaurantAppetizerList: ArrayList<Appetizer> = arrayListOf()
        val restaurantMainCourseList: ArrayList<MainCourse> = arrayListOf()
        val restaurantDessertList: ArrayList<Dessert> = arrayListOf()
        val restaurantBeveragesList: ArrayList<Beverages> = arrayListOf()
        val category = StringBuilder()
        for (j in 1..3) {
            if (j < 3) {
                category.append(restaurantCategoryName.get(Random.nextInt(0, 9)))
                category.append("،")
            } else {
                category.append(restaurantCategoryName.get(Random.nextInt(0, 9)))
            }
        }
        for (j in 1..restaurantAppetizerName.size - 1) {
            val appetizer = Appetizer(
                    j,
                    restaurantAppetizerName.get(j),
                    restaurantAppetizerImage.get(j),
                    restaurantAppetizerPrice.get(j)
            )
            restaurantAppetizerList.add(appetizer)
            val mainCourse = MainCourse(
                    j,
                    restaurantMainCourseName.get(j),
                    restaurantMainCourseImage.get(j),
                    restaurantMainCoursePrice.get(j)
            )
            restaurantMainCourseList.add(mainCourse)
            val dessert = Dessert(
                    j,
                    restaurantDessertName.get(j),
                    restaurantDessertImage.get(j),
                    restaurantDessertPrice.get(j)
            )
            restaurantDessertList.add(dessert)
            val beverages = Beverages(
                    j,
                    restaurantBeveragesName.get(j),
                    restaurantBeveragesImage.get(j),
                    restaurantBeveragesPrice.get(j)
            )
            restaurantBeveragesList.add(beverages)

        }
        val restaurant = Restaurant(
                1,
                restaurantName.get(Random.nextInt(0, restaurantName.size - 1)),
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

        return restaurant

    }
}