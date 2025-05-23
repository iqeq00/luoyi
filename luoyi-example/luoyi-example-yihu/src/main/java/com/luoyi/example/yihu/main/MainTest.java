package com.luoyi.example.yihu.main;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.http.HtmlUtil;
import org.apache.commons.lang3.StringEscapeUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class MainTest {

    public static void main(String[] args) {
//        1742483940
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(1742483946), ZoneId.systemDefault());
//        System.out.println(LocalDateTimeUtil.format(localDateTime, "yyyy-MM-dd HH:mm:ss"));

        int compare = NumberUtil.compare(200000L, 200000L);
        System.out.println(compare);
        System.out.println(compare > 0);


        String escapedStr = "再加一个&amp;ldquo;&amp;mdash;&amp;mdash;&amp;rdquo;，";
        // 左双引号
//        String restored = HtmlUtil.unescape(escapedStr).replace("“", "&ldquo;").replace("”", "&rdquo;");
//        System.out.println(StringEscapeUtils.unescapeHtml4(escapedStr));
//
        String ss ="<p>　　易经的道理，看起来非常艰深，实际上十分简单，否则凭什么叫做&amp;ldquo;易&amp;rdquo;经呢？<br /><br />　　宇宙万象千变万化，可以用&amp;ldquo;错综复杂&amp;rdquo;来描述。在这错综复杂的现象背后，有一个简单明了的宇宙秩序，也就是变化的原则，称为&amp;ldquo;一阴一阳之谓道&amp;rdquo;。<br /><br />　　一阴一阳，指的是两个符号。&amp;ldquo;&amp;mdash;&amp;mdash;&amp;rdquo;代表&amp;ldquo;阴&amp;rdquo;，而&amp;ldquo;-&amp;rdquo;表示&amp;ldquo;阳&amp;rdquo;。说它们不一样，就真的不相同：阴是中断的，而阳则是没有中断的。说它们一样，也就真的相同：阳是一小段直线，阴不过是再加上一小段直线，有什么不一样？这就产生了&amp;ldquo;物极必反&amp;rdquo;的概念，一个&amp;ldquo;-&amp;rdquo;算&amp;ldquo;阳&amp;rdquo;，再加上一个&amp;ldquo;-&amp;rdquo;，变成&amp;ldquo;&amp;mdash;&amp;mdash;&amp;rdquo;，就是&amp;ldquo;阴&amp;rdquo;了。反过来一个&amp;ldquo;&amp;mdash;&amp;mdash;&amp;rdquo;是&amp;ldquo;阴&amp;rdquo;，再加一个&amp;ldquo;&amp;mdash;&amp;mdash;&amp;rdquo;，变成&amp;ldquo;&amp;mdash;&amp;mdash;&amp;mdash;&amp;mdash;&amp;rdquo;，太多也太密了，干脆连接在一起，不就成了&amp;ldquo;-&amp;rdquo;，便是&amp;ldquo;阳&amp;rdquo;了。于是又引申出&amp;ldquo;事不过三&amp;rdquo;的概念，四太多了，限制在三以内。伏羲氏只画三画卦，不画四画卦，影响到后代子孙，谨守&amp;ldquo;无三不成礼&amp;rdquo;的原则。<br /><br />　　用现代的话来说，用&amp;ldquo;0&amp;rdquo;(阴)和&amp;ldquo;1&amp;rdquo;(阳)两个数字，在电脑上玩排列组合的游戏，相当于伏羲氏当年，用&amp;ldquo;&amp;mdash;&amp;mdash;&amp;rdquo;(阴)、&amp;ldquo;-&amp;rdquo;(阳)两种符号，玩排列组合的游戏。每卦由三个符号组成，每一个符号都有&amp;ldquo;&amp;mdash;&amp;mdash;&amp;rdquo;和&amp;ldquo;-&amp;rdquo;两种可能，于是出现八种不同的组合，那就是■、■■、■、■、■、■、■、■■，即为&amp;ldquo;八卦&amp;rdquo;。一个不能多，也一个不能少。<br /><br />　　伏羲氏是一个人的名字，还是一群人的代表，我们不必管它，让考古学家去伤脑筋。甚至于根本没有这个人，或者那时候的人还没有取名字的习惯，也不干我们的事。<br /><br />　　我们只知道，伏羲氏画卦，目的为了造字。透过占卜的符号游戏，来推行识字教育，没有把&amp;ldquo;神&amp;rdquo;搬出来，说&amp;ldquo;神&amp;rdquo;是一切的主宰，使我们进入&amp;ldquo;人本位&amp;rdquo;的大门，却没有&amp;ldquo;神本位&amp;rdquo;的想法。中华民族有信仰而没有宗教，和易经有十分密切的关系。我们对于&amp;ldquo;神&amp;rdquo;的认识，相对也很单纯，觉得神奇、神妙、神灵、神明，并没有太大的威势，只要敬而远之，便可以相安无事。<br /><br />　　比较重要的，是&amp;ldquo;道&amp;rdquo;。易经指出：道有&amp;ldquo;天道&amp;rdquo;、&amp;ldquo;人道&amp;rdquo;、&amp;ldquo;地道&amp;rdquo;。人居天地之中，必须顶天立地，上半身依&amp;ldquo;天道&amp;rdquo;，下半身重&amp;ldquo;地道&amp;rdquo;。下学地道的种种知识，以求活命；上达天道的精神修养，来提升人生的价值。&amp;ldquo;道&amp;rdquo;至少有三个层次，最高是&amp;ldquo;不可说的&amp;rdquo;，其次是&amp;ldquo;很难说的&amp;rdquo;，还有&amp;ldquo;可以说的&amp;rdquo;。可以说的部分，我们把它称为秩序、规矩、制度、法则。<br /><br />　　伏羲氏当年，人类生活在自然状态中，借由对大自然种种景象的观察，采用一阴(&amp;mdash;&amp;mdash;)一阳(-)两个符号，将比较熟悉，和生活有密切关系的八种景象，以八卦来表示，告诉大家应该遵守的规矩，必须保持的秩序，从共同认可的法则中，建立若干制度，实在有很了不起的贡献。<br /><br />　　周文王看到商纣王暴虐无道，人民无辜受苦，唯恐好不容易建立起来的政治理念和社会秩序，遭受扭曲和破坏而逐渐丧失，或造成错乱。于是把八卦两两相重，两个单卦重叠起来，成为六十四个重卦。八八六十四，同样是排列组合的必然结果，一个不能多，也一个少不了。<br /><br />　　周文王把毕生累积的宝贵心得和难得经验，透过卦辞和爻辞，分别加以注解。利用大众关心未来变化，又喜欢趋吉避凶的心理，设计出一套占筮的方法，一方面掩饰自己的用意，逃避纣王的迫害；一方面也经由大家的占卜，推广宇宙秩序的观念，使之继续发扬光大。<br /><br />　　周朝成立之后，设置正式的占筮官员，遇有国家大事，便占卜成卦，相当于向君王作一次政治哲学的专题报告，也促使大家对天人合一有进一步的认识。由此可见，周文王以神道设教的苦心，令人由衷敬佩。<br /><br />　　孔子生长在混乱的春秋时代，对于乱臣贼子的不守秩序，十分厌恶；看到暴君污吏的横征暴敛，更是深恶痛绝。于是根据鲁史而作《春秋》，目的在使乱臣贼子心生畏惧而改变作为。但是作《春秋》原本是天子才能做的事情，孔子不是天子，恐别人说他僭越，所以有&amp;ldquo;知我者其惟春秋乎！罪我者其惟春秋乎&amp;rdquo;的感慨。后来他研究易理，既欣赏周文王以神道设教的方式，又担心占卜被误用，搞不好就会造成严重的迷信。盲目接受占卜的结果，等于放弃可贵的自主性和创造力，对人生的意义和价值，都有负面的影响。这才为易经作传，希望把易经的道理，说明得更加符合时代的要求。<br /><br />　　孔子生时，距离周文王重卦，已经有五百年之久。种种变迁，使他不得不说出一些和卦爻辞不一样的话。他的重点，在把宇宙秩序和人生规律，更加紧密地连结起来，并且加强道德实践的重要性，把它视为趋吉避凶能否有效的根本要素。后人把这些批注易经的传，称为&amp;ldquo;十翼&amp;rdquo;。因为一共算起来，刚好有十种，好比《易经》添加了十只强有力的翅膀，从此振翼高飞，可以发挥大用了。我们把易理的弘扬，当做大用；而将占卜的功能，看成小用。希望大家多多研究易理，透过象、数、理的连锁作用，来掌握未来的变化，寻求趋吉避凶的有效途径。<br /><br />　　&amp;ldquo;象&amp;rdquo;就是现代常说的现象，&amp;ldquo;数&amp;rdquo;代表我们十分重视的数据，而&amp;ldquo;理&amp;rdquo;便是依据现象和数据，推论出背后的道理。说出为什么会这样，而又必然产生哪些后果，我们现代把这种过程，叫做推理。<br /><br />　　推理和占卜，其实可以联合运用。资讯充足，数据准确时，当然方便推理。若是资讯不足，数据缺乏，而又自己拿不定主意，或者左右为难，以致摇摆不定时，为什么不能借由占卜，来找到自己的定位？这对寻求此时、此地合理的平衡点，很有助益。<br /><br />　　以上叙述，是不是完全符合事实，我们真的没有把握。其实经历这样漫长的岁月，对于当时的真实状况，恐十白谁也没有把握。我们只是按照象、数、理的连锁作用，把它推论出来，作为一个忠诚的易理实践者，尽一份微薄的心力而已。<br /><br />　　历史看起来好像是一、二人创造出来的，实际上却是当代所有的人，共同写出来的。我们正在写历史，真正的意思是：我们每一个人，都在写一部分的历史。把它叫做&amp;ldquo;共业&amp;rdquo;，并没有什么不好或者神秘的意味。<br /><br />　　易学经过伏羲、文王、孔子三位贤人，接棒跑了三千五百年，才有辉煌的成果。孔子以后，每一个时代，都有很多有志之士，前仆后继，不断地研究发展。经历了两千多年，还是有很多由于看不懂、听不明白，也想不通。虽然有很大的热诚，却不得不望而兴叹，擦身而过。<br /><br />　　近四百年来，西方文化成为引领世界的主流。把易学放在一旁，置之脑后，并没有什么大不了的损失。如今冷静下来，稍微算一算账，发现由西方主导的结果，竟然是浪费地球能源、破坏自然生态、漠视社会正义、欺压弱势族群。这才猛然觉醒，是不是应该回头看看古老的易经？能够经历这么久远，还有人舍不得丢弃，是不是另有一番道理？我们恭逢这样的难得机会，不自量力，抱着&amp;ldquo;何德何能&amp;rdquo;的愧疚心态，做出&amp;ldquo;人人看得懂&amp;rdquo;的尝试，深切盼望各界先进朋友，不吝赐教为幸。<br /><br />　　曾仕强<br /><br />　　刘君政<br /><br />　　谨识于台湾师范大学<br /><br />　　二〇〇八年八月八日</p>";

        String restored1 = HtmlUtil.unescape(ss);
        System.out.println(restored1);
        String restored = HtmlUtil.unescape(restored1);
        System.out.println(restored);

    }
}
