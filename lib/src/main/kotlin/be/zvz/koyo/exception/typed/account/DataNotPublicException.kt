package be.zvz.koyo.exception.typed.account

import be.zvz.koyo.exception.HoyoLabException

class DataNotPublicException(data: Any) : HoyoLabException("User's data is not public", data)
