package be.zvz.koyo.exception.typed.common

import be.zvz.koyo.exception.HoyoLabException

class InternalErrorException(data: Any): HoyoLabException("Internal database error, see original message", data)
