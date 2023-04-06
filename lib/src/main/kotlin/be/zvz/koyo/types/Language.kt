package be.zvz.koyo.types

enum class Language(val id: String) {
    SIMPLIFIED_CHINESE("zh-cn"),
    TRADITIONAL_CHINESE("zh-tw"),
    GERMAN("de-de"),
    ENGLISH("en-us"),
    SPANISH("es-es"),
    FRENCH("fr-fr"),
    IDONESIAN("id-id"),
    ITALIAN("it-it"),
    JAPANESE("ja-jp"),
    KOREAN("ko-kr"),
    PORTUGUESE("pt-pt"),
    RUSSIAN("ru-ru"),
    THAI("th-th"),
    TURKISH("tr-tr"),
    VIETNAMESE("vi-vn"),
    ;

    companion object {
        infix fun from(value: String): Language? = Language.values().firstOrNull { it.id == value }
    }
}
