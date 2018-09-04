package cn.edu.zhku.meituan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zhku.meituan.mapper.TbCommentMapper;
import cn.edu.zhku.meituan.pojo.TbComment;
import cn.edu.zhku.meituan.pojo.TbCommentExample;
import cn.edu.zhku.meituan.pojo.TbCommentExample.Criteria;
import cn.edu.zhku.meituan.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private TbCommentMapper commentMapper ;
	
	/**
	 * 添加评论
	 */
	public void addComment(TbComment comment) {
		commentMapper.insert(comment);
	}

	/**
	 * 根据用户id查看评论
	 */
	public List<TbComment> geTbComment(Long uid) {
		TbCommentExample example = new TbCommentExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(uid);
		List<TbComment> list = commentMapper.selectByExample(example);
		return list;
	}

	/**
	 * 根据店家id查询评论
	 */
	public List<TbComment> geTbCommentByMid(Long mid) {
		
		TbCommentExample example = new TbCommentExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andMeridEqualTo(mid);
		
		List<TbComment> list = commentMapper.selectByExample(example);
		
		return list;
	}

	public List<TbComment> geTbCommentByOidAndUid(Long oid, Long uid) {
		TbCommentExample example = new TbCommentExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderidEqualTo(oid);
		criteria.andUseridEqualTo(uid);
		List<TbComment> list = commentMapper.selectByExample(example);
		return list;
	}
	
	
}
