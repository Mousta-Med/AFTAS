package com.med.aftas.serverside.service;

import com.med.aftas.serverside.dto.UserDto;
import com.med.aftas.serverside.dto.UserLoginDto;
import com.med.aftas.serverside.dto.respDto.UserRespDto;
import com.med.aftas.serverside.model.AuthenticationResponse;

import java.util.List;

public interface UserService extends BaseService<UserDto, Integer, UserRespDto> {
    List<UserRespDto> findByNameOrFamilyName(String query);
    AuthenticationResponse register(UserDto userDto);
    AuthenticationResponse login(UserLoginDto userDto);
}
