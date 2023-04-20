package be.zvz.koyo.exception.typed.gachalog

import be.zvz.koyo.exception.HoyoLabException

class AuthKeyTimeoutException(data: Any) : HoyoLabException("Authkey is valid but has timed out.", data)
