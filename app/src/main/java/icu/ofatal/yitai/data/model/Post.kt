package icu.ofatal.yitai.data.model

data class Post(
    val author: PostAuthor,
    val time: String,
    val content: String,
    val commentCount: Int,
    val likeCount: Int
)

data class PostAuthor(
    val name: String,
    val avatar: Int,
)

fun MockPostList(): List<Post> {
    return listOf(
//        Post(
//            author = PostAuthor()
//        )
    )
}