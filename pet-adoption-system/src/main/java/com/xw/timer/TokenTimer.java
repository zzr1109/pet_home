package com.xw.timer;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.xw.dao.UserTokenDao;
import com.xw.entity.User;
import com.xw.entity.UserToken;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
public class TokenTimer {

    @Resource
    private UserTokenDao userTokenDao;

    @Scheduled(fixedDelay = 1000 * 60) // 一分钟扫描一次
    public void tokenSet() {
        List<UserToken> userTokenList = userTokenDao.selectAll(new UserToken());
        for (UserToken userToken : userTokenList) {
            if (userToken.getOverdue().compareTo(-1) > 0) {
                DateTime dateTime = DateUtil.offsetMinute(userToken.getCreateTime(), userToken.getOverdue());
                long time = dateTime.getTime();
                if (new Date().getTime() > time) {
                    userTokenDao.deleteById(userToken.getId());
                }
            }
        }
    }
}
