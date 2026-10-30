package cn.haigle.virtue.mapper.dataobject.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 刷新令牌
 * <p>每次轮换插入一条新记录，已轮换的记录保留到过期，用于检测令牌重放</p>
 *
 * @author haigle
 */
@TableName("auth_refresh_token")
@Data
public class SysRefreshTokenDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 登录会话ID，同一次登录的所有轮换共用
     */
    private String sessionId;

    private Long userId;

    /**
     * 登录类型 admin/app/pc
     */
    private String loginType;

    /**
     * 令牌的 SHA-256 摘要，数据库不保存明文令牌
     */
    private String tokenHash;

    private LocalDateTime expiresTime;

    /**
     * 是否已轮换
     */
    private Boolean used;

    private LocalDateTime createTime;

}
