package cn.edu.nju.service.impl;

import cn.edu.nju.exception.CustomException;
import cn.edu.nju.constant.UserStatus;
import cn.edu.nju.dao.UserDao;
import cn.edu.nju.dto.UserDTO;
import cn.edu.nju.form.AddUserForm;
import cn.edu.nju.form.ModifyUserPasswordForm;
import cn.edu.nju.model.UserEntity;
import cn.edu.nju.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * @author hongchuanwang
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean addUser(AddUserForm form) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(form, userEntity);
        userEntity.setPassword(passwordEncoder.encode(form.getPassword()));
        userEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userEntity.setStatus(UserStatus.NORMAL.getValue());
        userDao.save(userEntity);
        return true;
    }

    @Override
    public UserDTO checkPasswordByEmail(String email, String password) {
        UserEntity userEntity = userDao.findByEmail(email);

        return checkUser(password, userEntity);
    }

    @Override
    public UserDTO checkPasswordByPhone(String phone, String password) {
        UserEntity userEntity = userDao.findByPhone(phone);

        return checkUser(password, userEntity);
    }

    @Override
    public boolean modifyUserPassword(ModifyUserPasswordForm form) {
        Optional<UserEntity> userEntityOptional = userDao.findById(form.getId());
        if (!userEntityOptional.isPresent()) {
            throw new CustomException(-1, "用户Id不存在");
        }

        UserEntity userEntity = userEntityOptional.get();
        userEntity.setPassword(passwordEncoder.encode(form.getPassword()));
        userDao.save(userEntity);
        return true;
    }

    private UserDTO checkUser(String rawPassword, UserEntity userEntity) {
        if (userEntity == null) {
            throw new CustomException(-1, "用户不存在");
        }

        if (passwordEncoder.matches(rawPassword, userEntity.getPassword())) {
            return new UserDTO(userEntity.getId(), userEntity.getEmail(), userEntity.getPhone(), 1);
        } else {
            throw new CustomException(-1, "密码不正确");
        }
    }
}
