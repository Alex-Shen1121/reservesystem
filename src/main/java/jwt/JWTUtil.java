package jwt;

import com.scy.model.UserInfoVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtil {
    //设置密钥
    private static String key="kappy";
    //有效时间长度  60分钟
    private static Long failTime=1000*60*30L;

    /**
     * 生成token
     */
    public static String createJsonWebToken(UserInfoVo user){
       //判断用户对象信息
        if(user==null || user.getId()==null || user.getUsername()==null){
            return null;
        }

        //生成token对象
        String token= Jwts.builder()
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                .claim("type",user.getType())
                .setIssuedAt(new Date())//签发时间
                .setExpiration(new  Date(System.currentTimeMillis()+failTime))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();

         return token;
    }

    /**
     * 验证token是否有效
     */
    public static Claims checkJwt(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户id
     */
    public static Integer getUserId(String token){
        Claims claims = JWTUtil.checkJwt(token);
        return (Integer) claims.get("id");
    }

    /**
     * 获取用户姓名
     */
    public static String getUsername(String token){
        Claims claims = JWTUtil.checkJwt(token);
        return (String) claims.get("username");
    }

    /**
     * 获取用户姓名
     */
    public static String getUserType(String token){
        Claims claims = JWTUtil.checkJwt(token);
        return (String) claims.get("type");
    }

    /**
     * 判断token是否过期
     */
    public static boolean isTokenExpired(String token){
        Claims claims = JWTUtil.checkJwt(token);
        Date times=claims.getExpiration();
        return times.before(new Date());
    }

    public static void main(String [] args){
       UserInfoVo user=new UserInfoVo();
        user.setId(1);
        user.setType("1");
        user.setUsername("kappys");
        String token=createJsonWebToken(user);
        System.out.println("token:"+token);

        //解析用户名称信息
        String name= getUsername(token);
        System.out.println(name+"---------");

        //String string="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJrYXBweSIsInR5cGUiOiIxIiwiaWF0IjoxNjEyMzY2NzY5LCJleHAiOjE2MTIzNjg1Njl9.CeLztv0LVZuKwxNHEuNKNQxYC-hxsOIfF99fgORsgok";
        boolean bs=isTokenExpired(token);
        System.out.println(bs+"~~~~");


    }
}
