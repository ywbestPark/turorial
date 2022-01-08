package com.example.tutorial.service;

import com.example.tutorial.entity.ZthmError;
import com.example.tutorial.repository.ZthmErrorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ZthmErrorServiceImpl implements ZthmErrorService {

    private final ZthmErrorRepository zthmErrorRepository;

    @Override
    public void save(ZthmError zthmError) {
        zthmErrorRepository.save(zthmError);
    }

    @Override
    public List<ZthmError> getErrorList() {
        return zthmErrorRepository.findAll();
    }
}
