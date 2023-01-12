package icu.ofatal.yitai.data.model

import icu.ofatal.yitai.R

data class Production(
    val image: Int,
    val name: String,
    val price: Float,
    val intro: String
)


fun MockProcutions(): List<Production> {
    return listOf<Production>(
        Production(
            image = R.drawable.production_chair,
            name = "少儿轮椅",
            intro = "这是一个少儿轮椅",
            price = 165.0f
        ),
        Production(
            image = R.drawable.production_medicine,
            name = "预防骨质疏松",
            intro = "这是保健胶囊",
            price = 275.0f
        ),
        Production(
            image = R.drawable.production_shirt,
            name = "Curved Hem Shirts",
            intro = "这是一件T恤",
            price = 99.0f
        )
    )
}