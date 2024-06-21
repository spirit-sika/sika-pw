package cc.sika.service;

import cc.sika.vo.SikaPWVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;


public interface PasswordService {
    /**
     * 以分页方式获取所有的用户密码, 密码将以vo的形式返回,
     * 此方法不对用户id进行校验, 会获取系统中所有的密码
     * 方法的返回值将存储在Page对象中
     * @see Page
     * @param pageNum 页码
     * @param pageSize 页面容量
     * @return PageHelper分页插件的分页结果对象
     */
    PageInfo<SikaPWVO> listAllPasswords(int pageNum, int pageSize);

    PageInfo<SikaPWVO> listUserPasswords(int pageNum, int pageSize);
}
