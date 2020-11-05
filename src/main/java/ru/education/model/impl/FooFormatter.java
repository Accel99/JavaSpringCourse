package ru.education.model.impl;

import org.springframework.stereotype.Component;
import ru.education.model.Formatter;

@Component("fooFormatter")
public class FooFormatter implements Formatter {

    public String format() {
        return "foo";
    }
}
