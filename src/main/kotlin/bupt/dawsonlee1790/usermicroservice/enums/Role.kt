package bupt.dawsonlee1790.usermicroservice.enums

enum class Role(val value: String, val description: String) {
    Planner("计划人", "制定生产批次计划"),
    Researcher("研究员", "制定SOP流程"),
    Forklift("叉车工", "执行生产指令"),
    WorkshopManager("车间管理员", "执行生产指令"),
    ProductionLeader("生产负责人", "对一个批次的生产负责")
}