package cn.haigle.virtue.mapper.mysql.auth;

import cn.haigle.virtue.common.mybatis.mapper.BaseMapperX;
import cn.haigle.virtue.mapper.dataobject.auth.SysRefreshTokenDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

/**
 * 刷新令牌 Mapper
 *
 * @author haigle
 */
@Mapper
public interface RefreshTokenMapper extends BaseMapperX<SysRefreshTokenDO> {

    default SysRefreshTokenDO selectByTokenHash(String tokenHash) {
        return selectOne(SysRefreshTokenDO::getTokenHash, tokenHash);
    }

    /**
     * 原子地把令牌标记为已轮换，返回 0 表示已被并发请求或重放使用
     */
    default int markUsed(String tokenHash) {
        return update(new LambdaUpdateWrapper<SysRefreshTokenDO>()
                .eq(SysRefreshTokenDO::getTokenHash, tokenHash)
                .eq(SysRefreshTokenDO::getUsed, false)
                .set(SysRefreshTokenDO::getUsed, true));
    }

    default void deleteBySessionId(String sessionId) {
        delete(SysRefreshTokenDO::getSessionId, sessionId);
    }

    default void deleteByUserId(Long userId) {
        delete(SysRefreshTokenDO::getUserId, userId);
    }

    default int deleteExpired(LocalDateTime now) {
        return delete(new LambdaQueryWrapper<SysRefreshTokenDO>()
                .lt(SysRefreshTokenDO::getExpiresTime, now));
    }

}
