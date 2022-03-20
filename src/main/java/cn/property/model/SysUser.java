package cn.property.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
public class SysUser implements UserDetails {

    private String id;
    private String buildingId;
    private String name;
    private String username;
    private String password;
    private Integer sex;
    private String phone;
    private String address;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date joinDate;
    private Integer isDel;

    private SysRole sysRole;

    private Collection<? extends GrantedAuthority> authorities;

    public SysUser() {
    }

    public SysUser(String id, String name, String username, String password, SysRole sysRole, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.sysRole = sysRole;
        this.authorities = authorities;
    }

    //账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户是否未被锁
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //帐户密码是否过期，一般有的密码要求性高的系统会使用到，比较每隔一段时间就要求用户重置密码
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //帐号是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
