package com.cs.resolver;


import com.cs.entity.User;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class Query implements GraphQLQueryResolver {

    public User getUser(){
        return new User(1,"张三");
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,"张三"));
        users.add(new User(2,"李四"));
        users.add(new User(3,"王五"));
        return users;
    }
}