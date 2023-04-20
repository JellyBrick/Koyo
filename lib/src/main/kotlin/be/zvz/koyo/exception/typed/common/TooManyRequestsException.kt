package be.zvz.koyo.exception.typed.common

import be.zvz.koyo.exception.HoyoLabException

class TooManyRequestsException(data: Any) : HoyoLabException("Can't get data for more than 30 accounts per cookie per day.", data)
