package com.med.aftas.serverside.service.impl;

import com.med.aftas.serverside.dto.HuntingDto;
import com.med.aftas.serverside.exception.ResourceNotFoundException;
import com.med.aftas.serverside.model.Hunting;
import com.med.aftas.serverside.repository.HuntingRepository;
import com.med.aftas.serverside.service.HuntingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HuntingServiceImpl implements HuntingService {
    @Autowired
    private HuntingRepository huntingRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public HuntingDto save(HuntingDto huntingDto) {
        Optional<Hunting> huntingOptional = huntingRepository.getHuntingByCompetitionCodeAndFishNameAndMemberNum(huntingDto.getCompetition().getCode(), huntingDto.getFish().getName(), huntingDto.getMember().getNum());
        if (huntingOptional.isPresent()) {
            huntingOptional.get().setNumberOfFish(huntingOptional.get().getNumberOfFish() + huntingDto.getNumberOfFish());
            return modelMapper.map(huntingRepository.save(huntingOptional.get()), HuntingDto.class);
        } else {
            Hunting hunting = modelMapper.map(huntingDto, Hunting.class);
            return modelMapper.map(huntingRepository.save(hunting), HuntingDto.class);
        }
    }

    @Override
    public void delete(Integer id) {
        huntingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hunting Not found with this: " + id));
        huntingRepository.deleteById(id);
    }

    @Override
    public HuntingDto update(Integer id, HuntingDto huntingDto) {
        Hunting existingHunting = huntingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hunting Not found with this: " + id));
        BeanUtils.copyProperties(huntingDto, existingHunting);
        existingHunting.setId(id);
        return modelMapper.map(huntingRepository.save(existingHunting), HuntingDto.class);
    }

    @Override
    public HuntingDto findOne(Integer id) {
        return huntingRepository.findById(id)
                .map(hunting -> modelMapper.map(hunting, HuntingDto.class)).orElseThrow(() -> new ResourceNotFoundException("Hunting Not found with this: " + id));
    }

    @Override
    public List<HuntingDto> findAll() {
        return huntingRepository.findAll().stream().map(hunting -> modelMapper.map(hunting, HuntingDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<HuntingDto> findWithPagination(Pageable pageable) {
        Page<Hunting> huntingsPage = huntingRepository.findAll(pageable);
        return huntingsPage.map(hunting -> modelMapper.map(hunting, HuntingDto.class));
    }


    @Override
    public List<HuntingDto> getHuntingsByCompetitionCodeAndMemberNum(String code, Integer num) {
        return huntingRepository.getHuntingsByCompetitionCodeAndMemberNum(code, num).stream().map(hunting -> modelMapper.map(hunting, HuntingDto.class)).collect(Collectors.toList());
    }
}
