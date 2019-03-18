package com.gupao.pattern.prototype.deep;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 19:49
 * @email soda931vzr@163.com
 * @description
 */
public class QiTianDaSheng extends Monkey implements Cloneable, Serializable {

    public JinGuBang jinGuBang;

    public QiTianDaSheng() {
        this.birthday = new Date();
        this.jinGuBang = new JinGuBang();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return this.deepClone();
    }

    public Monkey deepClone() {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            QiTianDaSheng daSheng = (QiTianDaSheng) ois.readObject();
            daSheng.birthday = new Date();

            return daSheng;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public QiTianDaSheng shallowClone(QiTianDaSheng monkey) {

        QiTianDaSheng daSheng = new QiTianDaSheng();
        daSheng.weight = monkey.weight;
        daSheng.height = monkey.height;
        daSheng.jinGuBang = monkey.jinGuBang;
        daSheng.birthday = new Date();

        return daSheng;
    }
}