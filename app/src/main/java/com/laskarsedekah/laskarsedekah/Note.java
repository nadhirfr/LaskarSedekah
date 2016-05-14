package com.laskarsedekah.laskarsedekah;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "note", strict = false)
public class Note {

    @Element(name = "to", required = false)
    private String to;
    @Element(name = "from", required = false)
    private String from;
    @Element(name = "heading", required = false)
    private String heading;
    @Element(name = "body", required = false)
    private String body;

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getHeading() {
        return heading;
    }

    public String getBody() {
        return body;
    }
}