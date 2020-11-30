package com.demo.constant;

/**
 * 更新配置中心所用到的常量
 */
public interface NacosConstants {
    //状态
     String STATE = "0";
    //事件
     String EVENT = "1";
    //流转状态
     String TRANSTION = "2";
    //配置中心状态key
     String _STATES = "_states:";
    //配置中心事件key
     String _EVENTS = "_events:";
    //配置中心流转状态key
     String _TRANSITION = "_transition:";
    //换行分隔符
     String RETURNSEPARATOR = "\n";
    //2个空格符 + "- "
     String TWO_SPACE_ONE_LINE = "  - ";
    //4个空格符 + "- "
     String FOUR_SPACE_ONE_LINE = "    - ";
    //4个空格符
     String FOUR_SPACE = "    ";
    //两个空格符
     String TWO_SPACE = "  ";
    //换行符
     String RETURN = "\n";
    //回车符
     String R = "\r";
    //1个横线
     String ONE_LINE = "- ";
    //流转状态拼接常量，
     String TRANSITION_SOURCE = "source: ";
     String TRANSITION_TARGET = "target: ";
     String TRANSITION_EVENT = "event: ";
}
