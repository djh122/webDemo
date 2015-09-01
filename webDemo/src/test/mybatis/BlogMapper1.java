package test.mybatis;

import org.apache.ibatis.annotations.Select;


public interface BlogMapper1 {

	@Select("select * from blog where id=1")
	public Blog selectOne();
}
