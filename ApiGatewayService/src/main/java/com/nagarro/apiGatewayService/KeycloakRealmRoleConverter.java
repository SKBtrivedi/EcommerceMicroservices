//package com.nagarro.apiGatewayService;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
//
//    @Override
//    public Collection<GrantedAuthority> convert(Jwt jwt) {
//        // Fetch roles from 'realm_access' claim
//        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaim("realm_access");
//        Collection<String> roles = (Collection<String>) realmAccess.get("roles");
//
//        return roles.stream()
//            .map(role -> "ROLE_" + role) // Prefix role with 'ROLE_' to match Spring Security convention
//            .map(SimpleGrantedAuthority::new)
//            .collect(Collectors.toList());
//    }
//}
//
//
