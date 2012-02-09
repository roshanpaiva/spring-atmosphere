<!DOCTYPE HTML>
<html>
    <head>
        <style type="text/css">
            body {
              font: 13px/1.5 'Helvetica Neue', Arial, Helvetica, 'Liberation Sans', FreeSans, sans-serif;
            }

            pre,
            code {
              font-family: Menlo, Consolas, monospace;
            }

            hr {
              border: 0 #ccc solid;
              border-top-width: 1px;
              clear: both;
              height: 0;
            }

            h1 {
              font-size: 25px;
            }

            h2 {
              font-size: 18px;
            }

            p,
            hr,
            h1,
            h2 {
              margin-bottom: 20px;
            }
        </style>
        <script type="text/javascript" src="jquery/jquery-1.6.4.js"></script>
        <script type="text/javascript" src="jquery/jquery.atmosphere.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                function callback(response) {
                    // Websocket events.
                    $.atmosphere.log("info", ["response.state: " + response.state]);
                    $.atmosphere.log("info", ["response.transport: " + response.transport]);
                    $.atmosphere.log("info", ["response.status: " + response.status]);

                    if (response.transport != "polling" && response.state == "messageReceived") {
                        $.atmosphere.log("info", ["response.responseBody: " + response.responseBody]);
                        if (response.status == 200) {
                            var data = response.responseBody;
                            $("#time").text(data);
                        }
                    }
                }

                $.atmosphere.subscribe(
                        "/comet/time",
                        callback,
                        $.atmosphere.request = { transport:"websocket", requestCount: 0 });
            });
        </script>
    </head>
    <body>
        <h1>Spring and Atmosphere</h1>
        <h2>The time pushed from the server</h2>
        <p id="time"></p>
    </body>
</html>
