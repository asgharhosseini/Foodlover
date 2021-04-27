package ir.ah.app.foodlover.other

object NumberHelper {
    fun EnglishToPersian(a: String): String {
        var a = a
        val pNum = arrayOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")
        a = a.replace("0", pNum[0])
        a = a.replace("1", pNum[1])
        a = a.replace("2", pNum[2])
        a = a.replace("3", pNum[3])
        a = a.replace("4", pNum[4])
        a = a.replace("5", pNum[5])
        a = a.replace("6", pNum[6])
        a = a.replace("7", pNum[7])
        a = a.replace("8", pNum[8])
        a = a.replace("9", pNum[9])
        return a
    }

    fun PersianToEnglish(a: String): String {
        var a = a
        val pNum = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        a = a.replace("۰", pNum[0])
        a = a.replace("۱", pNum[1])
        a = a.replace("۲", pNum[2])
        a = a.replace("۳", pNum[3])
        a = a.replace("۴", pNum[4])
        a = a.replace("۵", pNum[5])
        a = a.replace("۶", pNum[6])
        a = a.replace("۷", pNum[7])
        a = a.replace("۸", pNum[8])
        a = a.replace("۹", pNum[9])
        return a
    }
}
