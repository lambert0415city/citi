package com.cs.resolver;

import com.cs.entity.Result;
import com.cs.entity.User;
import com.cs.entity.UserInput;
import com.cs.util.EmptyUtils;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: szh
 * @since:
 */

@Component
public class MutationResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private static final Logger logger = LogManager.getLogger(MutationResolver.class);

    public Result addmsg(String input1, String input2) {
        logger.info("Mutation Resolver ==> addMsg");
        logger.info("params: input1:{}, input2:{}, password:{}, description:{}",
                input1, input2);

        if(EmptyUtils.isNotEmpty(input1) && EmptyUtils.isNotEmpty(input2)){
            if(StringUtils.isNumeric(input1) && StringUtils.isNumeric(input2)){
                int result = Integer.parseInt(input1) + Integer.parseInt(input2);
                return new Result("interger add",result);
            }else{
                String result = input1 + input2;
                return new Result(result,200);
            }
        }

        return new Result("failed: params error",205);

    }

    public List<User> addUser(UserInput user){
        logger.info("Mutation Resolver ==> addUser");
        logger.info("params: {}", user);

        List<User> users = new ArrayList<>();
        users.add(new User(1,"张三"));
        users.add(new User(2,"李四"));
        users.add(new User(user.getId(),user.getName()));
        return users;
    }
}
