package be.zvz.koyo.types.genshin

enum class GenshinRegion(val id: String) {
    OS_USA("os_usa"),
    OS_EURO("os_euro"),
    OS_ASIA("os_asia"),
    OS_CHT("os_cht"),
    ;

    companion object {
        infix fun from(value: String): GenshinRegion = GenshinRegion.values().first { it.id == value }
    }
}
