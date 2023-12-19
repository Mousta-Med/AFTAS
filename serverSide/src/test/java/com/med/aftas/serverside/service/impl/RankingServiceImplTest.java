package com.med.aftas.serverside.service.impl;

import com.med.aftas.serverside.dto.CompetitionDto;
import com.med.aftas.serverside.dto.RankingDto;
import com.med.aftas.serverside.model.Competition;
import com.med.aftas.serverside.model.Hunting;
import com.med.aftas.serverside.model.Member;
import com.med.aftas.serverside.model.Ranking;
import com.med.aftas.serverside.repository.RankingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class RankingServiceImplTest {

    @Mock
    private RankingRepository rankingRepository;
    @Mock private ModelMapper modelMapper;
    @Mock private HuntingServiceImpl huntingService;
    @Mock private CompetitionServiceImpl competitionService;

    @InjectMocks
    private RankingServiceImpl underTest;

    private Member member;
    private Competition competition;
    private Ranking ranking;
    private RankingDto rankingDto;
    private CompetitionDto competitionDto;
    private Hunting hunting;

//    @BeforeEach
//    public void setUp() {
//        member = Member.builder()
//                .num(1)
//                .name("ahmed")
//                .accessionDate(LocalDate.now())
//                .familyName("test")
//                .identityDocument(IdentityDocumentType.CIN)
//                .nationality("MA")
//                .identityNumber("HH121093")
//                .build();
//        competition = Competition.builder()
//                .code("Saf-12-12-23")
//                .date(LocalDate.now())
//                .startTime(LocalDateTime.now())
//                .endTime(LocalDateTime.now())
//                .amount(100.00)
//                .location("Safi")
//                .build();
//
//        hunting = Hunting.builder()
//                .numberOfFish(1)
//                .fish(Fish.builder()
//                        .level(Level.builder()
//                                .points(10)
//                                .build()
//                        ).build()
//                )
//                .build();
//
//        member = Member.builder()
//                .num(1)
//                .name("hamza")
//                .familyName("essouli")
//                .identityDocument(IdentityDocumentType.CIN)
//                .identityNumber("HH28712")
//                .accessionDate(LocalDate.now())
//                .nationality("MA")
//                .build();
//        CompetitionMember id = CompetitionMember.builder()
//                .memberNum(1)
//                .competitionCode("Saf-12-12-23")
//                .build();
//        competitionDto = new CompetitionDto();
//        competitionDto.setCode("Saf-12-12-23");
//        competitionDto.setDate(LocalDate.now());
//        competitionDto.setAmount(120.00);
//        competitionDto.setStartTime(LocalDateTime.now());
//        competitionDto.setEndTime(LocalDateTime.now());
//
//        ranking = Ranking.builder()
//                .id(id)
//                .score(0)
//                .member(member)
//                .competition(competition)
//                .build();
//        rankingDto = new RankingDto();
//        rankingDto.setId(id);
//        rankingDto.setScore(0);
//        rankingDto.setCompetition(competitionDto);
//    }

//    @Test
//    public void underTestMethodShouldThrowsARunTimeExceptionWhenANotExistCompetitionCodePassed() {
//        String competitionCode = "Not exist";
//        given(competitionService.findByID(competitionCode)).willThrow(RuntimeException.class);
//
//        assertThatExceptionOfType(RuntimeException.class)
//                .isThrownBy(() -> underTest.SetUpCompetitionRankings(competitionCode));
//
//        verify(competitionService).findByID(competitionCode);
//    }
//
//    @Test
//    public void underTestMethodShouldThrowsARunTimeExceptionWhenTheCompetitionDidNotStartYet() {
//        var competition = competitionDto;
//        competition.setStartTime(LocalDateTime.of(30, 1, 15, 0, 0));
//        given(competitionService.findByID("code")).willReturn(competitionDto);
//        assertThatExceptionOfType(RuntimeException.class)
//                .isThrownBy(() -> underTest.SetUpCompetitionRankings("code"));
//
//        verify(competitionService).findByID("code");
//    }
//
//    @Test
//    public void underTestMethodShouldThrowsARunTimeExceptionWhenTheCompetitionHaveNoRankings() {
//        var competition = competitionDto;
//        competition.setRankings(Arrays.asList());
//        given(competitionService.findByID("code")).willReturn(competitionDto);
//        given(rankingRepository.findByCompetitionCode("code")).willReturn(Arrays.asList());
//        assertThatExceptionOfType(RuntimeException.class)
//                .isThrownBy(() -> underTest.SetUpCompetitionRankings("code"));
//
//        verify(competitionService).findByID("code");
//        verify(rankingRepository).findByCompetitionCode("code");
//    }

}