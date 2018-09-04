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
	 * �û�ע��
	 */
	public E3Result register(TbUser user) {
		//������Ч��У��
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) 
				|| StringUtils.isBlank(user.getPhone())) {
			return E3Result.build(400, "�û����ݲ�������ע��ʧ��");
		}
		
		//��ȫpojo������
		user.setCreated(new Date());
		user.setUpdated(new Date());
		//���������md5����
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		//���û����ݲ��뵽���ݿ���
		userMapper.insert(user);
		//������ӳɹ�
		return E3Result.ok();

	}

	/**
	 * �û���¼
	 */
	public E3Result userLogin(String username, String password) {
		// 1���ж��û��������Ƿ���ȷ
		//�����û�����ѯ�û���Ϣ
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		//ִ�в�ѯ
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			//���ص�¼ʧ��
			return E3Result.build(400, "�û������������");
		}
		//ȡ�û���Ϣ
		TbUser user = list.get(0);
		//�ж������Ƿ���ȷ
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			// 2���������ȷ�����ص�¼ʧ��
			return E3Result.build(400, "�û������������");
		}
		
		
		// 6����token����
		return E3Result.ok(user);
	}

	/**
	 * �����û�id��ѯ�û���Ϣ
	 */
	public TbUser selectUserInfoByUserId(Long userId) {
		TbUser user = userMapper.selectByPrimaryKey(userId);
		return user;
	}


	/**
	 * ��ѯ���е��û�
	 */
	public List<TbUser> findTbUser() {
		TbUserExample example = new TbUserExample();
		List<TbUser> list = userMapper.selectByExample(example);
		return list;
	}
	
	
	/**
	 * ɾ���û�
	 */
	public void delTbUser(Long id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateTbUser(TbUser user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

}
