package org.example.demoproject.mapper;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Mapper<F, T> {

    T mapFrom(F from);

}
