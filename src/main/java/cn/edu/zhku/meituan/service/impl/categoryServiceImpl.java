package cn.edu.zhku.meituan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zhku.meituan.mapper.TbCategoryMapper;
import cn.edu.zhku.meituan.pojo.TbCategory;
import cn.edu.zhku.meituan.pojo.TbCategoryExample;
import cn.edu.zhku.meituan.service.CategoryService;

@Service
public class categoryServiceImpl implements CategoryService {

	@Autowired
	private TbCategoryMapper categoryMapper;

	/**
	 * 查询所有的店家分类
	 */
	public List<TbCategory> selectCategoryList() {
		TbCategoryExample example = new TbCategoryExample();
		List<TbCategory> list = categoryMapper.selectByExample(example );
		return list;
	}
	
	
}
