package com.kon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security Configuration
 *
 * EnableGlobalMethodSecurity配置参数说明：
 *
 *      securedEnabled, 默认false, 搭配 {@link org.springframework.security.access.annotation.Secured} 使用
 *    需要注意的是，Security在设置权限时，默认添加了ROLE_前缀。
 *
 *      prePostEnabled, 默认false。如果要使用 {@link org.springframework.security.access.prepost.PreAuthorize} 则需要开启 prePostEnabled = true
 *          {@link org.springframework.security.access.prepost.PreAuthorize}
 *         在标记的方法调用之前，通过表达式来计算是否可以授权访问。
 *         *    基于 {@link org.springframework.security.access.expression.SecurityExpressionOperations} 接口的表达式
 *            eg: @PreAuthorize("hasRole('ADMIN')"), 必须拥有ROLE_ADMIN角色。
 *         *    基于 {@link org.springframework.security.core.userdetails.UserDetails}的表达式，此表达式用以对当前用户的一些额外的限定操作。
 *            eg: @PreAuthorize("principal.username.startsWith('kon')"), 用户名开头为`kon`的用户才能访问。
 *         *    基于对入参的 SpEL 表达式处理。关于 SpEL 表达式可参考官方文档。
 *            eg: @PreAuthorize("#id.equals(principal.username)") 入参 id 必须同当前的用户名相同
 *
 *          {@link org.springframework.security.access.prepost.PostAuthorize}
 *         在标记的方法调用之后，通过表达式来计算是否可以授权访问。该注解是针对 @PreAuthorize 。区别 在于先执行方法。而后进行表达式判断。
 *       如果方法没有返回值实际上等于开放权限控制;如果有返回值 实际的结果是用户操作成功但是得不到响应
 *
 *          {@link org.springframework.security.access.prepost.PreFilter}
 *        基于方法入参相关的表达式，对入参进行过滤。分页慎用!该过程发生在接口接收参数之前。入参必须为 `java.util.Collection` 且支持 remove(Object) 的参数。如果有多个集合需要通过
 *        filterTarget=<参数名> 来指定过滤的集合。内置保留名称 filterObject 作为集合元素的操作名来进行评估过滤。
 *              // 入参为Collection<String> ids 测试数据 ["Felordcn","felord","jetty"]
 *              // 过滤掉 felord jetty 为 Felordcn
 *              // @PreFilter(value = "filterObject.startsWith('F')",filterTarget = "ids") // 如果 当前用户持有 ROLE_AD 角色 参数都符合 否则 过滤掉不是 f 开头的
 *              // DEMO 用户不持有 ROLE_AD 角色 故而 集合只剩下 felord @PreFilter("hasRole('AD') or filterObject.startsWith('f')")
 *          {@link org.springframework.security.access.prepost.PostFilter}
 *        和 @PreFilter 不同的是， 基于返回值相关的表达式，对返回值进行过滤。分页慎用!该过程发生接 口进行数据返回之前。 相关测试与 @PreFilter 相似。
 *
 *
 *      如果要开启 JSR-250 ,则需要开启 jsr250Enabled = true
 *          {@link javax.annotation.security.DenyAll} 拒绝所有的访问
 *          {@link javax.annotation.security.PermitAll} 同意所有的访问
 *          {@link javax.annotation.security.RolesAllowed} 用法和 5. 中的 @Secured 一样
 *
 *
 * @author kon, created on 2021/12/9T16:32.
 * @version 1.0.0-SNAPSHOT
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 授权时去掉默认角色前缀"ROLE_"
     *
     *      为了解决 {@link org.springframework.security.access.annotation.Secured} 配置后匹配问题。
     *   不处理的时候，默认登陆后会将权限进行处理，值 ： ROLE_权限 格式
     *
     *      此处不会影响 {@link org.springframework.security.access.prepost.PreAuthorize} 的正常使用。
     *   eg: @PreAuthorize("hasAnyAuthority('menu:system')")
     *
     * @return GrantedAuthorityDefaults
     */
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        // Remove the ROLE_ prefix
        return new GrantedAuthorityDefaults("");
    }

    /**
     * AuthenticationManager
     * @return  AuthenticationManager
     * @throws Exception 异常
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/doc.html", "/webjars/**", "/swagger-resources", "/v2/api-docs")
                .permitAll()
                .anyRequest()
                // 需要认证
                .authenticated();
        // 配置认证， 默认登入地址 /login
        http.formLogin();
        // 可以设置登出的URL地址，默认 /logout
        // http.logout().logoutUrl("");
        // // CSRF漏洞，为了方便通常会先禁用csrf，启用csrf先禁用掉下面这一行
        http.csrf().disable();
    }
}
