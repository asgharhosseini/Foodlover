package ir.ah.app.foodlover.data.remot.repositoeies.home

import ir.ah.app.foodlover.data.model.appetizer.*
import ir.ah.app.foodlover.data.model.banner.*
import ir.ah.app.foodlover.data.model.beverages.*
import ir.ah.app.foodlover.data.model.category.*
import ir.ah.app.foodlover.data.model.dessert.*
import ir.ah.app.foodlover.data.model.maincourse.*
import ir.ah.app.foodlover.data.model.restaurant.*
import ir.ah.app.foodlover.data.remot.repositoeies.*
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.bannerCode
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.bannerColor
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.bannerImageName
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.bannerName
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.bannerSubtitle
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.categoryColor
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.categoryImageName
import ir.ah.app.foodlover.data.remot.repositoeies.FakeData.categoryName
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
import java.util.*
import kotlin.random.Random

class HomeRepositoryFake : RestaurantsRepository, CategoryRepository, BannerRepository {


    override suspend fun getAllRestaurant(): List<Restaurant> {
        val restaurantList: ArrayList<Restaurant> = arrayListOf()
        val restaurantAppetizerList: ArrayList<Appetizer> = arrayListOf()
        val restaurantMainCourseList: ArrayList<MainCourse> = arrayListOf()
        val restaurantDessertList: ArrayList<Dessert> = arrayListOf()
        val restaurantBeveragesList: ArrayList<Beverages> = arrayListOf()

        for (i in 0..restaurantName.size - 1) {
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
                        restaurantAppetizerName.get(Random.nextInt(0, restaurantAppetizerName.size - 1)),
                        restaurantAppetizerImage.get(Random.nextInt(0, restaurantAppetizerImage.size - 1)),
                        restaurantAppetizerPrice.get(Random.nextInt(0, restaurantAppetizerPrice.size - 1))
                )
                restaurantAppetizerList.add(appetizer)
                val mainCourse = MainCourse(
                        j,
                        restaurantMainCourseName.get(Random.nextInt(0, restaurantMainCourseName.size - 1)),
                        restaurantMainCourseImage.get(Random.nextInt(0, restaurantMainCourseImage.size - 1)),
                        restaurantMainCoursePrice.get(Random.nextInt(0, restaurantMainCoursePrice.size - 1))
                )
                restaurantMainCourseList.add(mainCourse)
                val dessert = Dessert(
                        j,
                        restaurantDessertName.get(Random.nextInt(0, restaurantDessertName.size - 1)),
                        restaurantDessertImage.get(Random.nextInt(0, restaurantDessertImage.size - 1)),
                        restaurantDessertPrice.get(Random.nextInt(0, restaurantDessertPrice.size - 1))
                )
                restaurantDessertList.add(dessert)
                val beverages = Beverages(
                        j,
                        restaurantBeveragesName.get(Random.nextInt(0, restaurantBeveragesName.size - 1)),
                        restaurantBeveragesImage.get(Random.nextInt(0, restaurantBeveragesImage.size - 1)),
                        restaurantBeveragesPrice.get(Random.nextInt(0, restaurantBeveragesPrice.size - 1))
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
                restaurantImageName.get(i),
                restaurantAppetizerList,
                restaurantMainCourseList,
                restaurantDessertList,
                restaurantBeveragesList
            )
            restaurantList.add(restaurant)

        }
        return restaurantList
    }

    override suspend fun getAllCategory(): List<Category> {
        val categoryList: ArrayList<Category> = arrayListOf()
        for (i in 0..11) {
            val category = Category(
                i,
                categoryName.get(i),
                Random.nextInt(10, 100),
                categoryImageName.get(i),
                categoryColor.get(i)

            )
            categoryList.add(category)

        }
        return categoryList
    }

    override suspend fun getAllBanner(): List<Banner> {
        val bannerList: ArrayList<Banner> = arrayListOf()
        for (i in 0..9) {
            val banner = Banner(
                i,
                bannerName.get(i),
                bannerSubtitle.get(i),
                bannerImageName.get(Random.nextInt(0, 9)),
                Random.nextInt(30, 70).toString(),
                bannerColor.get(i),
                bannerCode.get(i)
                )
            bannerList.add(banner)

        }
        return bannerList
    }
}