package be.zvz.koyo.exception.typed.redeem

class RedemptionCodeAlreadyUsedException(data: Any) : CodeRedeemException("This Redemption Code is already in use", data)
