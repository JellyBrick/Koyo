package be.zvz.koyo.exception.typed.reward

import be.zvz.koyo.exception.HoyoLabException

class AlreadyClaimedException(data: Any) : HoyoLabException("Already claimed daily reward today.", data)

