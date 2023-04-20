package be.zvz.koyo.exception.typed.redeem

class DuplicateKindOfRedemptionCodeException(data: Any) : CodeRedeemException("You have already used a redemption code of the same kind.", data)
