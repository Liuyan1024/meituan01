package cn.edu.zhku.meituan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.edu.zhku.meituan.mapper.TbUserMapper;
import cn.edu.zhku.meituan.pojo.TbUser;
import cn.edu.zhku.meituan.pojo.TbUserExample;
import cn.edu.zhku.meituan.pojo.TbUserExample.Criteria;
import cn.edu.zhku.meituan.service.UserService;
import cn.edu.zhku.meituan.util.E3Result;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;
	
	/**
	 * 用户注册
	 */
	public E3Result register(TbUser user) {
		//数据有效性校验
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) 
				|| StringUtils.isBlank(user.getPhone())) {
			return E3Result.build(400, "用户数据不完整，注册失败");
		}
		
		//补全pojo的属性
		user.setCreated(new Date());
		user.setUpdated(new Date());
		//对密码进行md5加密
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		//把用户数据插入到数据库中
		userMapper.insert(user);
		//返回添加成功
		return E3Result.ok();

	}

	/**
	 * 用户登录
	 */
	public E3Result userLogin(String username, String password) {
		// 1、判断用户和密码是否正确
		//根据用户名查询用户信息
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			//返回登录失败
			return E3Result.build(400, "用户名或密码错误");
		}
		//取用户信息
		TbUser user = list.get(0);
		//判断密码是否正确
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			// 2、如果不正确，返回登录失败
			return E3Result.build(400, "用户名或密码错误");
		}
		
		
		// 6、把token返回
		return E3Result.ok(user);
	}

	/**
	 * 根据用户id查询用户信息
	 */
	public TbUser selectUserInfoByUserId(Long userId) {
		TbUser user = userMapper.selectByPrimaryKey(userId);
		return user;
	}


	/**
	 * 查询所有的用户
	 */
	public List<TbUser> findTbUser() {
		TbUserExample example = new TbUserExample();
		List<TbUser> list = userMapper.selectByExample(example);
		return list;
	}
	
	
	/**
	 * 删除用户
	 */
	public void delTbUser(Long id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateTbUser(TbUser user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

}
