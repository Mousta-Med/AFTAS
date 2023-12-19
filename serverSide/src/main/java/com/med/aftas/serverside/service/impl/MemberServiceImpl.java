package com.med.aftas.serverside.service.impl;

import com.med.aftas.serverside.dto.MemberDto;
import com.med.aftas.serverside.dto.respDto.MemberRespDto;
import com.med.aftas.serverside.exception.ResourceNotFoundException;
import com.med.aftas.serverside.model.Member;
import com.med.aftas.serverside.repository.MemberRepository;
import com.med.aftas.serverside.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public MemberRespDto save(MemberDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        return modelMapper.map(memberRepository.save(member), MemberRespDto.class);
    }

    @Override
    public void delete(Integer id) {
        memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member Not found with this: " + id));
        memberRepository.deleteById(id);
    }

    @Override
    public MemberRespDto update(Integer id, MemberDto memberDto) {
        Member existingMember = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member Not found with this: " + id));
        BeanUtils.copyProperties(memberDto, existingMember);
        existingMember.setNum(id);
        return modelMapper.map(memberRepository.save(existingMember), MemberRespDto.class);
    }

    @Override
    public MemberRespDto findOne(Integer id) {
        return memberRepository.findById(id)
                .map(member -> modelMapper.map(member, MemberRespDto.class)).orElseThrow(() -> new ResourceNotFoundException("Member Not found with this: " + id));
    }

    @Override
    public List<MemberRespDto> findAll() {
        return memberRepository.findAll().stream().map(member -> modelMapper.map(member, MemberRespDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<MemberRespDto> findWithPagination(Pageable pageable) {
        Page<Member> membersPage = memberRepository.findAll(pageable);
        return membersPage.map(member -> modelMapper.map(member, MemberRespDto.class));
    }

    @Override
    public List<MemberRespDto> findByNameOrFamilyName(String query) {
        return memberRepository.findMemberByNameContainingIgnoreCaseOrFamilyNameContainingIgnoreCase(query, query)
                .stream().map(member -> modelMapper.map(member, MemberRespDto.class)).collect(Collectors.toList());
    }
}
