package io.file;

import cn.hutool.core.io.FileUtil;

/**
 * 替换配置文件
 */
public class ReplaceProfiles {

    public static void main(String[] args) {
        local();
        //develop();
    }

    private static void local(){
        //替换user
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\本地启动需要修改的配置\\user\\user-mongodb.properties","E:\\LineWell\\henan\\ycsl\\license-user\\license-user-config\\src\\main\\resources\\com\\linewell\\license\\config\\user\\user-mongodb.properties",true);
        //替换gridfs
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\本地启动需要修改的配置\\gridfs\\gridfs-mongodb.properties","E:\\LineWell\\henan\\ycsl\\license-gridfs\\license-gridfs-config\\src\\main\\resources\\com\\linewell\\license\\config\\gridfs\\gridfs-mongodb.properties",true);
        //替换owap
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\本地启动需要修改的配置\\owap\\owap-mongodb.properties","E:\\LineWell\\henan\\ycsl\\was-owap\\was-owap-config\\src\\main\\resources\\conf\\serviceConf\\owap-mongodb.properties",true);
        //替换web
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\本地启动需要修改的配置\\web\\integrated-spring-config.xml","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\resource\\conf\\integrated-spring-config.xml",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\本地启动需要修改的配置\\web\\memcached.properties","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\webapp\\WEB-INF\\config\\cache\\memcached.properties",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\本地启动需要修改的配置\\web\\node.properties","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\webapp\\WEB-INF\\config\\node.properties",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\本地启动需要修改的配置\\web\\oneThing.properties","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\resource\\conf\\oneThing.properties",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\本地启动需要修改的配置\\web\\redis.properties","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\resource\\conf\\redis.properties",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\本地启动需要修改的配置\\web\\web.xml","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\webapp\\WEB-INF\\web.xml",true);
    }

    private static void develop(){
        //替换user
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\user\\redis.properties","E:\\LineWell\\henan\\ycsl\\license-user\\license-user-config\\src\\main\\resources\\com\\linewell\\license\\config\\user\\redis.properties",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\user\\user-dubbo-config.xml","E:\\LineWell\\henan\\ycsl\\license-user\\license-user-config\\src\\main\\resources\\com\\linewell\\license\\config\\user\\user-dubbo-config.xml",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\user\\user-mongodb.properties","E:\\LineWell\\henan\\ycsl\\license-user\\license-user-config\\src\\main\\resources\\com\\linewell\\license\\config\\user\\user-mongodb.properties",true);
        //替换gridfs
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\gridfs\\gridfs-dubbo-config.xml","E:\\LineWell\\henan\\ycsl\\license-gridfs\\license-gridfs-config\\src\\main\\resources\\com\\linewell\\license\\config\\gridfs\\gridfs-dubbo-config.xml",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\gridfs\\gridfs-mongodb.properties","E:\\LineWell\\henan\\ycsl\\license-gridfs\\license-gridfs-config\\src\\main\\resources\\com\\linewell\\license\\config\\gridfs\\gridfs-mongodb.properties",true);
        //替换owap
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\owap\\owap-dubbo-config.xml","E:\\LineWell\\henan\\ycsl\\was-owap\\was-owap-config\\src\\main\\resources\\conf\\serviceConf\\owap-dubbo-config.xml",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\owap\\owap-mongodb.properties","E:\\LineWell\\henan\\ycsl\\was-owap\\was-owap-config\\src\\main\\resources\\conf\\serviceConf\\owap-mongodb.properties",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\owap\\redis.properties","E:\\LineWell\\henan\\ycsl\\was-owap\\was-owap-config\\src\\main\\resources\\conf\\serviceConf\\redis.properties",true);
        //替换web
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\web\\integrated-spring-config.xml","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\resource\\conf\\integrated-spring-config.xml",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\web\\memcached.properties","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\webapp\\WEB-INF\\config\\cache\\memcached.properties",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\web\\node.properties","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\webapp\\WEB-INF\\config\\node.properties",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\web\\oneThing.properties","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\resource\\conf\\oneThing.properties",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\web\\redis.properties","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\resource\\conf\\redis.properties",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\web\\web.xml","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\webapp\\WEB-INF\\web.xml",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\web\\sso_oauth2.properties","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\resource\\sso_oauth2.properties",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\web\\InterfaceServerEnum.java","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\java\\com\\linewell\\login\\new_sso\\enums\\InterfaceServerEnum.java",true);
        FileUtil.copy("E:\\LineWell\\henan\\文档\\一窗受理\\配置文件\\开发环境文件\\web\\index.js","E:\\LineWell\\henan\\ycsl\\ycslypt_web\\src\\main\\webapp\\core\\themes\\theme2\\js\\index.js",true);

    }
}
