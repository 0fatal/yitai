package icu.ofatal.yitai.data.model

import icu.ofatal.yitai.R

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
        Post(
            author = PostAuthor(
                name = "内涵青年",
                avatar = R.drawable.community_avatar,
            ),
            time = "3小时前",
            commentCount = 12,
            likeCount = 26,
            content = "大家觉得这个设备怎么样呀"
        ),
        Post(
            author = PostAuthor(
                name = "巧克力中的“爱马仕”",
                avatar = R.drawable.community_avatar2,
            ),
            time = "3小时前",
            commentCount = 69,
            likeCount = 117,
            content = "用了近一个月，对我帮助挺大的，脊柱比以前更挺直了，推荐大家尝试！！"
        ),
        Post(
            author = PostAuthor(
                name = "火山泥里的夏威夷果",
                avatar = R.drawable.community_avatar2,
            ),
            time = "昨天",
            commentCount = 92,
            likeCount = 346,
            content = "脊柱一直是我的心头病，终于有一款产品能够缓解我的焦虑。另外还有专业的医师一对一提供治疗纠正意见，太棒了！！"
        ),
        Post(
            author = PostAuthor(
                name = "火山泥里的夏威夷果",
                avatar = R.drawable.community_avatar2,
            ),
            time = "昨天",
            commentCount = 92,
            likeCount = 346,
            content = "脊柱一直是我的心头病，终于有一款产品能够缓解我的焦虑。另外还有专业的医师一对一提供治疗纠正意见，太棒了！！"
        ),
    )
}