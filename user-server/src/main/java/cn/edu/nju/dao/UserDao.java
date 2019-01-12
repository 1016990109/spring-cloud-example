package cn.edu.nju.dao;

import cn.edu.nju.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hongchuanwang
 */
public interface UserDao extends JpaRepository<UserEntity, Long> {
    /**
     * 根据邮箱查找用户
     *
     * @param email
     * @return
     */
    UserEntity findByEmail(String email);

    /**
     * 根据手机查找用户
     *
     * @param phone
     * @return
     */
    UserEntity findByPhone(String phone);
}
