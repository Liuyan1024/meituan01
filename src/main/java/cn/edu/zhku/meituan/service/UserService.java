package cn.edu.zhku.meituan.service;

import java.util.List;

import cn.edu.zhku.meituan.pojo.TbUser;
import cn.edu.zhku.meituan.util.E3Result;

public interface UserService {
	
	public E3Result register(TbUser user);
	public E3Result userLogin(String username, String password);
	public TbUser selectUserInfoByUserId(Long userId);
	public List<TbUser> findTbUser();
	public void delTbUser(Long id);
	public void updateTbUser(TbUser user);
}
