import {FishDto} from "./fish-dto.model";
import {MemberDto} from "./member-dto.model";
import {CompetitionDto} from "./competition-dto.model";

export interface HuntingDto {
  id: number;
  numberOfFish: number;
  fishName: string;
  memberNum: number | undefined;
  competitionCode: string;
  competition?: CompetitionDto;
  member?: MemberDto;
  fish?: FishDto;
}
