package be.zvz.koyo.exception.typed.redeem

class GameAccountNotLinkedException(data: Any) : CodeRedeemException("Cannot claim code. Account has no game account bound to it.", data)
