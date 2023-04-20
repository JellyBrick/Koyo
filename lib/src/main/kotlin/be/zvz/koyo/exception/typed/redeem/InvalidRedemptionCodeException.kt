package be.zvz.koyo.exception.typed.redeem

class InvalidRedemptionCodeException(data: Any) : CodeRedeemException("Redemption code is invalid", data)
