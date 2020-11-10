package com.cs.resolver;

import com.cs.entity.Msg;
import com.cs.entity.Result;
import com.cs.entity.User;
import com.cs.entity.UserInput;
import com.cs.util.DateUtil;
import com.cs.util.EmptyUtils;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: szh
 * @since:
 */

@Component
public class MutationResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private static final Logger logger = LogManager.getLogger(MutationResolver.class);

    public Result addmsg(Msg msg) throws ParseException {
        logger.info("Mutation Resolver ==> addMsg");
        logger.info("params: input1:{}, input2:{}",
                msg.getInput1(), msg.getInput2());

        String now = DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss");

        if(EmptyUtils.isNotEmpty(msg)){
            if(StringUtils.isNumeric(msg.getInput1()) && StringUtils.isNumeric(msg.getInput2())){
                int result = Integer.parseInt(msg.getInput1()) + Integer.parseInt(msg.getInput2());
                return new Result("interger add: now" + now, result);
            }else{
                String result = msg.getInput1() + msg.getInput2();
                return new Result(result + " time:"+now,200);
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
