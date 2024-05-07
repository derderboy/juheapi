package com.derder.constant;

/**
 * 地址常量
 *
 * @author YJL
 * @version 1.0
 */
public interface MyUrl {
    /**
     * 网关
     */
    String GATEWAY_HOST = "http://localhost:8090";
    /**
     * 获取随机数
     */
    String RANDOM_NUMBER = "/api/RandomNumber";
    /**
     * 获取你的名字
     */
    String GET_YOURNAME = "/api/name/user";
    /**
     * 获取随机动物图片
     */
    String GET_ANIMALIMAGE = "/api/randomImage";
    /**
     * ChatGpt聊天
     */
    String DO_CHAT = "/api/mry5l/doChatMessage";
    /**
     * 获取随机情话
     */
    String TALK_LOVE = "/api/mry5l/talkLove";

    /**
     * 获取随机风景图片
     */
    String RANDOM_SCENERY = "/api/mry5l/RandomScenery";

    /**
     * 获取摸鱼日历
     */
    String MO_YU = "/api/mry5l/getMoYu";

    /**
     * 获取随机壁纸
     */
    String RANDOM_WALLPAPER = "/api/mry5l/getRandomWallpaper";

    /**
     * 获取星座运势
     */
    String HOROSCOPE = "/api/mry5l/getHoroscope";
}
