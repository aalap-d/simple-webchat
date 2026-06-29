import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer {
    private static Map<String, List<String>> c = new ConcurrentHashMap<>();

    public static void main(String[] a) throws Exception {
        HttpServer s = HttpServer.create(new InetSocketAddress(9001), 0);
        s.createContext("/", new H());
        s.createContext("/chat", new C());
        s.createContext("/m", new M());
        s.createContext("/x", new X());
        s.setExecutor(null);
        s.start();
    }

    static void p(HttpExchange x, String f) throws IOException {
        File d = new File(f);
        byte[] b = new byte[(int) d.length()];
        FileInputStream i = new FileInputStream(d);
        i.read(b);
        i.close();
        x.sendResponseHeaders(200, b.length);
        OutputStream o = x.getResponseBody();
        o.write(b);
        o.close();
    }

    static String q(String u) {
        if (u == null || !u.contains("r=")) return "g";
        for (String k : u.split("&")) {
            if (k.startsWith("r=")) return k.split("=")[1];
        }
        return "g";
    }

    static class H implements HttpHandler {
        public void handle(HttpExchange x) throws IOException {
            p(x, "home.html");
        }
    }

    static class C implements HttpHandler {
        public void handle(HttpExchange x) throws IOException {
            p(x, "index.html");
        }
    }

    static class M implements HttpHandler {
        public void handle(HttpExchange x) throws IOException {
            String r = q(x.getRequestURI().getQuery());
            c.putIfAbsent(r, new ArrayList<>());
            List<String> l = c.get(r);

            if ("POST".equals(x.getRequestMethod())) {
                InputStream i = x.getRequestBody();
                byte[] b = i.readAllBytes();
                l.add(new String(b));
                x.sendResponseHeaders(200, 0);
                x.getResponseBody().close();
            } else {
                StringBuilder b = new StringBuilder("[");
                for (int j = 0; j < l.size(); j++) {
                    b.append("\"").append(l.get(j).replace("\"", "\\\"")).append("\"");
                    if (j < l.size() - 1) b.append(",");
                }
                b.append("]");
                byte[] z = b.toString().getBytes();
                x.getResponseHeaders().add("Content-Type", "application/json");
                x.sendResponseHeaders(200, z.length);
                OutputStream o = x.getResponseBody();
                o.write(z);
                o.close();
            }
        }
    }

    static class X implements HttpHandler {
        public void handle(HttpExchange x) throws IOException {
            String r = q(x.getRequestURI().getQuery());
            if (c.containsKey(r)) c.get(r).clear();
            x.sendResponseHeaders(200, 0);
            x.getResponseBody().close();
        }
    }
}