package ir.ah.app.foodlover.data.remot.repositoeies.home

import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.model.banner.Banner
import ir.ah.app.foodlover.data.model.category.Category
import ir.ah.app.foodlover.data.model.restaurant.Restaurant
import ir.ah.app.foodlover.data.remot.repositoeies.BannerRepository
import ir.ah.app.foodlover.data.remot.repositoeies.CategoryRepository
import ir.ah.app.foodlover.data.remot.repositoeies.RestaurantRepository
import java.util.*
import kotlin.random.Random

class HomeRepositoryFake : RestaurantRepository, CategoryRepository, BannerRepository {


    private val restaurantName = arrayListOf<String>(
        "سرآشپز",
        "مهتاب",
        "یاس",
        "خورشید",
        "تخت جمشید",
        "سیب",
        "حسن رشتی",
        "اکبر جوجه ",
        "پدیده",
        "سراب"
    )
    private val restaurantCategoryName = arrayListOf<String>(
        "بین الملی",
        "کبابی",
        "جکرکی",
        "کره ای",
        "سنتی",
        "فست فود",
        "آشی",
        "ایتالیایی ",
        "کافه",
        "جوجه ای"
    )
    private val restaurantImageName = arrayListOf<String>(
        "https://blog.okala.com/wp-content/uploads/2019/07/%D9%82%D9%88%D8%B1%D9%85%D9%87-%D8%B3%D8%A8%D8%B2%DB%8C.jpg",
        "https://i.pinimg.com/originals/51/a8/b8/51a8b825c90acb6860a6e3056e6466f5.jpg",
        "https://i.pinimg.com/originals/e7/d7/38/e7d7383501f66883806e722ff4d3081f.jpg",
        "https://snapp.market/blog/wp-content/uploads/2020/04/kebab3.jpg",
        "https://hotelyar.com/mag/uploads/blog/GOHARSHAD-MOSQUE/shandiz/kandovan/Atigh-mosque/famouris%20house/chehel-sotoun/kish%20time%20travel/kish%20retaurant/zosk-village/coffee/NewFolder/kariz-e-kish/%D8%B3%D8%A7%D9%86%D8%A7/Bank-password/%D8%B1%D8%B2%D8%B1%D9%88%20%D9%87%D8%AA%D9%84/%D8%AE%D8%A7%D9%86%D9%87%20%D8%A8%D9%87%D9%86%D8%A7%D9%85/%D8%AE%D8%A7%D9%86%D9%87%20%D8%B3%DB%8C%D9%85%DB%8C%D9%86%20%D8%AF%D8%A7%D9%86%D8%B4%D9%88%D8%B1/%D8%B1%D8%B3%D8%AA%D9%88%D8%B1%D8%A7%D9%86%20%D9%87%D8%A7%DB%8C%20%D9%85%D8%B4%D9%87%D8%AF/%D9%85%D9%82%D8%A7%DB%8C%D8%B3%D9%87%20%D9%82%D8%B4%D9%85%20%D9%88%20%DA%A9%DB%8C%D8%B4/%D8%A8%D8%B3%D8%AA%D9%86%DB%8C/%D8%BA%D8%B0%D8%A7%DB%8C%20%D8%B3%D9%86%D8%AA%DB%8C%20%D9%85%D8%B4%D9%87%D8%AF/Mashahad-traditional-food-HOTELYAR-.jpg",
        " https://blog.okala.com/wp-content/uploads/2020/10/lunch.jpg",
        "https://img9.irna.ir/d/r2/2019/11/12/3/156756061.jpg",
        "https://damdary.com/assets/uploads/news/1FEC2624-0756-4A43-8FF0-C2BA4B92CD19--0.jpg ",
        "https://files.virgool.io/upload/users/16237/posts/jl5rz1pqxbqd/5sudpkj4wlha.jpeg",
        "https://jamejamonline.ir/files/fa/news/1399/9/15/181915_716.jpg"
    )


    private val categoryName = arrayListOf<String>(
        "غذا",
        "نوشیدنی",
        "گیاهی",
        "برگر",
        "کیک",
        "دسر",
        "پیتزا",
        "نودل",
        "شیک ",
        "مرغ",
        "مکزیکی",
        "قهوه"
    )
    private val categoryImageName = arrayListOf<Int>(
        R.drawable.food,
        R.drawable.drink,
        R.drawable.vege,
        R.drawable.burger,
        R.drawable.coke,
        R.drawable.dessert,
        R.drawable.pizza,
        R.drawable.noodles,
        R.drawable.milktea,
        R.drawable.chicken,
        R.drawable.mexicanfood,
        R.drawable.coffee
    )
    private val categoryColor = arrayListOf<String>(
        "#FB3061",
        "#5653D4",
        "#08A791",
        "#FAA33F",
        "#5653D4",
        "#FB3061",
        "#E5454C",
        "#08A791",
        "#5653D4",
        "#FAA33F",
        "#FB3061",
        "#B6644F"
    )


    private val bannerName = arrayListOf<String>(
        "سرآشپز",
        "مهتاب",
        "یاس",
        "خورشید",
        "تخت جمشید",
        "سیب",
        "حسن رشتی",
        "اکبر جوجه ",
        "پدیده",
        "سراب"
    )
    private val bannerSubtitle = arrayListOf<String>(
        "کرج",
        "تهران",
        "هشتگرد",
        "عظیمیه",
        "محمد شهر",
        "فردیس",
        "حصارک",
        "باغستان",
        "گوهردشت ",
        "جهانشهر",
        "طالقانی",
        "رجایی شهر"
    )
    private val bannerImageName = arrayListOf<String>(
        "https://cdn.takhfifan.com/images/1.0?id=/149457/6/_/6_357_16.jpg",
        "https://cdn.takhfifan.com/images/1.0?id=/149333/_/2/_2__500_7.jpg",
        "https://cdn.takhfifan.com/images/1.0?id=/200410/2/_/2_955_25.jpg",
        "https://cdn.takhfifan.com/images/1.0?id=/200345/2/_/2_956_33.jpg",
        "https://cdn.takhfifan.com/images/1.0?id=/200408/1/_/1_838_8_2_1_11_1_1.jpg",
        "https://cdn.takhfifan.com/images/1.0?id=/200177/i/m/image_413_1_34_1_69_1_9_1_34_1_29_1_16.jpg",
        "https://cdn.takhfifan.com/images/1.0?id=/199171/7/_/7_311_3.jpg",
        "https://cdn.takhfifan.com/images/1.0?id=/155471/_/1/_13__64_6.jpg",
        "https://cdn.takhfifan.com/images/1.0?id=/192338/p/9/p9_1_2.jpg",
        "https://cdn.takhfifan.com/images/1.0?id=/172519/_/2/_2__485_32_1_2_1_1_1_1.jpg"
    )
    private val bannerColor = arrayListOf<String>(
        "#FB3061",
        "#5653D4",
        "#08A791",
        "#FAA33F",
        "#5653D4",
        "#FB3061",
        "#E5454C",
        "#08A791",
        "#5653D4",
        "#FAA33F",
        "#FB3061",
        "#B6644F"
    )
    private val bannerCode = arrayListOf<String>(
        "FB3061",
        "5653D4",
        "08A791",
        "FAA33F",
        "5653D4",
        "FB3061",
        "E5454C",
        "08A791",
        "5653D4",
        "FAA33F",
        "FB3061",
        "B6644F"
    )


    override suspend fun getAllRestaurant(): List<Restaurant> {
        val restaurantList: ArrayList<Restaurant> = arrayListOf()
        for (i in 0..9) {
            val category = StringBuilder()
            for (j in 1..3) {
                category.append(restaurantCategoryName.get(Random.nextInt(0, 9)))
                category.append("،")
            }
            val restaurant = Restaurant(
                i,
                restaurantName.get(i),
                category.toString(),
                Random.nextInt(10, 100),
                Random.nextInt(10, 30).toString() + "'",
                Random.nextInt(10, 30).toString() + "m",
                restaurantImageName.get(Random.nextInt(0, 9))

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