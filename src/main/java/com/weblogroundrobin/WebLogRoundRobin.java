package com.weblogroundrobin;
import com.weblogroundrobin.logService.RRInvoker;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class WebLogRoundRobin
{
    public static void main( String[] args )
    {
        port(getPort());
        staticFiles.location("/public");

        get("/log", (req, res) ->
            RRInvoker.invoke()
        );

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
