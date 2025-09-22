package org.emilia.tienchin.utils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.OperatingSystem;

/**
 * 用户代理工具类，用于解析浏览器和操作系统信息
 */
public class UserAgentUtils {

    /**
     * 获取浏览器名称
     */
    public static String getBrowserName(String userAgent) {
        UserAgent ua = UserAgent.parseUserAgentString(userAgent);
        Browser browser = ua.getBrowser();
        return browser.getName();
    }

    /**
     * 获取操作系统名称
     */
    public static String getOsName(String userAgent) {
        UserAgent ua = UserAgent.parseUserAgentString(userAgent);
        OperatingSystem os = ua.getOperatingSystem();
        return os.getName();
    }
}
