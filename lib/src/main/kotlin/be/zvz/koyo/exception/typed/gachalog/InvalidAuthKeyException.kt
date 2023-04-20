package be.zvz.koyo.exception.typed.gachalog

import be.zvz.koyo.exception.HoyoLabException

class InvalidAuthKeyException(data: Any) : HoyoLabException("Authkey is not valid.", data)
