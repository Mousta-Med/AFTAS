import {CompetitionDto} from "./competition-dto.model";
import {MemberDto} from "./member-dto.model";

export interface RankingDto{
  rank: number,
  score: number,
  memberNum: number,
  competitionCode: string,
  member?: MemberDto,
  competition?: CompetitionDto,
}
