package ir.ah.app.foodlover.data.remot.repositoeies.restaurant

import ir.ah.app.foodlover.data.model.appetizer.*
import ir.ah.app.foodlover.data.model.beverages.*
import ir.ah.app.foodlover.data.model.dessert.*
import ir.ah.app.foodlover.data.model.maincourse.*
import ir.ah.app.foodlover.data.model.restaurant.*
import ir.ah.app.foodlover.data.remot.repositoeies.*
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
import ir.ah.app.foodlover.other.*
import java.util.*
import javax.inject.*
import kotlin.random.Random

class RestaurantRepositoryFake @Inject constructor(private val userInfoManager: UserInfoManager) :
    RestaurantRepository {


    override suspend fun getRestaurant(restaurantId: Int): Restaurant {
        val restaurantAppetizerList: ArrayList<Appetizer> = arrayListOf()
        val restaurantMainCourseList: ArrayList<MainCourse> = arrayListOf()
        val restaurantDessertList: ArrayList<Dessert> = arrayListOf()
        val restaurantBeveragesList: ArrayList<Beverages> = arrayListOf()
        var latitudeFake: Double = 0.0
        var longitudeFake: Double = 0.0
        if (userInfoManager.getLatitude() != null && userInfoManager.getLongitude() != null) {
            latitudeFake =
                userInfoManager.getLatitude()!!.toDouble() + Random.nextDouble(0.000000, 0.009999)
            longitudeFake =
                userInfoManager.getLongitude()!!.toDouble() + Random.nextDouble(0.000000, 0.009999)
        }

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
            restaurantId,
            restaurantName.get(restaurantId),
            category.toString(),
            Random.nextInt(10, 100),
            Random.nextInt(10, 30).toString() + "'",
            Random.nextInt(10, 30).toString() + "دقیقه",
            restaurantImageName.get(restaurantId),
            restaurantAppetizerList,
            restaurantMainCourseList,
            restaurantDessertList,
            restaurantBeveragesList,
            latitudeFake, longitudeFake
        )

        return restaurant

    }


}