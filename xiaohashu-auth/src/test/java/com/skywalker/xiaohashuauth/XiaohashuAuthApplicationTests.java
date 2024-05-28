package com.skywalker.xiaohashuauth;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author baijj
 * @date 2024-05-28
 */
@SpringBootTest
@Slf4j
public class XiaohashuAuthApplicationTests {

    @Autowired
    private UserDoMapper userDoMapper;

    @Test
    void testInsert(){
        UserDo userDo = UserDo.builder()
                .username("skywalker")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        userDoMapper.insert(userDo);
    }

    @Test
    void testSearch() {
        UserDo userDo = userDoMapper.selectById(1L);
        log.info("userDo:{}", userDo);
    }

    /**
     * 更新数据
     */
    @Test
    void testUpdate() {
        UserDo userDO = UserDo.builder()
                .id(1L)
                .username("sky")
                .updateTime(LocalDateTime.now())
                .build();

        // 根据主键 ID 更新记录
        userDoMapper.updateById(userDO);
    }

    @Test
    void testDelete() {
        userDoMapper.deleteById(1L);
    }
}
