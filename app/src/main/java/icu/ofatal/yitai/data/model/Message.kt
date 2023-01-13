package icu.ofatal.yitai.data.model

import icu.ofatal.yitai.R

data class Message(
    val target: TargetUser,
    val time: String,
    val latestMsg: String
)

data class TargetUser(
    val name: String,
    val avatar: Int
)

fun MockMessageList(): List<Message> {
    return listOf<Message>(
        Message(
            target = TargetUser(
                name = "程医生",
                avatar = R.drawable.msg_list_people1
            ),
            latestMsg = "你的矫形支具需要调整，看到请回复",
            time = "刚刚"
        ),
        Message(
            target = TargetUser(
                name = "Marvin McKinney",
                avatar = R.drawable.msg_list_people2
            ),
            latestMsg = "这是一段示例文字这是一段示例文文字",
            time = "2 分钟前"
        ),
        Message(
            target = TargetUser(
                name = "易小态",
                avatar = R.drawable.msg_list_people3
            ),
            latestMsg = "新版本2.0功能更新日志请查收~",
            time = "3 分钟前"
        ),
        Message(
            target = TargetUser(
                name = "Theresa Webb",
                avatar = R.drawable.msg_list_people4
            ),
            latestMsg = "这是一段示例文字这是一段示例文文字",
            time = "4 分钟前"
        ),
        Message(
            target = TargetUser(
                name = "Wade Warren",
                avatar = R.drawable.msg_list_people5,
            ),
            latestMsg = "这是一段示例文字这是一段示例文文字",
            time = "1 小时前",
        ),

        Message(
            target = TargetUser(
                name = "Deri Rutabeth",
                avatar = R.drawable.msg_list_people1
            ),
            latestMsg = "这是一段示例文字这是一段示例文文字",
            time = "1 小时前"

        )
    )
}