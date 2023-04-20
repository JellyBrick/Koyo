package be.zvz.koyo.utils

import be.zvz.koyo.exception.HoyoLabException
import be.zvz.koyo.exception.typed.account.*
import be.zvz.koyo.exception.typed.common.InternalErrorException
import be.zvz.koyo.exception.typed.common.TooManyRequestsException
import be.zvz.koyo.exception.typed.gachalog.AuthKeyTimeoutException
import be.zvz.koyo.exception.typed.gachalog.InvalidAuthKeyException
import be.zvz.koyo.exception.typed.redeem.*
import be.zvz.koyo.exception.typed.reward.AlreadyCheckedException
import be.zvz.koyo.exception.typed.reward.AlreadyClaimedException

object ExceptionUtil {
    fun getException(code: Int, data: Any): HoyoLabException? = when (code) {
        10101 -> TooManyRequestsException(data)
        -100, 10001 -> InvalidCookieException(data)
        10102 -> DataNotPublicException(data)
        1009 -> AccountNotFoundException(data)
        -1 -> InternalErrorException(data)
        -10002 -> LinkedGameAccountNotFoundException(data)
        -108 -> LanguageNotValidException(data)
        10108 -> LinkedGameAccountNotFoundException(data)
        -2003 -> InvalidRedemptionCodeException(data)
        -2007 -> DuplicateKindOfRedemptionCodeException(data)
        -2017 -> DuplicateRedemptionCodeException(data)
        -2018 -> RedemptionCodeAlreadyUsedException(data)
        -2001 -> RedemptionCodeExpiredException(data)
        -2021 -> AdventureRankTooLowException(data)
        -1073 -> GameAccountNotLinkedException(data)
        -1071 -> AccountIdAndCookieTokenNotFoundException(data)
        -5003 -> AlreadyClaimedException(data)
        2001 -> AlreadyCheckedException(data)
        -100 -> InvalidAuthKeyException(data)
        -101 -> AuthKeyTimeoutException(data)
        0 -> null
        else -> HoyoLabException("Unknown error code: $code", data)
    }
}
