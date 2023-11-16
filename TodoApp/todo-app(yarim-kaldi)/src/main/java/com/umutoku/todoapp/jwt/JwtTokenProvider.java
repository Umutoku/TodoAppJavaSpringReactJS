package com.umutoku.todoapp.jwt;

import com.umutoku.todoapp.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final String secretKey = "yourSecretKey"; // Güvenli bir şekilde saklanmalıdır
    private final long validityInMilliseconds = 3600000; // 1 saat geçerlilik süresi
    @Autowired
    private final JwtParser jwtParser;
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("firstName",user.getFirstName());
        claims.put("lastName",user.getLastName());
        Date tokenCreateDate = new Date();
        Date validity = new Date(tokenCreateDate.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenCreateDate)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, getKey())
                .compact();
    }

    private Key getKey() {
        String secretKey="sdd";
        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }
    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }
    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        List<String> roles = (List<String>) claims.get("roles");

        Collection<? extends GrantedAuthority> authorities =
                roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, "", authorities);

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }


    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Loglama veya kontrol amacıyla e.printStackTrace(); kullanabilirsiniz
            return false;
        }
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }


    public String getEmail(Claims claims) {
        return claims.getSubject();
    }
//    private List<String> getRoles(Claims claims) {
//        return (List<String>) claims.get("roles");
//    }
    //    public String generateToken(UserDetails user) {
//        return Jwts.builder()
//                .setClaims(new HashMap<>())
//                .setSubject(user.getUsername())
//                .setSubject(user.getPassword())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//                .signWith(SignatureAlgorithm.HS256, getKey())
//                .compact();
//    }
    //    public UserResponse save(UserDto userDto) {
//        User user = User.builder()
//                .username(userDto.getUsername())
//                .password(passwordEncoder.encode(userDto.getPassword()))
//                .nameSurname(userDto.getNameSurname())
//                .role(Role.USER).build();
//        IUserRepository.save(user);
//        var token = jwtService.generateToken(user);
//        return UserResponse.builder().token(token).build();
//
//    }
//
//    public UserResponse auth(UserRequest userRequest) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
//        User user = IUserRepository.findByUsername(userRequest.getUsername()).orElseThrow();
//        String token = jwtService.generateToken(user);
//        return UserResponse.builder().token(token).build();
//    }
}
