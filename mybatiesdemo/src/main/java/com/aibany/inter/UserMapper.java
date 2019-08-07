package com.aibany.inter;


import com.aibany.User;

public interface UserMapper {

    User queryUser(int id);

    User findUserByUsername(String name);

}
