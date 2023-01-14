package icu.ofatal.yitai.data.model

import icu.ofatal.yitai.R

data class Course(
    val name: String,
    val author: Uploader,
    val totalCount: Int,
    val finishCount: Int,
    val cover: Int
)

data class Uploader(
    val avatar: Int,
    val name: String
)


fun MockCourse(): List<Course>{
    return listOf<Course>(
        Course(
            name = "轻微脊柱侧弯矫形1（15天）",
            cover = R.drawable.course_cover1,
            author = Uploader(
                name = "上传者 1",
                avatar = R.drawable.course_author1
            ),
            totalCount = 15,
            finishCount = 8
        ),
        Course(
            name = "轻微脊柱侧弯矫形2（15天）",
            cover = R.drawable.course_cover2,
            author = Uploader(
                name = "上传者 1",
                avatar =  R.drawable.course_author2
            ),
            totalCount = 15,
            finishCount = 0
        ),
        Course(
            name = "English",
            cover = R.drawable.course_cover3,
            author = Uploader(
                name = "上传者 1",
                avatar =  R.drawable.course_author3
            ),
            totalCount = 15,
            finishCount = 8
        )
    )
}
