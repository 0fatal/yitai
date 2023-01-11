package icu.ofatal.yitai.data.model

data class Production(
    val image: Int,
    val name: String,
    val price: Float,
    val intro: String
)


fun MockProcutions(): List<Production> {
    return listOf<Production>(
        Production(
            image = 0,
            name = "少儿轮椅",
            intro = "这是一个少儿轮椅",
            price = 165.0f
        ),
        Production(
            image = 0,
            name = "预防骨质疏松",
            intro = "这是保健胶囊",
            price = 275.0f
        ),
        Production(
            image = 1,
            name = "Curved Hem Shirts",
            intro = "这是一件T恤",
            price = 99.0f
        )
    )
}