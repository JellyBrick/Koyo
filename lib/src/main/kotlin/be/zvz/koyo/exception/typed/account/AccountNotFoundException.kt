package be.zvz.koyo.exception.typed.account

import be.zvz.koyo.exception.HoyoLabException

class AccountNotFoundException(data: Any) : HoyoLabException("Could not find user; uid may not be valid.", data)
