package be.zvz.koyo.exception.typed.account

import be.zvz.koyo.exception.HoyoLabException

class LanguageNotValidException(data: Any) : HoyoLabException("Language is not valid", data)
