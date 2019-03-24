package com.gupao.pattern.prototype.deep;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 19:57
 * @email soda931vzr@163.com
 * @description
 */
public class QiTianDaShengTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        try {
            QiTianDaSheng clone = (QiTianDaSheng)qiTianDaSheng.clone();
            System.out.println("深克隆：" + (qiTianDaSheng.jinGuBang == clone.jinGuBang));
        } catch (Exception e) {
            e.printStackTrace();
        }

        QiTianDaSheng q = new QiTianDaSheng();
        QiTianDaSheng n = q.shallowClone(q);
        System.out.println("浅克隆：" + (q.jinGuBang == n.jinGuBang));
    }
}