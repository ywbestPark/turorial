package com.example.tutorial.service;

import com.example.tutorial.entity.ZthmError;

import java.util.List;

public interface ZthmErrorService {
    void save(ZthmError zthmError);
    List<ZthmError> getErrorList();
}
