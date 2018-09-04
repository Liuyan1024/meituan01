package cn.edu.zhku.meituan.service;

import java.util.List;

import cn.edu.zhku.meituan.pojo.TbComment;
import cn.edu.zhku.meituan.pojo.TbMerchants;

public interface CommentService {

	public void addComment(TbComment comment);
	public List<TbComment> geTbComment(Long uid);
	public List<TbComment> geTbCommentByMid(Long mid);
	public List<TbComment> geTbCommentByOidAndUid(Long oid,Long uid);
	
}
