package be.zvz.koyo.exception.typed.redeem

class DuplicateRedemptionCodeException(data: Any) : CodeRedeemException("Redemption code has been claimed already.", data)
