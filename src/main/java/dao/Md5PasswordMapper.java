package dao;

import entity.Md5Password;

public interface Md5PasswordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Md5Password record);

    int insertSelective(Md5Password record);

    Md5Password selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Md5Password record);

    int updateByPrimaryKey(Md5Password record);
}