package cn.succy.jfinal.handler;

import com.jfinal.handler.Handler;
import com.jfinal.kit.StrKit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 * @author Succy
 * @date 2017-03-11 12:47
 **/

public class WebSocketHandler extends Handler {

    private Pattern filterUrlRegPatten;

    public WebSocketHandler(String filterUrlReg) {
        if (StrKit.isBlank(filterUrlReg)) {
            throw new IllegalArgumentException("the filterUrlReg can not be blank");
        }
        filterUrlRegPatten = Pattern.compile(filterUrlReg);
    }

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandle) {
        if (filterUrlRegPatten.matcher(target).find()) {
            return;
        }
        next.handle(target, request, response, isHandle);
    }
}
