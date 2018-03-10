package com.qg.recruit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 小铭
 * Date: 2018/3/9
 * No struggle, talent how to match the willfulness.
 * Description:
 */
@Repository
public interface IpBlackListRepository extends JpaRepository<IpBlackList, Integer> {
    /**
     * 根据ip查找
     *
     * @param ip ip
     * @return 黑名单实体类
     */
    IpBlackList findByIp(@Param("ip") String ip);
}
