package be.zvz.koyo.exception.typed.account

import be.zvz.koyo.exception.HoyoLabException

class InvalidCookieException(data: Any) : HoyoLabException("Login cookies have not been provided or are incorrect.", data)
