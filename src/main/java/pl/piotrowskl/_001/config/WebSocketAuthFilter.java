package pl.piotrowskl._001.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.util.Map;

public class WebSocketAuthFilter implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        URI uri = request.getURI();
        String token = getQueryParam(uri, "token");
        if (token != null) {
            // Verify the token and get the authentication object
            Authentication auth = authenticateToken(token);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        return true;

    }

    private Authentication authenticateToken(String token) {
        // Implement your token authentication logic here
        // For example, you can use a JWT token service to parse and validate the token
        // and then return the corresponding Authentication object
        return null; // Replace with actual authentication logic
    }

    private String getQueryParam(URI uri, String param) {
        String query = uri.getQuery();
        if (query == null || query.isEmpty()) {
            return null;
        }
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            if (idx > 0 && pair.substring(0, idx).equals(param)) {
                return pair.substring(idx + 1);
            }
        }
        return null;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
