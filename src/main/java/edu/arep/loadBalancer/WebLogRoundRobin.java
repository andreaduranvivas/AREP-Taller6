package edu.arep.loadBalancer;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class WebLogRoundRobin
{
    public static void main( String[] args ){
        port(getPort());
        staticFiles.location("/public");

        get("/log", (req, res) -> {
            String message = req.queryParams("msg");
            return RRInvoker.invoke(message);
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}
