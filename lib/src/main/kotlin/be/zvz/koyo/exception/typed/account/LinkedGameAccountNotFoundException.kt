package be.zvz.koyo.exception.typed.account

import be.zvz.koyo.exception.HoyoLabException

class LinkedGameAccountNotFoundException(data: Any) : HoyoLabException("Cookies are correct but do not have a hoyolab account bound to them.", data)
