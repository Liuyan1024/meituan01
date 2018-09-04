package cn.edu.zhku.meituan.mapper;

import cn.edu.zhku.meituan.pojo.TbMerchants;
import cn.edu.zhku.meituan.pojo.TbMerchantsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbMerchantsMapper {
    int countByExample(TbMerchantsExample example);

    int deleteByExample(TbMerchantsExample example);

    int deleteByPrimaryKey(Long cid);

    int insert(TbMerchants record);

    int insertSelective(TbMerchants record);

    List<TbMerchants> selectByExample(TbMerchantsExample example);

    TbMerchants selectByPrimaryKey(Long cid);

    int updateByExampleSelective(@Param("record") TbMerchants record, @Param("example") TbMerchantsExample example);

    int updateByExample(@Param("record") TbMerchants record, @Param("example") TbMerchantsExample example);

    int updateByPrimaryKeySelective(TbMerchants record);

    int updateByPrimaryKey(TbMerchants record);
}