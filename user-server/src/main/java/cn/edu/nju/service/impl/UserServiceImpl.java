package cn.edu.nju.service.impl;

import cn.edu.nju.constant.ErrorCode;
import cn.edu.nju.constant.UserStatus;
import cn.edu.nju.dao.UserDao;
import cn.edu.nju.dto.UserDTO;
import cn.edu.nju.form.AddUserForm;
import cn.edu.nju.form.ModifyUserPasswordForm;
import cn.edu.nju.model.UserEntity;
import cn.edu.nju.service.UserService;
import cn.edu.nju.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * @author hongchuanwang
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
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
            throw ErrorCode.generateCustomException(ErrorCode.USER_NOT_EXIST);
        }

        UserEntity userEntity = userEntityOptional.get();
        userEntity.setPassword(passwordEncoder.encode(form.getPassword()));
        userEntity.setModifyTime(new Timestamp(System.currentTimeMillis()));
        userDao.save(userEntity);
        return true;
    }

    @Override
    public UserVO getUserDetail(Long userId) {
        UserEntity user = userDao.findById(userId).orElseThrow(() -> ErrorCode.generateCustomException(ErrorCode.USER_NOT_EXIST));
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setUserId(user.getId());
        return userVO;
    }

    private UserDTO checkUser(String rawPassword, UserEntity userEntity) {
        if (userEntity == null) {
            throw ErrorCode.generateCustomException(ErrorCode.USER_NOT_EXIST);
        }

        if (passwordEncoder.matches(rawPassword, userEntity.getPassword())) {
            UserDTO userDTO = new UserDTO();
            //这里的权限另外处理
            userDTO.setPermissions(0);
            BeanUtils.copyProperties(userEntity, userDTO);
            return userDTO;
        } else {
            throw ErrorCode.generateCustomException(ErrorCode.PASSWORD_ERROR);
        }
    }
}
