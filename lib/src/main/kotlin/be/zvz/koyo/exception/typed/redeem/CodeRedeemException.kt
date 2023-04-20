package be.zvz.koyo.exception.typed.redeem

import be.zvz.koyo.exception.HoyoLabException

open class CodeRedeemException(message: String, data: Any) : HoyoLabException(message, data)
