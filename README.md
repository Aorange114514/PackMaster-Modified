# The Packmaster: Pack Overrides（`pmoverrides`）

Packmaster（`anniv5`，本体 2.5.1）的附属模组：**运行时不修改本体 jar**，只重写 6 个基础卡包的卡表与描述。

## 依赖

- ModTheSpire 3.30.2+、BaseMod、StSLib
- The Packmaster（`anniv5`，workshop 2920075378）

## 实现方式

`AbstractCardPack.initializePack()` 会调用各包的 `getCards()` 来建立 `pack.cards`、`cardParentMap`、`cardClassParentMap`。
本模组用 6 个 `@SpirePatch2(clz = XxxPack.class, method = "getCards")` 后置补丁，在返回值被使用前整体替换卡牌 ID 列表，
因此卡池、卡面卡包标签、图鉴、包预览会一次性保持一致，且不会残留旧卡的映射。

描述在同一个补丁里改 `__instance.description`（`initializePack()` 中 `makePreviewCard()` 在其之后执行，预览卡同步更新）：

- 静默猎手的收场 / 铁甲战士的信念 / 故障性梦境：按游戏语言替换为新描述
- 观者之愿景：去掉「(不包含姿态牌)」括号内容，并同步去掉韩文里的「스탠스를 제외한 」

## 新的卡表（每包 10 张）

| 卡包 | 卡牌 |
|---|---|
| 静默猎手的收场 `SilentPack` | 幽魂形态、华丽收场、计算下注、计划妥当、本能反应、战术大师、杂技、早有准备、内脏切除、隐秘打击 |
| 铁甲战士的信念 `IroncladPack` | 祭品、恶魔之焰、黑暗之拥、重振精神、战斗专注、放血、破灭、震荡波、剑柄打击、燃烧契约 |
| 观者之愿景 `WatcherPack` | 潦草急就、腾跃、化智为空、发泄、内心宁静、渐强、五体投地、不惧妖邪、义愤填膺、猛虎下山 |
| 故障性梦境 `DefectPack` | 冷静头脑、回响形态、超频、陨石打击、快速检索、回收、散热片、汇集、内核加速、全息影像 |
| 堡垒 `BulwarkPack` | 幽灵铠甲、火焰屏障、狂怒、壁垒、耸肩无视、后空翻、偏折、扫腿、余像、硬化机体 |
| 状态 `StatusPack` | 坚毅、恐怖、断魂斩、硬撑、进化、燔祭、火焰吐息、无谋冲锋、飞身踢、核心电涌 |

## 描述文本

| 语言 | 静默猎手 | 铁甲战士 | 故障 | 观者 |
|---|---|---|---|---|
| ZHS | 包含静默猎手的弃牌与运转牌 | 包含铁甲战士的烧牌 | 包含故障机器人的能量和运转牌 | 一套可靠的观者牌。 |
| ENG | A collection of Silent cards built around Discard and card cycling. | A collection of Ironclad cards built around Exhaust. | A collection of Defect cards built around Energy and card cycling. | A collection of reliable Watcher cards. |
| KOR | 버리기와 카드 순환을 중심으로 한 Silent 카드들. | 카드 소멸을 중심으로 한 아이언클래드 카드들. | 에너지와 카드 순환을 중심으로 한 Defect 카드들. | 신뢰할 수 있는 Watcher 카드들. |

堡垒 / 状态 保持原有描述；其它语言（Packmaster 未支持）回退英文。

## 冲突核查（本体 62 个包 + 官方扩展包 46 个包全量扫描）

本卡表**无冲突**：

- 每包 10 张、包内无重复、跨包无重复（原需求中「破灭」曾被「铁甲战士的信念」与「状态」同时引用，已改为「状态」使用「恐怖」）
- 一批「搬家型」卡片（重振精神→铁甲、超频/内核加速→故障、壁垒/耸肩无视/扫腿→堡垒）因为同时重写 6 个包而干净转移
- 与官方 Expansion Packs（1.12.0）的 46 个包零重叠（原「摆手」与 `IndomitablePack` 的冲突已随替换解除）

## 被换下的旧卡（改后不再属于任何卡包）

- 静默猎手：刀刃之舞、斗篷与匕首、扫腿、勒脖、灵动步法、子弹时间、钢铁风暴
- 铁甲战士：头槌、闪电霹雳、旋风斩、暴走、上勾拳、燃烧、双发、壁垒、耸肩无视
- 观者：时之沙、追击、点穴、粉碎关节、天眼、斋戒、当头棒喝、欺瞒现实、勤学精进
- 故障：球状闪电、碎片整理、扩容、递归、愁云惨淡、冰川、多重释放、偏差认知
- 堡垒：充电、蒸汽护壁、旋身、均衡、圣洁、重振精神、势不可当、精神护盾
- 状态：狂野打击、内核加速、超频、Darker Embrace

## 构建

```powershell
mvn clean package
```

`pom.xml` 里的 `${steam.windows}` 指向你的 Steam 安装目录；打包后 antrun 会自动把 jar 复制到 `SlayTheSpire/mods/`。
