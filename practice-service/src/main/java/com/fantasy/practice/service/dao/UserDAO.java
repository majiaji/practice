package com.fantasy.practice.service.dao;

import com.fantasy.practice.service.domain.UserDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiaji on 16/9/28.
 */
@Repository
public interface UserDAO extends BaseSqlMapper {
    List<UserDO> list();

    UserDO getById();

    int insert(UserDO userDO);

    int update(UserDO userDO);

    int delete(Long id);
}
