package vua.pavic.ZhoonstagramApi.security.JWTSupport;



import com.mysql.cj.xdevapi.JsonParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import vua.pavic.ZhoonstagramApi.model.Role;
import vua.pavic.ZhoonstagramApi.model.User;
import vua.pavic.ZhoonstagramApi.services.JdbcUserDetailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
public class JwtTokenUtil implements Serializable {

    private static final String SECRET ="SecretCodeBla" ;
    private static final String HEADER_STRING = "Authorization" ;
    private static final String TOKEN_PREFIX = "Bearer";


    static void addAuthentication(HttpServletRequest req, HttpServletResponse res, String username, JdbcUserDetailService userService) throws IOException {

        Calendar timeTillExpr = Calendar.getInstance();
        timeTillExpr.setTime(new Date(System.currentTimeMillis()));
        timeTillExpr.add(Calendar.MONTH,1);
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(timeTillExpr.getTime())
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();

        res.addHeader(HEADER_STRING,TOKEN_PREFIX + " " +token);
        res.getWriter().write(token);
        }

    static Authentication getAuthentication(HttpServletRequest req, JdbcUserDetailService userService){
        String token = req.getHeader(HEADER_STRING);
        if(token == null){
            token = req.getParameter("key");
        }
        if(token != null && !token.equals("")){

            String username;
            try{
            username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                        .getBody()
                        .getSubject();
            }catch(SignatureException ex){
                return null;
            }

            User user = userService.getUserByUsername(username);
            List<GrantedAuthority> grantedAuthorityList= new ArrayList<>();
            for (Role role: user.getRoles()) {
                grantedAuthorityList.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            if(username != null){
                return new UsernamePasswordAuthenticationToken(user.getEmail(),null, grantedAuthorityList);
            }
            else{
                return null;
            }

        }
        return null;
    }

   public static String unpackToken(String token){
        String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                .getBody()
                .getSubject();

        return user;
    }



}
