package cn.edu.zhku.meituan.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.edu.zhku.meituan.mapper.TbMerchantsMapper;
import cn.edu.zhku.meituan.pojo.TbItem;
import cn.edu.zhku.meituan.pojo.TbItemExample;
import cn.edu.zhku.meituan.pojo.TbMerchants;
import cn.edu.zhku.meituan.pojo.TbMerchantsExample;
import cn.edu.zhku.meituan.pojo.TbUser;
import cn.edu.zhku.meituan.pojo.TbUserExample;
import cn.edu.zhku.meituan.pojo.TbMerchantsExample.Criteria;
import cn.edu.zhku.meituan.service.ItemService;
import cn.edu.zhku.meituan.service.MerchantsService;
import cn.edu.zhku.meituan.util.E3Result;
import cn.edu.zhku.meituan.util.EasyUIDataGridResult;
@Service
public class MerchantsServiceImpl implements MerchantsService {
	
	@Autowired
	private TbMerchantsMapper merchantsMapper;
	
	
	/**
	 * ��ѯ���е�ң�����ҳ��
	 */
	public EasyUIDataGridResult getMerchantsList(int page, int rows) {
		//���÷�ҳ��Ϣ
		PageHelper.startPage(page, rows);
		//ִ�в�ѯ
		TbMerchantsExample example = new TbMerchantsExample();
		List<TbMerchants> list = merchantsMapper.selectByExample(example);
		//����һ������ֵ����
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//ȡ��ҳ���
		PageInfo<TbMerchants> pageInfo = new PageInfo<>(list);
		//ȡ�ܼ�¼��
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	/**
	 * ���ݵ�ҷ���id��ѯ���еĵ��
	 */
	public List<TbMerchants> getMerchantsByCid(Long cid) {
		TbMerchantsExample example = new TbMerchantsExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbMerchants> tbMerchantsList = merchantsMapper.selectByExample(example );
		return tbMerchantsList;
	}

	/**
	 * ��ѯ���е��
	 */
	public List<TbMerchants> getMerchantsList() {
		TbMerchantsExample example = new TbMerchantsExample();
		List<TbMerchants> tbMerchantsList = merchantsMapper.selectByExample(example );
		return tbMerchantsList;
	}
	
	//ɾ��
	public void delMerchants(Long id) {
		merchantsMapper.deleteByPrimaryKey(id);
	}
	
	//
	public TbMerchants getMerchantsById(Long id) {
		TbMerchants merchants = merchantsMapper.selectByPrimaryKey(id);
		return merchants;
	}
	
	//�޸�
	public void updateMerchant(TbMerchants  merchants) {
		merchantsMapper.updateByPrimaryKeySelective(merchants);
	}
	
	/**
	 * ���ݵ���û���ѯ����û����루����û���¼��
	 */
	public E3Result MerchantsLogin(String username, String password) {
		// 1���ж��û��������Ƿ���ȷ
		//�����û�����ѯ�û���Ϣ
		TbMerchantsExample example = new TbMerchantsExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(username);
		//ִ�в�ѯ
		List<TbMerchants> list = merchantsMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			//���ص�¼ʧ��
			return E3Result.build(400, "�û������������");
		}
		//ȡ�û���Ϣ
		 TbMerchants merchants = list.get(0);
		//�ж������Ƿ���ȷ
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(merchants.getEstimate())) {
			// 2���������ȷ�����ص�¼ʧ��
			return E3Result.build(400, "�û������������");
		}
	
		// 6����token����
		return E3Result.ok(merchants);
	}
	
	
	/**
	 * ��ӵ��(���ע��)
	 */
	public Long addMerchants(TbMerchants merchants) {
		//������Ч��У��
		if (StringUtils.isBlank(merchants.getName()) || StringUtils.isBlank(merchants.getEstimate()) 
				|| StringUtils.isBlank(merchants.getPhone())) {
			E3Result.build(400, "�û����ݲ�������ע��ʧ��");
		}
				
		//��ȫ����
		merchants.setCreated(new Date());
		merchants.setUpdated(new Date());
		//���������md5����
		String md5Pass = DigestUtils.md5DigestAsHex(merchants.getEstimate().getBytes());
		merchants.setEstimate(md5Pass);
		
		merchantsMapper.insert(merchants);
		return merchants.getCid();
	}

	/**
	 * ���µ��
	 */
	public void updateMerchants(TbMerchants merchants) {
		merchantsMapper.updateByPrimaryKey(merchants);
	}

	
	

	

}
