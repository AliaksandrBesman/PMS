package by.bstu.besman.lw12.Model

data class Coin(
    var id: String? = null,
    var rank: String? = null,
    var symbol: String? = null,
    var name: String? = null,
    var supply: String? = null,
    var maxSupply: Any? = null,
    var marketCapUsd: String? = null,
    var volumeUsd24Hr: String? = null,
    var priceUsd: String? = null,
    var changePercent24Hr: String? = null,
    var vwap24Hr: String? = null
)