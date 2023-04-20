package be.zvz.koyo.exception

open class HoyoLabException : RuntimeException {
    var data: Any? = null
        private set
    constructor() : super()
    constructor(message: String, data: Any) : super(message) {
        this.data = data
    }
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
    constructor(
        message: String,
        cause: Throwable,
        enableSuppression: Boolean,
        writableStackTrace: Boolean,
    ) : super(message, cause, enableSuppression, writableStackTrace)
}
