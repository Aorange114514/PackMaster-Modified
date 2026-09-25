package pmoverrides;

import com.megacrit.cardcrawl.core.Settings;

public class PackText {
    public static String silent() {
        return pick("包含静默猎手的弃牌与运转牌",
                "A collection of Silent cards built around Discard and card cycling.",
                "버리기와 카드 순환을 중심으로 한 Silent 카드들.");
    }

    public static String ironclad() {
        return pick("包含铁甲战士的烧牌",
                "A collection of Ironclad cards built around Exhaust.",
                "카드 소멸을 중심으로 한 아이언클래드 카드들.");
    }

    public static String defect() {
        return pick("包含故障机器人的能量和运转牌",
                "A collection of Defect cards built around Energy and card cycling.",
                "에너지와 카드 순환을 중심으로 한 Defect 카드들.");
    }

    public static String watcher(String description) {
        return description.replaceAll("\\s*[\\(（][^)）]*[\\)）]", "").replace("스탠스를 제외한 ", "");
    }

    private static String pick(String zhs, String eng, String kor) {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            return zhs;
        }
        if (Settings.language == Settings.GameLanguage.KOR) {
            return kor;
        }
        return eng;
    }
}
