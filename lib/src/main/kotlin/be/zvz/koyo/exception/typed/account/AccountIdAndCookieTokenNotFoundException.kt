package be.zvz.koyo.exception.typed.account

import be.zvz.koyo.exception.HoyoLabException

class AccountIdAndCookieTokenNotFoundException(data: Any) : HoyoLabException(
    "Login cookies from redeem_code() have not been provided or are incorrect. " +
    "Make sure you use account_id and cookie_token cookies.",
    data
)
