package org.example.demoproject.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<F, T> {

    T mapFrom(F from);

    F mapTo(T to);
}
