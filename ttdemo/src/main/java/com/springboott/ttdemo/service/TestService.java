package com.springboott.ttdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboott.ttdemo.po.User;

/**
 * <p>
 * Test 服务类
 * </p>
 *
 * @author Sam
 * @since 2019-10-09
 */
public interface TestService extends IService<User> {

    void toDoOpenTran(Boolean error);

    void transRest();

    void update1();

    void update2();

    void update3();

    void update4();

}
