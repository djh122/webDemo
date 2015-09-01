package com.djh.mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.djh.model.UserBean;

@Repository
public interface UserMapper {
	
	/**
	 * @param merId
	 * @return
	 */
	UserBean queryById( @Param("id")int id );
	
	/**
	 * @param merId
	 * @return
	 */
	@Select(value="select count(1) from tc_merchant where enable=1 and mer_id=#{merId}")
	int merIdIsValid( @Param("merId")String merId );

}