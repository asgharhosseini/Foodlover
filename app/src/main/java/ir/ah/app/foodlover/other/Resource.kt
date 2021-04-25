package ir.ah.app.foodlover.other

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null, code: Int?) : Resource<T>(data, message, code)
    class Loading<T> : Resource<T>()
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}