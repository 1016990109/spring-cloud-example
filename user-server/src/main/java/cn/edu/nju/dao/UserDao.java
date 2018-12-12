package cn.edu.nju.dao;

import cn.edu.nju.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

/**
 * @author hongchuanwang
 */
public interface UserDao extends JpaRepository<UserEntity, Long> {
    @Nullable
    UserEntity findByEmail(String email);

    @Nullable
    UserEntity findByPhone(String phone);
}
