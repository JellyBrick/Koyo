package be.zvz.koyo.exception.typed.redeem

class RedemptionCodeExpiredException(data: Any): CodeRedeemException("Redemption code has expired.", data)
