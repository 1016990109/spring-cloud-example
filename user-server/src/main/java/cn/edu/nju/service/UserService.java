package cn.edu.nju.service;

import cn.edu.nju.dto.UserDTO;
import cn.edu.nju.form.AddUserForm;
import cn.edu.nju.form.ModifyUserPasswordForm;
import cn.edu.nju.vo.UserVO;

/**
 * @author hongchuanwang
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param form
     * @return
     */
    boolean addUser(AddUserForm form);

    /**
     * 根据邮箱校验密码是否正确，错误返回 null
     *
     * @param email
     * @param password
     * @return
     */
    UserDTO checkPasswordByEmail(String email, String password);

    /**
     * 根据手机校验密码是否正确，错误返回 null
     *
     * @param phone
     * @param password
     * @return
     */
    UserDTO checkPasswordByPhone(String phone, String password);

    /**
     * 修改密码
     *
     * @param form
     * @return
     */
    boolean modifyUserPassword(ModifyUserPasswordForm form);

    /**
     * 获取用户详情
     *
     * @param userId
     * @return
     */
    UserVO getUserDetail(Long userId);
}
