package be.zvz.koyo.exception.typed.reward

import be.zvz.koyo.exception.HoyoLabException

class AlreadyCheckedException(data: Any) : HoyoLabException("Already checked into HoyoLab today.", data)
