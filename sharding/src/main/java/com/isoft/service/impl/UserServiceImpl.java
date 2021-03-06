package com.isoft.service.impl;

import com.isoft.dao.UserDao;
import com.isoft.entity.User;
import com.isoft.service.UserService;
import com.isoft.vo.ResponseJson;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Override
    // 本地事务
    //@ShardingTransactionType(TransactionType.LOCAL)
    // 两阶段事务（支持夸库事务）
    @ShardingTransactionType(TransactionType.XA)
    @Transactional
    public ResponseJson insertUser(String phone) {
        ResponseJson responseJson = new ResponseJson();
        try{
            User user = new User();
            //SnowFlakeUtil.initWorkerIdByIPKeyGenerator();
            // Sharding-JDBC采用snowflake算法作为默认的分布式分布式自增主键策略，用于保证分布式的情况下可以无中心化的生成不重复的自增序列。
            //因此自增主键可以保证递增，但无法保证连续。而snowflake算法的最后4位是在同一毫秒内的访问递增值。
            // 因此，如果毫秒内并发度不高，最后4位为零的几率则很大。
            // 因此并发度不高的应用生成偶数主键的几率会更高。
            DefaultKeyGenerator keyGenerator = new DefaultKeyGenerator();
            user.setId(keyGenerator.generateKey().longValue());
            user.setPhone(phone);
            user.setEmail("");
            user.setName("");
            userDao.save(user);
            int result = 10/0;
        }catch (Exception e){
            // 要加这个玩意，不然事务回滚会失败
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("添加新用户报错：",e);
            responseJson.setSuccess(false);
            responseJson.setCode(500);
            responseJson.setMsg("服务器报错");
            return responseJson;
        }
        return responseJson;
    }
/*

    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional
    public ResponseJson updateUserUserNameByPhone(String phone) {
        ResponseJson responseJson = new ResponseJson();
        try{
            String userName = "";
            User user = userDao.findUserByPhone(phone);
            user.setName(userName);
            // 需要注意：如果只是分表或者分库，记得是根据分片字段是更新数据，如果是分表分库是根据分表的分片字段去更新数据，不然会出现路由失败的情况，即更新数据失败
            //userMapper.updateByPrimaryKeySelective(user);
            //userMapper.updateUserNameById(userName,user.getId());
            userDao.updateUserNameByPhone(userName,phone);
            // 测试出现异常是否会回滚
            int result = 10/0;
        }catch (Exception e){
            // 要加这个玩意，不然事务回滚会失败
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("更新用户昵称报错：",e);
            responseJson.setSuccess(false);
            responseJson.setCode(500);
            responseJson.setMsg("服务器报错");
            return responseJson;
        }
        return responseJson;
    }

    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional
    public ResponseJson deleteUserByPhone(String phone) {
        ResponseJson responseJson = new ResponseJson();
        try{
            userDao.deleteUserByPhone(phone);
        }catch (Exception e){
            // 要加这个玩意，不然事务回滚会失败
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("删除用户报错：",e);
            responseJson.setSuccess(false);
            responseJson.setCode(500);
            responseJson.setMsg("服务器报错");
            return responseJson;
        }
        return responseJson;
    }

    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional
    public ResponseJson findUserByPhone(String phone) {
        ResponseJson responseJson = new ResponseJson();
        try{
            User user = userDao.findUserByPhone(phone);
            responseJson.setData(user);
        }catch (Exception e){
            // 要加这个玩意，不然事务回滚会失败
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("查询用户信息报错：",e);
            responseJson.setSuccess(false);
            responseJson.setCode(500);
            responseJson.setMsg("服务器报错");
            return responseJson;
        }
        return responseJson;
    }
*/

}
