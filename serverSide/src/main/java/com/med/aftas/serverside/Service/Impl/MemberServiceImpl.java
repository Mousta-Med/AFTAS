package com.med.aftas.serverside.Service.Impl;

import com.med.aftas.serverside.Dto.MemberDto;
import com.med.aftas.serverside.Service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberServiceImpl implements MemberService {
    @Override
    public MemberDto save(MemberDto memberDto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public MemberDto update(Integer id, MemberDto memberDto) {
        return null;
    }

    @Override
    public MemberDto findOne(Integer id) {
        return null;
    }

    @Override
    public List<MemberDto> findAll() {
        return null;
    }

    @Override
    public Page<MemberDto> findWithPagination(Pageable pageable) {
        return null;
    }
}
