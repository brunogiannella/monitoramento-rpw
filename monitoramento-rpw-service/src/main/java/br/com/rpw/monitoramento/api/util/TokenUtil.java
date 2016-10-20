package br.com.rpw.monitoramento.api.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import br.com.rpw.monitoramento.api.constantes.RpwServiceConstantes;
import br.com.rpw.monitoramento.api.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {

	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(RpwServiceConstantes.SECRET_KEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	public static Claims getTokenInformation(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(RpwServiceConstantes.SECRET_KEY))
				.parseClaimsJws(jwt).getBody();
		
		return claims;
	}
	
	public static Boolean validToken(String jwt, Usuario usuario) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(RpwServiceConstantes.SECRET_KEY))
				.parseClaimsJws(jwt).getBody();
		
		if(!"Autenticação RPW".equals(claims.getSubject())) {
			return false;
		}
		
		if(!claims.getIssuer().equals(usuario.getTipoUsuario().getDescricao())) {
			return false;
		}

		if(!claims.getId().equals(usuario.getId().toString())) {
			return false;
		}
		
		long nowMillis = System.currentTimeMillis();
		long expireMillis = claims.getExpiration().getTime();
		if(expireMillis < nowMillis) {
			return false;
		}
		
		return true;
	}
	
	public static Boolean simpleValidToken(String jwt) {
		
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(RpwServiceConstantes.SECRET_KEY))
					.parseClaimsJws(jwt).getBody();
			
			if(!"Autenticação RPW".equals(claims.getSubject())) {
				return false;
			}
			
			long nowMillis = System.currentTimeMillis();
			long expireMillis = claims.getExpiration().getTime();
			if(expireMillis < nowMillis) {
				return false;
			}
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
}
