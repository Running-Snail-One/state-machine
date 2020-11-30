package com.demo.constant;

/**
 * 更新配置中心所用到的常量
 */
public interface NacosConstants {
    //状态
    static String STATE = "0";
    //事件
    static String EVENT = "1";
    //流转状态
    static String TRANSTION = "2";
    //配置中心状态key
    static String _STATES = "_states";
    //配置中心事件key
    static String _EVENTS = "_events";
    //配置中心流转状态key
    static String _TRANSITION = "_transition";
    //换行分隔符
    static String RETURNSEPARATOR = "\n";
    //2个空格符 + "- "
    static String TWO_SPACE_ONE_LINE = "  - ";
    //4个空格符 + "- "
    static String FOUR_SPACE_ONE_LINE = "    - ";
    //2个空格符
    static String FOUR_SPACE = "    ";
    //换行符
    static String RETURN = "\n";
    //流转状态拼接常量，
    static String TRANSTION_SOURCE = "source: ";
    static String TRANSTION_TARGET = "target: ";
    static String TRANSTION_EVENT = "event: ";

}
