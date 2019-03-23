package com.gupao.pattern.observer.events;

import com.gupao.pattern.observer.events.mouserevent.Mouse;
import com.gupao.pattern.observer.events.mouserevent.MouseEventCallback;
import com.gupao.pattern.observer.events.mouserevent.MouseEventType;

/**
 * Created by Tom on 2019/3/17.
 */
public class MouseEventTest {
    public static void main(String[] args) {

        MouseEventCallback callback = new MouseEventCallback();

        Mouse mouse = new Mouse();

        //@谁？  @回调方法
        mouse.addLisenter(MouseEventType.ON_CLICK, callback);
        mouse.addLisenter(MouseEventType.ON_FOCUS, callback);

        mouse.click();

        mouse.focus();


    }
}
