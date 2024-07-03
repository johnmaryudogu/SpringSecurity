package com.johnmary.SprongSecurityDemo.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final static String SECRET_KEY =
            //the key was gotten online from https://jwt-keys.21no.de/ hs256, byte 64


            "idnBQNltXkrvex4mPmx7G1rk1FC1wh1eDLzgEkfFv/FzbiTwsf7/9t5rlhVNgjMw4b0084a5kFncslg7dePVIg==";


        //extract all claims

        private Claims extractAllClaims(String token){

            return Jwts
                    .parser()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getPayload();
        }

        //we are decoding the code here
        private Key getSignInKey() {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

            return Keys.hmacShaKeyFor(keyBytes);
        }

        //extract single claims

        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
            final Claims claims = extractAllClaims(token);

            return claimsResolver.apply(claims);
        }

        //method to extract claim

        public String extractUsername(String token){
            return extractClaim(token, Claims::getSubject);
        }

        //method to generate token
        public String generateToken(Map<String, Object> extractClaims,
                                    UserDetails userDetails){
            return Jwts
                    .builder()
                    .setClaims(extractClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 24 ))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
        }
        public String generateToken(UserDetails userDetails){
            return (generateToken(new HashMap<>(), userDetails));
        }
        //check if token is valid
        public Boolean isTokenValid(String token, UserDetails userDetails){
            final String username = extractUsername(token);

            return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        }

        private boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        private Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

    }