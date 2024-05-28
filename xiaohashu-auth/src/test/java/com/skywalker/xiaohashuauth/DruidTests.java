package com.skywalker.xiaohashuauth;

import com.alibaba.druid.filter.config.ConfigTools;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author baijj
 * @date 2024-05-28
 */
@SpringBootTest
@Slf4j
public class DruidTests {

    @Test
    void testEncodePassword() throws Exception {
        String password = "123456";
        String[] arr = ConfigTools.genKeyPair(512);

        //私钥
        log.info("私钥：{}", arr[0]);
        //公钥
        log.info("公钥：{}", arr[1]);

        //通过私钥加密密码
        String encodePassword = ConfigTools.encrypt(arr[0], password);
        log.info("加密后的密码：{}", encodePassword);
    }
}
