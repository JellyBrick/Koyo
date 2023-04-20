package be.zvz.koyo.exception.typed.redeem

class AdventureRankTooLowException(data: Any) : CodeRedeemException("Cannot claim codes for account with adventure rank lower than 10.", data)
