package org.matt.roberts.controllers;

import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.Meteor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * A Spring MVC controller that enables the server to push the time to clients.
 */
@Controller
public class CometController {

    /**
     * A BroadcasterFactory (injected as a singleton) that enables client code to look up broadcasters.
     */
    private final BroadcasterFactory bf;

    /**
     * Instantiates a new instance of the CometController class.
     * @param bf A BroadcasterFactory (injected as a singleton) that enables client code to look up broadcasters.
     */
    @Autowired
    public CometController(BroadcasterFactory bf) {
        this.bf = bf;
        this.initialiseBroadcaster();
    }

    /**
     * When a comet request is received allow Spring to auto-magically resolve the meteor argument then:
     * Set the meteor's broadcaster to '/comet/time', suspend, then broadcast to this request only.
     * @param m A meteor that represents the current comet session.
     */
    @RequestMapping("/comet/time")
    @ResponseBody
    public void time(final Meteor m) {
        final Broadcaster b = this.bf.lookup("/comet/time");
        m.setBroadcaster(b);
        m.suspend(-1);
        b.broadcast(new DateTime().toString(), m.getAtmosphereResource());
    }

    /**
     * Display the index.jsp file that contains the client atmosphere javascript code.
     * @return
     */
    @RequestMapping(value = "/comet", method = RequestMethod.GET)
    public String index() {
        return "comet/index";
    }

    /**
     * Start broadcasting the time every 5 seconds using the /comet/time broadcaster.
     */
    private void initialiseBroadcaster() {
        final Broadcaster b = this.bf.lookup("/comet/time", true);
        b.scheduleFixedBroadcast(
                new Callable<String>() {
                    @Override
                    public String call() {
                        return new DateTime().toString();
                    }
                },
                5,
                TimeUnit.SECONDS);
    }
}
